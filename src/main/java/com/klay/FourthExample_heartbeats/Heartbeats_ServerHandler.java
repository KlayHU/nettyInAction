package com.klay.FourthExample_heartbeats;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @description:
 * @author: KlayHu
 * @create: 2019/10/10 20:11
 **/
public class Heartbeats_ServerHandler extends ChannelInboundHandlerAdapter{     //这次不继承SimpChannelInboundHandler,相反，继承ChannelInboundHandlerAdapter


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){      //如果当前事件是一个空闲状态事件，就做强制类型转换
            IdleStateEvent event = (IdleStateEvent) evt;

            String EventType = null;        //定义一个字符串，switch里面做状态判断

            switch (event.state()){
                case READER_IDLE:
                    EventType = "通道读空闲";
                    break;
                case WRITER_IDLE:
                    EventType = "通道写空闲";
                    break;
                case ALL_IDLE:
                    EventType = "通道读写空闲";
                    break;
            }

            System.out.println("【" + ctx.channel().remoteAddress() + "】" + "超时事件:" + EventType);
            ctx.channel().close().sync();
        }
    }
}
