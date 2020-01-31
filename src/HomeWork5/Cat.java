package HomeWork5;

public class Cat extends Animal {
    // бег=200;
    // плавание=0;
    // прыжок=2f;
    Cat(String name) {
        super(name, 0, 2f, 200);
        type = "Кошка";
    }

    @Override
    protected String swim(float length) {
        return name + " не умеет плавать и даже близко к воде не подходит!";
    }
}
