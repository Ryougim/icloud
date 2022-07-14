package algorithm.bus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公共汽车类
 */
public class Bus {
    private String busName; //编号
    private BusStation station; //汽车当前所在站点
    private Direction direction; //汽车行驶方向
    private int maxPersonNum; //核载人数
    private int totalPersonNum; //总载客人数
    private LocalDateTime startTime; //发车开始时间
    private LocalDateTime endTime; //停止时间
    private int driveTime; //总共行驶时间（分）
    private List<Person> personList; //当前载客集合
    private List<DriveAction> driveActionList; //行车动作记录


    public Bus(String busName, BusStation station, Direction direction) {
        this.busName = busName;
        this.station = station;
        this.direction = direction;
        this.maxPersonNum = 29;
        this.totalPersonNum = 0;
        this.driveTime = 0;
        this.personList = new ArrayList<>();
        this.driveActionList = new ArrayList<>();
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public BusStation getStation() {
        return station;
    }

    public void setStation(BusStation station) {
        this.station = station;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getMaxPersonNum() {
        return maxPersonNum;
    }

    public void setMaxPersonNum(int maxPersonNum) {
        this.maxPersonNum = maxPersonNum;
    }

    public int getTotalPersonNum() {
        return totalPersonNum;
    }

    public void setTotalPersonNum(int totalPersonNum) {
        this.totalPersonNum = totalPersonNum;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getDriveTime() {
        return driveTime;
    }

    public void setDriveTime(int driveTime) {
        this.driveTime = driveTime;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<DriveAction> getDriveActionList() {
        return driveActionList;
    }

    public void setDriveActionList(List<DriveAction> driveActionList) {
        this.driveActionList = driveActionList;
    }

    /**
     * 公交车启动
     */
    public void doRun() {
        this.startTime = LocalDateTime.now();
        int runTime = 10; //汽车运行时间：300分钟
        while (this.startTime.until(LocalDateTime.now(), ChronoUnit.MINUTES) < runTime) {
            int downPerson = 0; //下车乘客数量
            int upPerson = 0; //上车乘客数量

            //当前车辆有无乘客下车
            Iterator<Person> it1 = this.personList.iterator();
            while (it1.hasNext()) {
                Person person = it1.next();
                if (person.getTargetStation().getStationNo().equals(this.station.getStationNo())) {
                    //乘客目标站点与当前站点一致  下车
                    try {
                        Thread.sleep(10000); //下车要10s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    it1.remove();
                    downPerson++;
                }
            }

            //公交车有十分之一的几率会出现故障（假设1为故障）
            if (RandomUtils.getRandomData(1, 10) == 1) {
                //公交车发生故障，车上剩下的乘客都下车，并设置上车优先
                for (Person person : this.personList) {
                    person.setPriority(true);
                }
                this.station.getPersonList().addAll(this.personList);
                this.personList.clear();
                this.driveActionList.add(new DriveAction("公交车故障，车上的" + (downPerson + this.personList.size()) + "名乘客全部下车"));
                this.endTime = LocalDateTime.now();
                break;
            }

            //当前站点有无上车乘客
            //先遍历优先上车的乘客
            Iterator<Person> it2 = this.station.getPersonList().stream().filter(p -> p.getPriority() == true).collect(Collectors.toList()).iterator();
            inner2:
            while (it2.hasNext()) {
                //判断是否满客
                if (this.personList.size() >= this.maxPersonNum) {
                    break inner2;
                }
                Person person = it2.next();
                if (person.getDirection() == this.direction) {
                    //乘客要去的方向跟汽车行驶方向一致  上车
                    try {
                        Thread.sleep(10000); //上车要10s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.station.getPersonList().remove(person);
                    this.personList.add(person);
                    upPerson++;
                }
            }
            //再遍历站台剩下的乘客
            Iterator<Person> it3 = this.station.getPersonList().iterator();
            inner3:
            while (it3.hasNext()) {
                //判断是否满客
                if (this.personList.size() >= this.maxPersonNum) {
                    break inner3;
                }
                Person person = it3.next();
                if (person.getDirection() == this.direction) {
                    //乘客要去的方向跟汽车行驶方向一致  上车
                    try {
                        Thread.sleep(10000); //上车要10s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.station.getPersonList().remove(person);
                    this.personList.add(person);
                    upPerson++;
                }
            }
            this.totalPersonNum += upPerson;

            //记录行车动作
            if ((this.direction == Direction.UP && this.station.getPrevStation() == null)
                    || (this.direction == Direction.DOWN && this.station.getNextStation() == null)) {
                //始发站
                this.driveActionList.add(new DriveAction("从" + this.station.getStationNo() + "站发车，乘客" + this.personList.size() + "人"));
            } else {
                //记录上下客人数
                this.driveActionList.add(new DriveAction("下客" + downPerson + "人，上客" + upPerson + "人，继续出发"));
            }

            //准备驶向下一个站点
            int time = RandomUtils.getRandomData(1, 2); //生成3-8之间的随机数（到下一站的行驶时间）
            try {
                Thread.sleep(time * 60 * 1000); //行驶到下一站的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.driveTime += time;

            //到达下一站
            if (this.direction == Direction.UP) {
                if (this.station.getNextStation() != null) {
                    this.station = this.station.getNextStation();
                } else {
                    this.direction = Direction.DOWN; //到达终点站变换方向，立即向另一个终点站行驶
                    this.station = this.station.getPrevStation();
                }
            } else {
                if (this.station.getPrevStation() != null) {
                    this.station = this.station.getPrevStation();
                } else {
                    this.direction = Direction.UP; //到达终点站变换方向，立即向另一个终点站行驶
                    this.station = this.station.getNextStation();
                }
            }

            //记录行车动作
            if ((this.direction == Direction.UP && this.station.getNextStation() == null)
                    || (this.direction == Direction.DOWN && this.station.getPrevStation() == null)) {
                //终点站
                this.driveActionList.add(new DriveAction("抵达终点站" + this.station.getStationNo()));
            } else {
                //记录到达站点
                this.driveActionList.add(new DriveAction("到达" + this.station.getStationNo() + "站"));
            }
        }
        this.endTime = LocalDateTime.now();
    }
}
