package the.flash.protocol.response;

import the.flash.protocol.Packet;

import static the.flash.protocol.command.Command.HEART_BEAT_RESPONSE;

/**
 * 心跳响应Packet
 *
 * @author xuanjian
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEART_BEAT_RESPONSE;
    }
}
