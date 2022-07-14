package algorithm.bus;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 汽车站点类
 */
public class BusStation {
    private String stationNo; //站点编号
    private BusStation prevStation; //上一站
    private BusStation nextStation; //下一站
    private List<Person> personList; //当前等车乘客集合

    public BusStation(String stationNo) {
        this.stationNo = stationNo;
        this.personList = new CopyOnWriteArrayList<>();
    }

    public String getStationNo() {
        return stationNo;
    }

    public BusStation getPrevStation() {
        return prevStation;
    }

    public void setPrevStation(BusStation prevStation) {
        this.prevStation = prevStation;
    }

    public BusStation getNextStation() {
        return nextStation;
    }

    public void setNextStation(BusStation nextStation) {
        this.nextStation = nextStation;
    }

    public List<Person> getPersonList() {
        return personList;
    }
}
