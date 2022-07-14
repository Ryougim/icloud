package designMode.agent.JDKAgent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    TrainStation trainStation = new TrainStation();

    public SellTickets getProxyObject() {
        SellTickets proxyObject = (SellTickets) Proxy.newProxyInstance(
                trainStation.getClass().getClassLoader(),
                trainStation.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理点收取服务费");
                        Object obj = method.invoke(trainStation, args);
                        return obj;
                    }
                });
        return proxyObject;
    }
}
