package algorithm.bus;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Client {
    //门栓，控制主线程在10个公交车线程执行完之后打印输出
    private static CountDownLatch latch = new CountDownLatch(10);

    public static void main(String[] args) {
        //生成15个站点
        List<BusStation> stations = getStations(15);
        //生产乘客线程启动
        PersonProducer personProducer = new PersonProducer(stations);
        new Thread(personProducer, "乘客生产线程").start();
        //创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 50, 300, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50),
                r -> new Thread(r, "thread_by_poll_" + r.hashCode()),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        //生成10辆电动公交车，分别从两个终点站开始运行
        BusFactory busFactory = new BusFactory();
        for (int i = 65, j = 70; i < 70 && j < 75; i++, j++) {
            char c1 = (char) i; //向上行驶车辆编号为A,B,C,D,E
            char c2 = (char) j; //向下行驶车辆编号为F,G,H,I,J
            //创建公交车
            Bus busUP = busFactory.buider(String.valueOf(c1), stations, Direction.UP);
            Bus busDOWN = busFactory.buider(String.valueOf(c2), stations, Direction.DOWN);
            //每隔15分钟两个方向各发一辆车
            threadPoolExecutor.submit(() -> {
                busUP.doRun();
                latch.countDown();
            });
            threadPoolExecutor.submit(() -> {
                busDOWN.doRun();
                latch.countDown();
            });
            //睡眠15分钟（最后一次循环不用睡）
            if (i < 69) {
                try {
                    Thread.sleep(3 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //主线程等待子线程执行完再输出
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出每辆公交车的运行状况
        System.out.println("公交车(NAME)   |   总载客人数   |   总运行时间(分钟)   |   总行驶时间(分钟)");
        for (Bus bus : busFactory.getBuss()) {
            //总运行时间
            Long runTime = bus.getStartTime().until(bus.getEndTime(), ChronoUnit.MINUTES);
            System.out.print(bus.getBusName() + "   |   " + bus.getTotalPersonNum() + "   |   " + runTime + "   |   " + bus.getDriveTime());
            System.out.println();
        }
        //输出任意2量公交车的运行明细
        for (Bus bus : busFactory.getBuss()) {
            System.out.println("公交车" + bus.getBusName());
            System.out.println("时间   |   动作");
            for (DriveAction driveAction : bus.getDriveActionList()) {
                System.out.println(driveAction.getActionTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "   |   " + driveAction.getActionDesc());
            }
        }
    }

    private static List<BusStation> getStations(int num) {
        List<BusStation> stations = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            BusStation station = new BusStation(String.valueOf(i));
            stations.add(station);
        }
        for (int i = 0; i < stations.size(); i++) {
            if (i > 0) {
                stations.get(i).setPrevStation(stations.get(i-1));
            }
            if (i < stations.size() - 1) {
                stations.get(i).setNextStation(stations.get(i+1));
            }
        }
        return stations;
    }
}
