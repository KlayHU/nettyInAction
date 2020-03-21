package com.klay.SecondExample_b_s;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @description: 自己定义的处理器
 * @author: KlayHu
 * @create: 2019/10/7 1:04
 **/
public class MyServerHandler extends SimpleChannelInboundHandler<String>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "," + msg);
        System.out.println(ctx.writeAndFlush("from server :" + UUID.randomUUID()));

    }

    //当出现异常，我们会将这个连接关闭掉
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
