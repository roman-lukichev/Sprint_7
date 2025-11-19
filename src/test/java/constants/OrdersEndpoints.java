package constants;

public class OrdersEndpoints {
    public static final String CREATE = "/orders";
    public static final String GET_ORDERS = "/orders";
    public static final String GET_ORDERS_BY_TRACK = "/orders/track";
    private static final String ACCEPT_ORDER = "/orders/accept/";

    public static String acceptOrder(Integer orderId) {
        return orderId == null ? ACCEPT_ORDER : ACCEPT_ORDER + orderId;
    }

}
