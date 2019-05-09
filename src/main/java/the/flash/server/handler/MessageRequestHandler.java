package the.flash.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.protocol.request.MessageRequestPacket;
import the.flash.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * 消息请求处理
 *
 * @author <a href="mailto:xuanjian@wacai.com">xuanjian</a>
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println(new Date() + " 收到客户端消息: " + messageRequestPacket.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复 [" + messageRequestPacket.getMessage() + "]");
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
