package ofo;

/**
 * 生成随机数工具类
 */
public class RandomUtils {
    public static int getRandomData(int from, int to) {
        return (int) (Math.random() * (to + 1 - from) + from);
    }

    public static int getRandomData(int from, int to, int exclude) {
        int random = (int) (Math.random() * (to + 1 - from) + from);
        return random == exclude ? (random + 1 > to ? random - 1 : random + 1) : random;
    }
}
