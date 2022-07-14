package designMode.agent.JDKAgent;

public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        SellTickets sellTickets = proxyFactory.getProxyObject();
        sellTickets.sell();
    }
}
