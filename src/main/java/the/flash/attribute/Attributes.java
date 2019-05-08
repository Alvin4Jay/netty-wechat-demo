package the.flash.attribute;

import io.netty.util.AttributeKey;

/**
 * 属性集
 *
 * @author xuanjian
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

}
