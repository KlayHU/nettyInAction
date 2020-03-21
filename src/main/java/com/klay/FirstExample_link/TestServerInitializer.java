package com.klay.FirstExample_link;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(io.netty.channel.socket.SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();        //pipeline管道中有很多ChannelHandler的拦截器

        channelPipeline.addLast("HttpServerCodec",new HttpServerCodec());       //netty提供的处理器

        channelPipeline.addLast("TestServerHandler",new TestHttpServerHandler());     //自己编写的子处理器也需要加入到管道当中
    }
}
