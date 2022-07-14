package designMode.factory.abstra;

public class Bread extends Food {
    public Bread(String name) {
        this.name = name;
    }

    @Override
    public void eated() {
        System.out.println(name + "被吃了");
    }
}
