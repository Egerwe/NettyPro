package com.atguigu.netty.inboundhandlerandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Author Yjw
 * 2022/11/3 22:23
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     *
     * @param ctx  上下文对象
     * @param in   入站的 ByteBuf
     * @param out  list 集合，解码后的数据传给下一个 handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        System.out.println("MyByteToLongDecoder 被调用");
        // Long 8个字节，需要8个字节才能读取一个 Long
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
