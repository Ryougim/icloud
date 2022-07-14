package algorithm.bus;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共汽工厂类
 */
public class BusFactory {
    private List<Bus> buss = new ArrayList<>();

    public List<Bus> getBuss() {
        return buss;
    }

    public Bus buider(String busName, List<BusStation> stations, Direction direction) {
        BusStation station = null;
        if (direction == Direction.UP) {
            station = stations.get(0);
        } else {
            station = stations.get(stations.size() - 1);
        }
        Bus bus = new Bus(busName, station, direction);
        buss.add(bus);
        return bus;
    }
}
