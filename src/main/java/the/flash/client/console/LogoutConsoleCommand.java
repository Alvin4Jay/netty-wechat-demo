package the.flash.client.console;

import io.netty.channel.Channel;
import the.flash.protocol.request.LogoutRequestPacket;

import java.util.Scanner;

/**
 * Class description here.
 *
 * @author xuanjian
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        LogoutRequestPacket logoutResponsePacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutResponsePacket);

    }
}
