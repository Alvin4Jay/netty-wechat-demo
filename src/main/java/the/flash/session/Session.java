package the.flash.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Session用户信息
 *
 * @author xuanjian
 */
@Data
@NoArgsConstructor
public class Session {

    private String userId;

    private String username;

    public Session(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
