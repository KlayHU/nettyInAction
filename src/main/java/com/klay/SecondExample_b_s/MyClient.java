package com.klay.SecondExample_b_s;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @description: 客户端
 * @author: KlayHu
 * @create: 2019/10/7 1:16
 **/
public class MyClient {
    public static void main(String[] args) throws Exception{
        //对于客户端来说只需要一个事件循环组，直接往服务端连
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).
                    handler(new MyClientInitializer());          //childHandler和handler的区别，跟组-group-有关

            ChannelFuture channelFuture = bootstrap.connect("localhost",8888).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            eventLoopGroup.shutdownGracefully();        //优雅关闭
        }
    }
}
