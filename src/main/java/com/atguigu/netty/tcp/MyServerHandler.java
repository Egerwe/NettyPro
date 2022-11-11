package com.atguigu.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Author Yjw
 * 2022/11/10 21:23
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        //将 buffer 转成字符串
        String message = new String(buffer, Charset.forName("utf-8"));
        System.out.println("服务器端接收到数据 = " + message);
        System.out.println("服务器端接收消息量 = " + (++this.count));

        //服务器回送数据给客户端
        ByteBuf buf = Unpooled.copiedBuffer("\n" + UUID.randomUUID().toString(), Charset.forName("utf-8"));
        ctx.writeAndFlush(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
