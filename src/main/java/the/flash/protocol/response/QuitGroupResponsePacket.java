package the.flash.protocol.response;

import lombok.Data;
import the.flash.protocol.Packet;

import static the.flash.protocol.command.Command.QUIT_GROUP__RESPONSE;

/**
 * Class description here.
 *
 * @author xuanjian
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private String reason;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP__RESPONSE;
    }
}
