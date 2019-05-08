package the.flash.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.flash.protocol.Packet;
import the.flash.protocol.PacketCodec;
import the.flash.protocol.request.LoginRequestPacket;
import the.flash.protocol.request.MessageRequestPacket;
import the.flash.protocol.response.LoginResponsePacket;
import the.flash.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * ServerHandler
 *
 * @author xuanjian
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf request = (ByteBuf) msg;
        Packet packet = PacketCodec.INSTANCE.decode(request);

        if (packet instanceof LoginRequestPacket) {
            System.out.println(new Date() + ": 客户端开始登录");

            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            // 校验
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 客户端登录成功");
            } else {
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("用户名密码校验失败");
                System.out.println(new Date() + ": 客户端登录失败");
            }

            ByteBuf response = PacketCodec.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(response);
        } else if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + " 收到客户端消息: " + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复 [" + messageRequestPacket.getMessage() + "]");

            ByteBuf response = PacketCodec.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(response);
        }

    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
