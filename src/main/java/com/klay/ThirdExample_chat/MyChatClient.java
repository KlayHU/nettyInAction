package com.klay.ThirdExample_chat;

import io.netty.bootstrap.Bootstrap;
        import io.netty.channel.Channel;
        import io.netty.channel.EventLoopGroup;
        import io.netty.channel.nio.NioEventLoopGroup;
        import io.netty.channel.socket.nio.NioSocketChannel;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;

/**
 * @description:    【聊天---客户端】
 * @author: KlayHu
 * @create: 2019/10/8 18:40
 **/
public class MyChatClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost",8888).sync().channel();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for(;;){
                channel.writeAndFlush(br.readLine() + "\r\n");
            }

        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
