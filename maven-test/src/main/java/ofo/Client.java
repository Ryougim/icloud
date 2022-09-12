package ofo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 启动类
 */
public class Client {
    //ofo总数量
    private static int ofoNum = 100;

    public static void main(String[] args) {
        //创建3个地铁站
        MetroStation metroStationA = new MetroStation("A", "a", "1");
        MetroStation metroStationB = new MetroStation("B", "h", "8");
        MetroStation metroStationC = new MetroStation("C", "d", "3");
        List<MetroStation> metroStationList = new ArrayList<>();
        metroStationList.add(metroStationA);
        metroStationList.add(metroStationB);
        metroStationList.add(metroStationC);

        //生成小黄车
        for (int i = 1; i <= 30; i++) {
            Ofo ofo = new Ofo(metroStationA.getX(), metroStationA.getY());
            ofo.setMetroStationList(metroStationList);
            metroStationA.getOfoList().add(ofo);
        }
        for (int i = 1; i <= 40; i++) {
            Ofo ofo = new Ofo(metroStationB.getX(), metroStationA.getY());
            ofo.setMetroStationList(metroStationList);
            metroStationB.getOfoList().add(ofo);
        }
        for (int i = 1; i <= 30; i++) {
            Ofo ofo = new Ofo(metroStationC.getX(), metroStationA.getY());
            ofo.setMetroStationList(metroStationList);
            metroStationC.getOfoList().add(ofo);
        }

        long begin = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            while (System.currentTimeMillis() - begin <= (200 * 1000)) {
                int r = RandomUtils.getRandomData(1, 3);
                executorService.submit(() -> {
                    if (r == 1) {
                        Ofo ofo = metroStationA.getOfoList().get(0);
                        if (ofo != null) {
                            //小黄车开始运行
                            metroStationA.getOfoList().remove(ofo);
                            ofo.doRun();
                        }
                    } else if (r == 2) {
                        Ofo ofo = metroStationB.getOfoList().get(0);
                        if (ofo != null) {
                            //小黄车开始运行
                            metroStationB.getOfoList().remove(ofo);
                            ofo.doRun();
                        }
                    } else {
                        Ofo ofo = metroStationC.getOfoList().get(0);
                        if (ofo != null) {
                            //小黄车开始运行
                            metroStationC.getOfoList().remove(ofo);
                            ofo.doRun();
                        }
                    }
                });
                //每秒生成一个乘客
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //另起一个线程每秒输出地铁站和小黄车的状态
        executorService.submit(() -> {
            while (System.currentTimeMillis() - begin <= (200 * 1000)) {
                String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                int numA = metroStationA.getOfoList().size();
                int numB = metroStationB.getOfoList().size();
                int numC = metroStationC.getOfoList().size();
                int numRun = ofoNum - numA - numB - numC;
                System.out.println(now + "：A站车" + numA + "，B站车" + numB+ "，C站车" + numC+ "，路上车" + numRun);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
