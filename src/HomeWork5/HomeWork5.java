package HomeWork5;

public class HomeWork5 {
    public static void main(String[] args) {
        //Запись спортсменов на сдачу нормативов ГТО.
        Animal[] athletes =
                {new Dog("Барбос"), new Horse("Мустанг"), new Cat("Мурзик"), new Bird("Олли")};

        //Начинаем сдавать нормативы. У каждого зверя они свои, иначе лошадям все легко бы давалось.
        for (int i = 0; i < athletes.length; i++) {
            start(athletes[i],athletes[i].averageLimitSwim,athletes[i].averageLimitJump,athletes[i].averageLimitRun);
        }
    }

    private static void start(Animal athlete, float swim, float jump, float run) {
        System.out.println("----------------------");
        System.out.println(athlete.getLimits());
        System.out.println(athlete.getAverageLimits());
        System.out.println(athlete.run(run));
        System.out.println(athlete.jump(jump));
        System.out.println(athlete.swim(swim));
        if (athlete.score>=2) System.out.println(athlete.name+" выполнил из 3 заданий "+athlete.score+"! И получает значок ГТО.");
        else System.out.println(athlete.name+" выполнил из 3 заданий "+athlete.score+"! Он не может получить значок. Попытается в следующий раз");
    }
}
