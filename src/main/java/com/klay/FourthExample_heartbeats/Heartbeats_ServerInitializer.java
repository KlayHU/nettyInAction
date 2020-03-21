package com.klay.FourthExample_heartbeats;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: KlayHu
 * @create: 2019/10/10 19:53
 **/
public class Heartbeats_ServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new IdleStateHandler(5,5,10, TimeUnit.SECONDS));
        pipeline.addLast(new Heartbeats_ServerHandler());
        
    }
}
