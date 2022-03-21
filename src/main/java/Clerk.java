import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Clerk extends Thread {
    private final int id;
    private final Cashier cashier;
    private final BlockingQueue<Client> clients = new LinkedBlockingQueue<>();

    public Clerk(int id, Cashier cashier) {
        this.id = id;
        this.cashier = cashier;
    }

    public void addClient(Client client) {
        synchronized (clients) {
            clients.add(client);
            System.out.println("New client is in the queue to clerk " + this.id + ". Total length: " + this.getQueueLength() + ".");
            clients.notify();
        }
    }

    public int getQueueLength() {
        return clients.size();
    }

    /**
     * Serves client. If he puts money, there will be no checking.
     * Only if the cashier has enough money, the client can get it. Otherwise, he will get a refusal.
     */
    public void serveClient(Client client) {
        if (client.getOperationType() == OperationType.PUT) {
            cashier.putMoney(client.getMoney());
            System.out.println("Client was served by clerk " + this.id + " for " + client.getServiceTime() + "ms and put " + client.getMoney() + "$.");
        } else {
            if (cashier.getBalance() > client.getMoney()) {
                cashier.getMoney(client.getMoney());
                System.out.println("Client was served by clerk " + this.id + " for " + client.getServiceTime() + "ms and get " + client.getMoney() + "$.");
            } else {
                System.out.println("Client was served by clerk " + this.id + " for " + client.getServiceTime() + "ms, but there were not enough money to get.");
            }
        }
    }

    /**
     * Waits until the client appears in the queue and serves him.
     */
    @Override
    public void run() {
        while (true) {
            try {
                synchronized (clients) {
                    if (clients.isEmpty()) {
                        clients.wait();
                    }
                }
                Client client = clients.poll();
                assert client != null;
                System.out.println("Client is being served by clerk " + this.id + ".");
                Thread.sleep(client.getServiceTime());
                serveClient(client);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
