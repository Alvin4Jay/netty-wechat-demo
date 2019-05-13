package the.flash.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import the.flash.protocol.request.GroupMessageRequestPacket;
import the.flash.protocol.response.GroupMessageResponsePacket;
import the.flash.util.SessionUtil;

/**
 * Class description here.
 *
 * @author xuanjian
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {
        // 1.拿到 groupId 构造群聊消息的响应
        String groupId = requestPacket.getToGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));
        responsePacket.setMessage(requestPacket.getMessage());

        // 2. 拿到群聊对应的 channelGroup，写到每个客户端
        channelGroup.writeAndFlush(responsePacket);
    }
}
