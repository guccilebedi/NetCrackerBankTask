import java.util.Random;

public enum OperationType {
    GET,
    PUT;

    public static OperationType getRandomOperationType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
