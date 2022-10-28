package com.atguigu.nio;

import java.nio.ByteBuffer;

/**
 * Author Yjw
 * 2022/10/14 14:33
 */
public class NIOByteBufferPutGet {
    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('姚');
        buffer.putShort((short) 4);

        buffer.flip();
        System.out.println();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
//        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());

        System.out.println(buffer.getShort());
    }
}
