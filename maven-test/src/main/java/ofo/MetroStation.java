package ofo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 地铁站
 */
public class MetroStation {
    //编号
    private String stationNo;
    //水平坐标
    private String x;
    //垂直坐标
    private String y;
    //小黄车集合
    private List<Ofo> ofoList = new CopyOnWriteArrayList<>();

    public MetroStation(String stationNo, String x, String y) {
        this.stationNo = stationNo;
        this.x = x;
        this.y = y;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public List<Ofo> getOfoList() {
        return ofoList;
    }

    public void setOfoList(List<Ofo> ofoList) {
        this.ofoList = ofoList;
    }
}
