package designMode.factory.abstra;

public class Apple extends Food {
    public Apple(String name) {
        this.name = name;
    }

    @Override
    public void eated() {
        System.out.println(name + "被吃了");
    }
}
