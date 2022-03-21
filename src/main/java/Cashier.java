public class Cashier {
    private volatile int balance;

    public Cashier(int CASHIER_BALANCE) {
        this.balance = CASHIER_BALANCE;
    }

    public synchronized void putMoney(int money) {
        this.balance += money;
        System.out.println("Cashier balance: " + this.balance + "$.");
    }

    public synchronized void getMoney(int money){
        this.balance -= money;
        System.out.println("Cashier balance: " + this.balance + "$.");
    }

    public int getBalance() {
        return this.balance;
    }
}
