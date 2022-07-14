package designMode.agent.staicAgent;

public class ProxyPoint implements SellTickets {
    TrainStation trainStation = new TrainStation();

    @Override
    public void sell() {
        System.out.println("代理点收取服务费");
        trainStation.sell();
    }
}
