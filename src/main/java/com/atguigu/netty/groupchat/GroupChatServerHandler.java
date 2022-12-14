package com.atguigu.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Author Yjw
 * 2022/10/27 23:31
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    //使用一个 hashmap 管理
//    public static Map<String, Channel> channels = new HashMap<String, Channel>();
//    public static Map<User, Channel> channels2 = new HashMap<User, Channel>();

    //定义一个 channel 组，管理所有的 channel
    //GlobalEventExecutor.INSTANCE 是全局的事件执行器，是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //handlerAdded 表示连接建立，一旦连接，第一个被执行
    //将当前 channel 加入到 channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户加入聊天的信息推送给其他在线的客户端
        /**
         * 该方法会将 channelGroup 中所有的 channel 遍历，并发送消息
         * 不需要自己遍历
         */
        channelGroup.writeAndFlush("[客户端]" +
                channel.remoteAddress() +
                "加入聊天" +
                sdf.format(new java.util.Date()) + "\n");
        channelGroup.add(channel);

    }

    //断开连接，推送 xx 用户离开消息
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "离开了\n");
        System.out.println("channelGroup size" + channelGroup.size());
    }

    //表示 channel 处于活动状态，提示 xx 上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().remoteAddress() + " 上线了 ");
    }

    //表示 channel 处于不活动状态，提示 xx 离线
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 离线了 ");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //获取当前 channel
        Channel channel = ctx.channel();
        //此时遍历 channelGroup，根据不同情况，回送不同消息
        channelGroup.forEach(ch -> {
            if (channel != ch) {//不是当前 channel，转发消息
                ch.writeAndFlush("[客户]" +
                        channel.remoteAddress() +
                        "发送了消息" +
                        msg + "\n");
            }else {//回显消息
                ch.writeAndFlush("[自己]发送了消息" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.close();
    }
}
