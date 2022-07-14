package designMode.factory.abstra;

public class FoodFactory implements Factory {
    @Override
    public Food createApple() {
        return new Apple("苹果");
    }

    @Override
    public Food createBread() {
        return new Bread("面包");
    }
}
