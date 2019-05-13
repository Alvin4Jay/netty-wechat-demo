package the.flash.client.console;

import io.netty.channel.Channel;
import the.flash.protocol.request.ListGroupMembersRequestPacket;

import java.util.Scanner;

/**
 * Class description here.
 *
 * @author xuanjian
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {

        System.out.print("输入 groupId，获取群成员列表：");

        String groupId = sc.next();

        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();
        listGroupMembersRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(listGroupMembersRequestPacket);

    }
}
