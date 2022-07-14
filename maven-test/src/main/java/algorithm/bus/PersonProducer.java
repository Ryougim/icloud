package algorithm.bus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 乘客生产者（每5分钟生成10名乘客，所在站点和目标站点随机生成）
 */
public class PersonProducer implements Runnable {
    private List<BusStation> stations;

    public PersonProducer(List<BusStation> stations) {
        this.stations = stations;
    }

    @Override
    public void run() {
        LocalDateTime startTime = LocalDateTime.now();
        int runTime = 20;
        while(startTime.until(LocalDateTime.now(), ChronoUnit.MINUTES) < runTime) {
            //随机生成10名乘客
            for (int i = 0; i < 10; i++) {
                //乘客所在站点随机数
                int localRandom = RandomUtils.getRandomData(1, this.stations.size());
                BusStation localStation = this.stations.stream().filter(s -> String.valueOf(localRandom).equals(s.getStationNo())).findFirst().get();
                //乘客目标站点随机数（排除生成的localStation）
                int targetRandom = RandomUtils.getRandomData(1, this.stations.size(), localRandom);
                BusStation targetStation = this.stations.stream().filter(s -> String.valueOf(targetRandom).equals(s.getStationNo())).findFirst().get();
                //生成乘客
                Person person = new Person();
                person.setDirection(localRandom < targetRandom ? Direction.UP : Direction.DOWN);
                person.setLocalStation(localStation);
                person.setTargetStation(targetStation);
                //向当前站点添加乘客
                localStation.getPersonList().add(person);
            }
            //睡眠5分钟
            try {
                Thread.sleep(1 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
