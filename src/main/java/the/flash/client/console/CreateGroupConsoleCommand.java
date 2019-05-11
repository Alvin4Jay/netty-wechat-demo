package the.flash.client.console;

import io.netty.channel.Channel;
import the.flash.protocol.request.CreateGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Class description here.
 *
 * @author xuanjian
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    public static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");

        String userIds = sc.next();
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));

        channel.writeAndFlush(createGroupRequestPacket);
    }
}
