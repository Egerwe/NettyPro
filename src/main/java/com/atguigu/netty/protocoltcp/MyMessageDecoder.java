package com.atguigu.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Author Yjw
 * 2022/11/11 19:43
 */
public class MyMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder decode 被调用");

        //将得到的二进制字节码 -> MessageProtocol 数据包(对象)
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);

        //封装成 MessageProtocol 对象，放入到 out，传递给下一个 handler 业务处理
        MessageProtocol protocol = new MessageProtocol();
        protocol.setLen(length);
        protocol.setContent(content);

        out.add(protocol);

    }
}
