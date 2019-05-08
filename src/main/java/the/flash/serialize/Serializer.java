package the.flash.serialize;

import the.flash.serialize.impl.JsonSerializer;

/**
 * 序列化器
 *
 * @author xuanjian
 */
public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    Byte getSerializerAlgorithm();

    byte[] serialize(Object obj);

    <T> T deserialize(Class<T> clazz, byte[] data);

}
