package com.atguigu.netty.dubborpc.provider;

import com.atguigu.netty.dubborpc.netty.NettyServer;

/**
 * Author Yjw
 * 2022/11/15 20:14
 */
//启动服务提供者，就是 NettyServer
public class ServerBootstrap {

    public static void main(String[] args) {

        NettyServer.startServer("127.0.0.1", 7000);
    }
}
