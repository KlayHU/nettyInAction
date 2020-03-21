package com.klay.FirstExample_link;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    public static void main(String[] args) throws Exception{
        //创建两个线程组,一个bossGroup,一个workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();     //事件循环组，异步io
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();                    //Bootstrap和ServerBootstrap是netty服务端启动的配置类
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)          //用到NioServerSocketChannel管道
                    .childHandler(new TestServerInitializer());                             //子处理器,(这里用到的是自己定义的初始化器)

            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();            //绑定端口,同步
            channelFuture.channel().closeFuture().sync();                   //关闭
        }
        finally{
            bossGroup.shutdownGracefully();             //优雅关闭
            workerGroup.shutdownGracefully();
        }
    }
}
