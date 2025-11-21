package model.request.courier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CreateCourierRequest {
    private String login;
    private String password;
    private String firstName;
}
