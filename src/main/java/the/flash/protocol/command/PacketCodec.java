package the.flash.protocol.command;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import the.flash.serialize.Serializer;
import the.flash.serialize.impl.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static the.flash.protocol.command.Command.LOGIN_REQUEST;

/**
 * Packet编解码器
 *
 * @author xuanjian
 */
public class PacketCodec {

    private static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends Packet>> PACKET_MAP = new HashMap<>(16);
    private static final Map<Byte, Serializer> SERIALIZER_MAP = new HashMap<>(16);

    static {
        PACKET_MAP.put(LOGIN_REQUEST, LoginRequestPacket.class);

        Serializer serializer = new JsonSerializer();
        SERIALIZER_MAP.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(Packet packet) {

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        byte[] data = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(4);

        byteBuf.skipBytes(1);

        byte serializerAlgorithm = byteBuf.readByte();

        byte command = byteBuf.readByte();

        int dataLen = byteBuf.readInt();

        byte[] data = new byte[dataLen];

        byteBuf.readBytes(data);

        Class<? extends Packet> clazz = getRequestType(command);

        Serializer serializer = getSerializer(serializerAlgorithm);

        if (clazz != null && serializer != null) {
            return serializer.deserialize(clazz, data);
        }
        return null;
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        return SERIALIZER_MAP.get(serializerAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return PACKET_MAP.get(command);
    }

}
