package the.flash.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.protocol.request.LoginRequestPacket;
import the.flash.protocol.response.LoginResponsePacket;
import the.flash.session.Session;
import the.flash.util.IDUtil;
import the.flash.util.SessionUtil;

import java.util.Date;

/**
 * 登录请求处理
 *
 * @author <a href="mailto:xuanjian@wacai.com">xuanjian</a>
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        String username = loginRequestPacket.getUsername();
        loginResponsePacket.setUsername(username);
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());

        // 校验
        if (valid(loginRequestPacket)) {
            String userId = IDUtil.randomId();
            SessionUtil.bindSession(new Session(userId, username), ctx.channel());

            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + username + "]登录成功");
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("用户名密码校验失败");
            System.out.println(new Date() + ": 登录失败!");
        }
        ctx.writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
    }
}
