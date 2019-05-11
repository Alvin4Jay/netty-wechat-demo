package the.flash.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.protocol.response.LoginResponsePacket;
import the.flash.session.Session;
import the.flash.util.SessionUtil;

/**
 * 登录响应处理
 *
 * @author <a href="mailto:xuanjian@wacai.com">xuanjian</a>
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String username = loginResponsePacket.getUsername();
        if (loginResponsePacket.isSuccess()) {
            String userId = loginResponsePacket.getUserId();
            System.out.println("[" + username + "]登录成功，userId 为: " + userId);
            SessionUtil.bindSession(new Session(userId, username), ctx.channel());
        } else {
            System.out.println("[" + username + "]登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }

}
