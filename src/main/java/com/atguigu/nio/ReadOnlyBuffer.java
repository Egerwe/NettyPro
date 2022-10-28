package com.atguigu.nio;

import java.nio.ByteBuffer;

/**
 * Author Yjw
 * 2022/10/14 14:47
 */
public class ReadOnlyBuffer {
    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        for (int i = 0; i < 64; i++) byteBuffer.put((byte) i);

        byteBuffer.flip();

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }

//        readOnlyBuffer.put((byte) 100);
    }
}
