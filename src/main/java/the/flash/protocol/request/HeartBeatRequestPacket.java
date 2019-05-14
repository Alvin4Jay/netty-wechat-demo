package the.flash.protocol.request;

import the.flash.protocol.Packet;

import static the.flash.protocol.command.Command.HEART_BEAT_REQUEST;

/**
 * 心跳请求Packet
 *
 * @author xuanjian
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEART_BEAT_REQUEST;
    }
}
