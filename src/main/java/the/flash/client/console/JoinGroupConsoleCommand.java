package the.flash.client.console;

import io.netty.channel.Channel;
import the.flash.protocol.request.JoinGroupRequestPacket;

import java.util.Scanner;

/**
 * Join Group Console Command
 *
 * @author xuanjian
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.print("输入 groupId，加入群聊：");

        String groupId = sc.next();

        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();
        joinGroupRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
