package the.flash.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * 消息响应处理
 *
 * @author <a href="mailto:xuanjian@wacai.com">xuanjian</a>
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println(new Date() + " 客户端收到消息: " + messageResponsePacket.getMessage());
    }
}
