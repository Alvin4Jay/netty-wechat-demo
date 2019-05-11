package the.flash.client.console;

import io.netty.channel.Channel;
import the.flash.protocol.request.MessageRequestPacket;

import java.util.Scanner;

/**
 * Class description here.
 *
 * @author xuanjian
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.print("发送消息给某个用户：");
        String toUserId = sc.next();
        String message = sc.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
