package com.atguigu.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Author Yjw
 * 2022/10/25 21:34
 */
public class NettyByteBuf01 {
    public static void main(String[] args) {

        /**
         * 创建一个ByteBuf
         * 1.创建对象，该对象包含一个 arr ，是一个 byte[10]
         * 2.在 netty 的 buffer 中，不需要使用 flip 进行反转
         *   底层维护了 readerIndex 和 writerIndex
         */
        ByteBuf buffer = Unpooled.buffer(10);
        
        for (int i = 0; i < 10; i++) buffer.writeByte(i);

        System.out.println("capacity = " + buffer.capacity());
//        for (int i = 0; i < buffer.capacity(); i++) System.out.println(buffer.getByte(i));

        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }
        System.out.println("执行完毕");
    }
}
