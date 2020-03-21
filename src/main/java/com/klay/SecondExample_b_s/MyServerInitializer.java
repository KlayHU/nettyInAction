package com.klay.SecondExample_b_s;

import io.netty.channel.ChannelInitializer;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;


/**
 * @description: 自定义初始化组件
 * @author: KlayHu
 * @create: 2019/10/6 18:11
 **/
public class MyServerInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline =  ch.pipeline();      //获取到对应pipeline对象
        //往里添加handler,编解码，戳api
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
        pipeline.addLast(new LengthFieldPrepender(4));

        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));      //字符串编解码
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));

        pipeline.addLast(new MyServerHandler());     //添加我们自己的处理器
    }
}
