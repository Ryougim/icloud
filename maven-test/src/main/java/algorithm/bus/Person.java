package algorithm.bus;

/**
 * 乘客类
 */
public class Person {
    private Direction direction; //乘客要去的方向
    private BusStation localStation; //乘客所在站点
    private BusStation targetStation; //目标站点
    private Boolean isPriority = false; //是否优先，默认false，故障车下来的乘客标记为true

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public BusStation getLocalStation() {
        return localStation;
    }

    public void setLocalStation(BusStation localStation) {
        this.localStation = localStation;
    }

    public BusStation getTargetStation() {
        return targetStation;
    }

    public void setTargetStation(BusStation targetStation) {
        this.targetStation = targetStation;
    }

    public Boolean getPriority() {
        return isPriority;
    }

    public void setPriority(Boolean priority) {
        isPriority = priority;
    }
}
