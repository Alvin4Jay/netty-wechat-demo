package the.flash.util;

import java.util.UUID;

/**
 * ID Generator
 *
 * @author xuanjian
 */
public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
