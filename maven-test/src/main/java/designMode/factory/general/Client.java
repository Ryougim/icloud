package designMode.factory.general;

public class Client {
    public static void main(String[] args) {
        Factory factory = new AppleFactory();
        Food food = factory.create();

        food.eated();
    }
}
