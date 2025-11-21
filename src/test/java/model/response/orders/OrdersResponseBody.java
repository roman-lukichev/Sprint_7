package model.response.orders;

import lombok.Data;

import java.util.List;

@Data
public class OrdersResponseBody {
    private List<Order> orders;
    private PageInfo pageInfo;
    private List<AvailableStation> availableStation;
}
