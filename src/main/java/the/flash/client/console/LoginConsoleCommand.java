package the.flash.client.console;

import io.netty.channel.Channel;
import the.flash.protocol.request.LoginRequestPacket;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Class description here.
 *
 * @author xuanjian
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.print("请输入用户名登录: ");
        String username = sc.nextLine();

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername(username);
        loginRequestPacket.setPassword("pwd");

        channel.writeAndFlush(loginRequestPacket);

        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
