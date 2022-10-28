package com.atguigu.nio;

import java.nio.IntBuffer;

/**
 * Author Yjw
 * 2022/10/11 21:00
 */
public class BasicBuffer {
    public static void main(String[] args) {

        //创建一个Buffer，大小为5
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //存入数据
//        intBuffer.put(10);
//        intBuffer.put(11);
//        intBuffer.put(12);
//        intBuffer.put(13);
//        intBuffer.put(14);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put( i * 2 );
        }
        //读取数据
        //将Buffer转换，读写切换
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
