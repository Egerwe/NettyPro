package com.atguigu.netty.dubborpc.customer;

import com.atguigu.netty.dubborpc.netty.NettyClient;
import com.atguigu.netty.dubborpc.publicinterface.HelloService;

/**
 * Author Yjw
 * 2022/11/15 21:07
 */
public class ClientBootstrap {

    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) throws InterruptedException {
        //创建一个消费者
        NettyClient customer = new NettyClient();

        //创建代理对象
        HelloService service = (HelloService) customer.getBean(HelloService.class, providerName);

        for (;;) {
            Thread.sleep(2000);
            //通过代理对象调用服务提供者的方法
            String res = service.hello("你好 dubbo");
            System.out.println("调用的结果 res = " + res);
        }

    }
}
