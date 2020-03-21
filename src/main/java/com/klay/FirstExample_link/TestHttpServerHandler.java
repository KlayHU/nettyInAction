package com.klay.FirstExample_link;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {           //读取客户端发来的请求并向客户端返回响应的方法

        System.out.println(msg.getClass());

        System.out.println(ctx.channel().remoteAddress());
        Thread.sleep(8000);

        if(msg instanceof HttpRequest){

            HttpRequest httpRequest = (HttpRequest)msg;     //啰嗦一下，转换msg类型

            System.out.println("请求方法名:" + httpRequest.method().name());

            URI uri = new URI(httpRequest.uri());

            if("/favicon.ico".equals(uri.getPath()))
            {
                System.out.println("请求favicon.con");
                return;
            }
            ByteBuf content = Unpooled.copiedBuffer("Hello world !", CharsetUtil.UTF_8);                //ByteBuf对象是向客户端返回的内容

        //netty提供的简化的专门支撑响应的对象
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);       //响应不是ServerResponses而是netty,HttpVersion1.1就是keep alive

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");                              //设置response相关的头信息,内容类型

            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());                 //响应内容长度

            ctx.writeAndFlush(response);
            ctx.channel().close();

        }
    }

    /**
     * @Description:   重写SimpleChannelInboundHandler中的方法，可以进一步了解连接创建的步骤
     *
     * @Author: KlayHu
     *
     * @Create: 2019/10/6 12:14
     **/

    //回调方法

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println("handler added");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("channel registered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.out.println("channel unregistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("channel active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("channel inactive");
    }
}