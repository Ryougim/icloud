package designMode.factory.abstra;

public class FoodFactory implements Factory {
    @Override
    public Food createApple() {
        return new Apple("čšć");
    }

    @Override
    public Food createBread() {
        return new Bread("é˘ĺ");
    }
}
