package the.flash.protocol;

import io.netty.buffer.ByteBuf;
import the.flash.protocol.request.*;
import the.flash.protocol.response.*;
import the.flash.serialize.Serializer;
import the.flash.serialize.impl.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static the.flash.protocol.command.Command.*;

/**
 * Packet编解码器
 *
 * @author xuanjian
 */
public class PacketCodec {

    public static final PacketCodec INSTANCE = new PacketCodec();
    public static final int MAGIC_NUMBER = 0x12345678;

    private final Map<Byte, Class<? extends Packet>> PACKET_MAP = new HashMap<>(16);
    private final Map<Byte, Serializer> SERIALIZER_MAP = new HashMap<>(16);

    private PacketCodec() {
        PACKET_MAP.put(LOGIN_REQUEST, LoginRequestPacket.class);
        PACKET_MAP.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        PACKET_MAP.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        PACKET_MAP.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        PACKET_MAP.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        PACKET_MAP.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
        PACKET_MAP.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        PACKET_MAP.put(CREATE_GROUP__RESPONSE, CreateGroupResponsePacket.class);
        PACKET_MAP.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        PACKET_MAP.put(JOIN_GROUP__RESPONSE, JoinGroupResponsePacket.class);
        PACKET_MAP.put(QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        PACKET_MAP.put(QUIT_GROUP__RESPONSE, QuitGroupResponsePacket.class);
        PACKET_MAP.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        PACKET_MAP.put(LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        PACKET_MAP.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        PACKET_MAP.put(GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);
        PACKET_MAP.put(HEART_BEAT_REQUEST, HeartBeatRequestPacket.class);
        PACKET_MAP.put(HEART_BEAT_RESPONSE, HeartBeatResponsePacket.class);

        Serializer serializer = new JsonSerializer();
        SERIALIZER_MAP.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
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
