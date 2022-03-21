import java.util.Random;

public class Client {
    private final OperationType operationType;
    private final int money;
    private final int serviceTime;

    public Client(int serviceTime) {
        Random random = new Random();
        this.operationType = OperationType.getRandomOperationType();
        this.money = random.nextInt(1000000);
        this.serviceTime = random.nextInt(serviceTime);
    }

    public OperationType getOperationType() {
        return operationType;
    }


    public int getMoney() {
        return money;
    }


    public int getServiceTime() {
        return serviceTime;
    }
}
