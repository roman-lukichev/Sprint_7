package model.request.courier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class LoginCourierRequest {
    private String login;
    private String password;
}
