package com.atguigu.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Author Yjw
 * 2022/11/3 22:34
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //加入一个出站的 handler 对数据进行一个编码
        pipeline.addLast(new MyLongToByteEncoder());

        pipeline.addLast(new MyByteToLongDecoder2());
        //加入一个自定义的 handler 处理业务
        pipeline.addLast(new MyClientHandler());
    }
}
