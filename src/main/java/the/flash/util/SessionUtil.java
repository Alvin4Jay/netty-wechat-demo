package the.flash.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import the.flash.attribute.Attributes;
import the.flash.session.Session;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Session Util
 *
 * @author xuanjian
 */
public class SessionUtil {

    private static final ConcurrentMap<String, Channel> USER_ID_CHANNEL_MAP = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        USER_ID_CHANNEL_MAP.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel) {
        if (hasLogin(channel)) {
            USER_ID_CHANNEL_MAP.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Session> attribute = channel.attr(Attributes.SESSION);
        return attribute.get() != null;
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return USER_ID_CHANNEL_MAP.get(userId);
    }

}
