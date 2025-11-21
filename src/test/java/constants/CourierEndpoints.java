package constants;

public class CourierEndpoints {
    public static final String CREATE = "/courier";
    public static final String LOGIN = "/courier/login";
    private static final String DELETE = "/courier/";

    public static String delete(String id) {
        return id == null? DELETE : DELETE + id;
    }

    private CourierEndpoints(){}
}
