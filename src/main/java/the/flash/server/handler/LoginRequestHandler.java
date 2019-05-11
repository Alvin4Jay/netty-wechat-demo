package the.flash.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.protocol.request.LoginRequestPacket;
import the.flash.protocol.response.LoginResponsePacket;
import the.flash.session.Session;
import the.flash.util.SessionUtil;

import java.util.Date;
import java.util.UUID;

/**
 * 登录请求处理
 *
 * @author <a href="mailto:xuanjian@wacai.com">xuanjian</a>
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        String username = loginRequestPacket.getUsername();
        loginResponsePacket.setUsername(username);
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());

        // 校验
        if (valid(loginRequestPacket)) {
            String userId = randomUserId();
            SessionUtil.bindSession(new Session(userId, username), ctx.channel());

            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + username + "]登录成功");
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("用户名密码校验失败");
            System.out.println(new Date() + ": 登录失败!");
        }
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
    }
}
