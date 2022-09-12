package ofo;

import algorithm.bus.RandomUtils;

import java.util.List;

/**
 * 小黄车
 */
public class Ofo {
    //编号
    private String ofoNo;
    //水平坐标
    private String x;
    //垂直坐标
    private String y;
    //地铁站集合
    List<MetroStation> metroStationList;

    public Ofo(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getOfoNo() {
        return ofoNo;
    }

    public void setOfoNo(String ofoNo) {
        this.ofoNo = ofoNo;
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

    public List<MetroStation> getMetroStationList() {
        return metroStationList;
    }

    public void setMetroStationList(List<MetroStation> metroStationList) {
        this.metroStationList = metroStationList;
    }

    /**
     * 小黄车开始被骑行
     */
    public void doRun() {
        for(; ;) {
            //根据当前小黄车的坐标随机移动一格
            rondomMove();
            //每秒移动一次
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断是否到达地铁站
            for (MetroStation metroStation : metroStationList) {
                if (x.equals(metroStation.getX()) && y.equals(metroStation.getY())) {
                    metroStation.getOfoList().add(this);
                    break;
                }
            }
        }
    }

    private void rondomMove() {
        //随机生成1和2  1代表修改x，2代表修改y
        int r = RandomUtils.getRandomData(1, 2);
        if (r == 1) {
            if ("a".equals(x)) {
                x = "b";
            } else if ("h".equals(x)) {
                x = "g";
            } else {
                //随机生成1和2  1代表修改x+1，2代表修改x-1
                int rr = RandomUtils.getRandomData(1, 2);
                int temp = (int) x.toCharArray()[0];
                if (rr == 1) {
                    temp++;
                } else {
                    temp--;
                }
                x = String.valueOf((char) temp);
            }
        } else {
            if ("1".equals(y)) {
                y = "2";
            } else if ("8".equals(y)) {
                y = "7";
            } else {
                //随机生成1和2  1代表修改y+1，2代表修改y-1
                int rr = RandomUtils.getRandomData(1, 2);
                int temp = Integer.parseInt(y);
                if (rr == 1) {
                    temp++;
                } else {
                    temp--;
                }
                y = String.valueOf(temp);
            }
        }

    }
}
