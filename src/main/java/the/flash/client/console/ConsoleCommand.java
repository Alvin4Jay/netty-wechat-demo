package the.flash.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Class description here.
 *
 * @author xuanjian
 */
public interface ConsoleCommand {

    void exec(Scanner sc, Channel channel);

}
