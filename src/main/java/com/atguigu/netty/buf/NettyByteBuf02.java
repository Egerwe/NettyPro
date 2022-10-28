package com.atguigu.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Author Yjw
 * 2022/10/25 21:58
 */
public class NettyByteBuf02 {
    public static void main(String[] args) {

        //创建ByteBuf
        ByteBuf buffer = Unpooled.copiedBuffer("hello,world", Charset.forName("utf-8"));

        //使用相关的api
        if (buffer.hasArray()) {
            byte[] array = buffer.array();
            //将arr转成字符串
            System.out.println(new String(array, Charset.forName("utf-8")));
            System.out.println(buffer);
            System.out.println(buffer.arrayOffset());
            System.out.println(buffer.readerIndex());
            System.out.println(buffer.writerIndex());
            System.out.println(buffer.capacity());

            System.out.println(buffer.readByte());

            int i = buffer.readableBytes();
            System.out.println("i = " + i);
        }
    }
}
