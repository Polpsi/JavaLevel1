package HomeWork4;

public class HomeWork4 {
    public static void main(String[] args) {

        //Так-то спасателям 04/03/2020 будет всего 31 год, но немного приврём про их возраст.
        Rescuer chip = new Rescuer("Чип", 45, "лидер(бурундук)", 112, 45000);
        //Задание 4
        System.out.printf("Имя: %s, должность: %s\n\n", chip.getName(), chip.getPosition());

        //Задание 5
        Rescuer[] staff = new Rescuer[5];
        staff[0] = chip;
        staff[1] = new Rescuer("Дейл", 44, "разнорабочий(бурундук)", 113, 30000);
        staff[2] = new Rescuer("Гайка", 35, "инженер(мышь)", 114, 40000);
        staff[3] = new Rescuer("Рокфор", 55, "повар(австралийская мышь)", 115, 35000);
        staff[4] = new Rescuer("Вжик", 30, "разведчик(зеленая муха)", 116, 30000);

        //Список всех спасателей для сравнения с результатами заданий.
        System.out.println("Штатное расписание команды спасателей:");
        printInfo(staff);

        //Продолжение Задания 5
        System.out.println("\nСписок спасателей старше 40 лет:");
        for (int i = 0; i < staff.length; i++) {
            if (staff[i].getAge() >= 40) printInfo(staff[i]); //если 40 исполнилось, то сотрудник уже старше сорока.
        }

        //Задание 6. Увеличение зп спасателям старше 45-ти лет.
        //Доп. параметры - вдруг Чип захочет c другим возрастом и на другое кол-во орехов увеличить зп.
        System.out.println("\nЗарплата увеличена сотрудникам:");
        salaryUp(staff, 45, 5000);

        //Задание 7. Вычисление среднего арифметического
        System.out.println("\nСредний арифметический возраст спасателей (лет) - " + meanArithmetic(staff, "Age"));
        System.out.println("Средняя арифметическая зарплата спасателей после повышения (орехов) - " + meanArithmetic(staff, "Salary"));
    }

    private static void printInfo(Rescuer rescuer) {
        System.out.printf("Имя: %s, возраст: %d, должность: %s, телефон: %d, оклад: %d орехов, UIN: %d.\n",
                rescuer.getName(), rescuer.getAge(), rescuer.getPosition(), rescuer.getPhone(), rescuer.getSalary(), rescuer.getUIN());
    }

    private static void printInfo(Rescuer[] arrRescuers) {
        for (int i = 0; i < arrRescuers.length; i++)
            printInfo(arrRescuers[i]);
    }

    private static void salaryUp(Rescuer[] arrRescuers, int age, int summ) {
        for (int i = 0; i < arrRescuers.length; i++) {
            if (arrRescuers[i].getAge() >= age) { //если 45 исполнилось, то сотрудник уже старше сорока пяти. Я добрый и не жадный...
                arrRescuers[i].setSalary(arrRescuers[i].getSalary() + summ);
                printInfo(arrRescuers[i]); //Исключительно для контроля, зп и без этого будет увеличена.
            }
        }
    }

    private static float meanArithmetic(Rescuer[] arrRescuers, String type) {
        float meanArith = 0;
        for (int i = 0; i < arrRescuers.length; i++) {
            //Для текущей задачи можно использовать тернарный оператор
            meanArith += (type == "Age") ? arrRescuers[i].getAge() : arrRescuers[i].getSalary();
            // Коммент под методом.
        }
        meanArith = meanArith / arrRescuers.length;
        return meanArith;
    }
// Если задавать еще и рост,вес,размер ноги... (если слесарный цех, то и количество пальцев):
// В теле цикла
//       for (int i = 0; i < arrWorkers.length; i++) {
//          switch (type) {
//              case "Age":
//                  meanArith += arrWorkers[i].getAge();
//                  break;
//              case "Salary":
//                  meanArith += arrWorkers[i].getSalary();
//                  break;
//          }
//       }
// Или есть более хороший способ, чем switch...case? Можно ли передавать аргументом в метод название нужного метода класса?
// Например meanArithmetic(staff,"getSalary"). На момент сдачи ДЗ не нашел _понятного мне_ ответа.
}