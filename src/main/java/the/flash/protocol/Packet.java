package the.flash.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Packet
 *
 * @author xuanjian
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    @JSONField(serialize = false, deserialize = false)
    private Byte version = 1;

    /**
     * 命令类型
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();

}
