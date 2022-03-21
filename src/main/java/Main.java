public class Main {

    public static void main(String[] args) {
        int N = 3;
        int SERVICE_TIME = 10000;
        int CLIENTS_PER_MINUTE = 100;
        int CASHIER_BALANCE = 500000;
        Bank bank = new Bank(N, CASHIER_BALANCE, SERVICE_TIME, CLIENTS_PER_MINUTE);
        bank.start();
    }
}
