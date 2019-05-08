package the.flash.serialize.impl;

import com.alibaba.fastjson.JSON;
import the.flash.serialize.SerializeAlgorithm;
import the.flash.serialize.Serializer;

/**
 * JSON 序列化
 *
 * @author xuanjian
 */
public class JsonSerializer implements Serializer {
    @Override
    public Byte getSerializerAlgorithm() {
        return SerializeAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data) {
        return JSON.parseObject(data, clazz);
    }
}
