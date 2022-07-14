package designMode.factory.general;

public class AppleFactory implements Factory {
    @Override
    public Food create() {
        return new Apple("苹果");
    }
}
