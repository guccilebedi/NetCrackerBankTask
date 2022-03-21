public class ClientsGenerator extends Thread {
    private final int SERVICE_TIME;
    private final int CLIENTS_PER_MINUTE;
    private final Bank bank;

    public ClientsGenerator(int SERVICE_TIME, int CLIENTS_PER_MINUTE, Bank bank) {
        this.SERVICE_TIME = SERVICE_TIME;
        this.CLIENTS_PER_MINUTE = CLIENTS_PER_MINUTE;
        this.bank = bank;
    }

    /**
     * Generates clients each (60000 / CLIENTS_PER_MINUTE) ms and adds them to the bank queues.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60000 / CLIENTS_PER_MINUTE);
                Client client = new Client(SERVICE_TIME);
                bank.addClient(client);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
