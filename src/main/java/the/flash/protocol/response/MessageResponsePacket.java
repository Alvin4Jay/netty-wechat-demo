package the.flash.protocol.response;

import lombok.Data;
import the.flash.protocol.Packet;

import static the.flash.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * 消息响应
 *
 * @author xuanjian
 */
@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUsername;

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
