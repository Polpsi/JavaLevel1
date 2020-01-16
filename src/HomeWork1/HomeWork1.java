package HomeWork1;

import java.util.Scanner;

public class HomeWork1 {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        // Ввод чисел с клавиатуры в консоли. Нет обработки исключений, если введут не int.
        System.out.println("Введите число А");
        int a = scan.nextInt();
        System.out.println("Введите число B");
        int b = scan.nextInt();
        System.out.println("Введите число C");
        int c = scan.nextInt();
        System.out.println("Введите число D");
        int d = scan.nextInt();

        // Вычисление результата выражения из ДЗ в методе calc(a,b,c,d),
        // вызываемом в аргументе операции вывода, возврат из метода float.
        System.out.println("Ответ на пример в первом задании - " + calc(a,b,c,d));

        // Проверка суммы чисел a и b, полученных ранее, на принадлежность к пределу [10,20].
        // Метод check(a,b), возврат boolean
        if (check(a,b)) {
            System.out.println("Сумма А и В лежит в пределе [10;20]");
        } else {
            System.out.println("Сумма А и В вышла из предела [10;20]");
        }

        // Запрос имени для строки приветствия
        System.out.println("Кому желаете передать привет? Введите имя:");
        String name = scan.next();
        // Формирование и вывод в консоль строки приветствия, метод sayHello(name), возврат String
        System.out.println(sayHello(name));

        // Проверка на високосность. Метод checkYear(year).
        // Метод ничего не возвращает, результат проверки выводит в консоль.
        System.out.println("Про какой год вы хотите узнать?");
        int year=scan.nextInt();
        checkYear(year);
    }

    private static float calc(int a,int b,int c,int d) {
        return a * (b + (c / (float)d));
    }
    /*Т.к. арифметические операции приводят к высшему типу данных, то во float можно перевести только один из аргументов.
     Вводить дополнительную переменную для результата в данном методе необязательно в данном случае,
     т.к. других операций с результатом нет. */

    private static boolean check(int a, int b) {
        if (((a+b)<=20) && ((a+b)>=10)) return true;
        else return false;
    }
    /* В каждом блоке конструкции if ... else ... всего по одному оператору,
    таким образом, можно обойтись без фигурных скобок и в одну строку.
    Но читать менее удобно при длинных выражениях. */

    private static void checkNegative (int a) {
        if (a<0) System.out.println("Число отрицательное");
        else System.out.println("Число положительное!");
    }
    //Метод void, ничего не возвращаем, только выводим в консоль.

    private static String sayHello (String name) {
        return "Привет, " + name + "!";
    }
    //Возврат строки с приветствием

    private static void checkYear (int year) {
        if (((year % 400) == 0) || ((year % 4 == 0) && (year % 100 != 0))) System.out.println("Год " + year + " - високосный.");
        else System.out.println("Год " + year + " - не високосный.");
    }
    /* До 1582 года метод работает не совсем точно.
    Стало интересно про високосные года. Оказалось, что описываемый метод определения високосных лет
    используется с 1582 (появление григорианского календаря).
    До 45 г. до н.э. високосных лет не было вообще. С 45 по 9 гг. до н.э. - каждые 3 года, потом не было вообще,
    потом с 8 г.н.э. до 1582 г. - каждые 4 года. Можно было сделать, но выйдет за рамки задачи и усложнит проверку ДЗ.*/
}
