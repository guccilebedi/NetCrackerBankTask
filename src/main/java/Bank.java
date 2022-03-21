public class Bank {
    private final Clerk[] clerks;
    private static Cashier cashier = null;
    private int SERVICE_TIME;
    private int CLIENTS_PER_MINUTE;

    public Bank(int N, int CASHIER_BALANCE, int SERVICE_TIME, int CLIENTS_PER_MINUTE) {
        this.clerks = new Clerk[N];
        cashier = new Cashier(CASHIER_BALANCE);
        this.SERVICE_TIME = SERVICE_TIME;
        this.CLIENTS_PER_MINUTE = CLIENTS_PER_MINUTE;
    }

    /**
     * Starts all clerk threads and a clients generator.
     */
    public void work(){
        for (int i = 0; i < clerks.length; i++) {
            clerks[i] = new Clerk(i + 1, cashier);
            clerks[i].start();
        }
        ClientsGenerator clientsGenerator = new ClientsGenerator(SERVICE_TIME, CLIENTS_PER_MINUTE, this);
        clientsGenerator.start();
    }

    /**
     * Chooses the smallest queue and adds a new client to it.
     */
    public void addClient(Client client){
        Clerk minQueueLength = clerks[0];
        for (Clerk clerk : clerks) {
            if(clerk.getQueueLength() < minQueueLength.getQueueLength()){
                minQueueLength = clerk;
            }
        }
        minQueueLength.addClient(client);
    }
}
