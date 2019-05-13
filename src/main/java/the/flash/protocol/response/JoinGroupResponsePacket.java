package the.flash.protocol.response;

import lombok.Data;
import the.flash.protocol.Packet;

import static the.flash.protocol.command.Command.JOIN_GROUP__RESPONSE;

/**
 * Join Group Response
 *
 * @author xuanjian
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP__RESPONSE;
    }
}
