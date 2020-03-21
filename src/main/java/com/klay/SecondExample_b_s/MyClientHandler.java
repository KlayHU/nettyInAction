package com.klay.SecondExample_b_s;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @description: netty客户端处理
 * @author: KlayHu
 * @create: 2019/10/7 1:48
 **/
public class MyClientHandler extends SimpleChannelInboundHandler<String>{           //戳源码

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("client output" + msg);
        ctx.writeAndFlush("from client" + LocalDateTime.now());
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Thread.sleep(2000);
        ctx.writeAndFlush("你好！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
