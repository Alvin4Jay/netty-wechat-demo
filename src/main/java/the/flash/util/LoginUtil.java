package the.flash.util;

import io.netty.channel.Channel;
import the.flash.attribute.Attributes;

/**
 * 客户端Channel登录状态判断工具
 *
 * @author xuanjian
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        return channel.attr(Attributes.LOGIN).get() != null;
    }

}
