package HomeWork5;

public class Bird extends Animal {
    // бег=5;
    // плавание=0;
    // прыжок=0.2f;

    Bird(String name) {
        super(name, 0, 0.2f, 5);
        type = "Птица";
    }

    @Override
    protected String swim(float length) {
        return name + " не умеет плавать и перелетел водную преграду! Это нарушение правил и очко не засчитывается!";
    }
}

