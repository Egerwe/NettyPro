package com.atguigu.netty.dubborpc.provider;

import com.atguigu.netty.dubborpc.publicinterface.HelloService;

/**
 * Author Yjw
 * 2022/11/15 20:10
 */
public class HelloServiceImpl implements HelloService {

    private static int count = 0;
    //当有消费方调用该方法时，返回一个结果
    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息" + msg);
        if (msg != null) {
            return "你好，客户端，我已经收到你的消息 [" + msg + "]第" + (++count) + "次";
        } else {
            return "你好，客户端，我已经收到你的消息 ";
        }
    }
}
