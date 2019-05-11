package the.flash.protocol.response;

import lombok.Data;
import the.flash.protocol.Packet;

import java.util.List;

import static the.flash.protocol.command.Command.CREATE_GROUP__RESPONSE;

/**
 * Class description here.
 *
 * @author xuanjian
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private String groupId;

    private List<String> usernameList;

    private boolean success;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP__RESPONSE;
    }
}
