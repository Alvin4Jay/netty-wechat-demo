package the.flash.client.console;

import io.netty.channel.Channel;
import the.flash.protocol.request.GroupMessageRequestPacket;

import java.util.Scanner;

/**
 * Class description here.
 *
 * @author xuanjian
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.print("发送消息给某个群组：");

        String toGroupId = sc.next();
        String message = sc.next();
        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));
    }
}
