package the.flash.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.flash.protocol.request.HeartBeatRequestPacket;

import java.util.concurrent.TimeUnit;

/**
 * 客户端定时发送心跳
 *
 * @author xuanjian
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {
    private static final int HEART_BEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        sendHeartBeatPacket(ctx);

        super.channelActive(ctx);
    }

    private void sendHeartBeatPacket(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                sendHeartBeatPacket(ctx);
            }
        }, HEART_BEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
