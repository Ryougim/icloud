package designMode.factory.abstra;

public class Client {
    public static void main(String[] args) {
        Factory factory = new FoodFactory();
        Food apple = factory.createApple();
        Food bread = factory.createBread();

        apple.eated();
        bread.eated();
    }
}
