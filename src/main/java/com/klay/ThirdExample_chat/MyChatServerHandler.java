package com.klay.ThirdExample_chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @description:    【聊天---服务器处理】
 * @author: KlayHu
 * @create: 2019/10/8 17:23
 **/
public class MyChatServerHandler extends SimpleChannelInboundHandler<String>{


    //定义保存建立连接的Channel对象的实例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(ch->{
            if(channel!=ch){
                ch.writeAndFlush("【" + channel.remoteAddress() +"】" + "发送的消息:" + msg + "\n");
            }else{
                ch.writeAndFlush("【我:】" + msg + "\n");
            }
        });


    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("【服务器：】-" + channel.remoteAddress() + "=======已加入！=======\n");     //遍历每一个channel对象,新连接的channel的远程地址告诉别的client它加入了。
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();        //获取channel对象
        channelGroup.writeAndFlush("【服务器：】-" + channel.remoteAddress() + "=======已离开！=======\n");

        System.out.println(channelGroup.size());    //当有客户端断开连接的时候，没有必要调用Remove，验证一下。
        //channelGroup.remove(channel);     netty会自动调用
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("【" + channel.remoteAddress() + "】" + "====上线了====");
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("【" + channel.remoteAddress() + "】" + "====下线了====");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
