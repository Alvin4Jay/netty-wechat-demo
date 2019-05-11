package the.flash.attribute;

import io.netty.util.AttributeKey;
import the.flash.session.Session;

/**
 * 属性集
 *
 * @author xuanjian
 */
public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
