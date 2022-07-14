package designMode.factory.simple;

public class Client {
    public static void main(String[] args) {
        FoodFactory foodFactory = new FoodFactory("1");
        Food food = foodFactory.getFoodInstance();

        food.eated();
    }
}
