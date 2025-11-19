package model.request.orders;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CreateOrderRequest {
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;
}
