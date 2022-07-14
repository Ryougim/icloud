package designMode.factory.simple;

public class FoodFactory {
    private Food food;

    public FoodFactory(String type) {
        if ("1".equals(type)) {
            food = new Apple("苹果");
        } else {
            throw new RuntimeException("工厂不生产此种类型的食物");
        }
    }

    public Food getFoodInstance() {
        return food;
    }
}
