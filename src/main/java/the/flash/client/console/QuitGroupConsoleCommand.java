package the.flash.client.console;

import io.netty.channel.Channel;
import the.flash.protocol.request.QuitGroupRequestPacket;

import java.util.Scanner;

/**
 * Class description here.
 *
 * @author xuanjian
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.print("输入 groupId，退出群聊：");

        String groupId = sc.next();

        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
