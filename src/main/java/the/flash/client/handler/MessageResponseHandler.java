package the.flash.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.protocol.response.MessageResponsePacket;

/**
 * 消息响应处理
 *
 * @author <a href="mailto:xuanjian@wacai.com">xuanjian</a>
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUsername = messageResponsePacket.getFromUsername();
        String message = messageResponsePacket.getMessage();
        System.out.println(fromUserId + ":" + fromUsername + " -> " + message);
    }
}
