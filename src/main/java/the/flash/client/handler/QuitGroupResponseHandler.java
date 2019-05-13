package the.flash.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.protocol.response.QuitGroupResponsePacket;

/**
 * Class description here.
 *
 * @author xuanjian
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        if (quitGroupResponsePacket.isSuccess()) {
            System.out.println("退出群[" + quitGroupResponsePacket.getGroupId() + "]成功!");
        } else {
            System.out.println("退出群[" + quitGroupResponsePacket.getGroupId() + "]失败，原因为" + quitGroupResponsePacket.getReason());
        }
    }
}
