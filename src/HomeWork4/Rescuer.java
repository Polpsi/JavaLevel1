package HomeWork4;

//Задание 1
public class Rescuer {
    // Задание 8. Значение nextUIN присвоится в поле UIN следующему создаваемому экземпляру класса.
    // static - не нужно это обьекту, нужно только конструктору при генерации нового экземпляра.
    // Не с нуля - потому, что Чип обидится,что он - ноль.
    // Да и, в принципе, обычно штатное расписание начинается с ген.дира или руководителя подразделения.
    // В конструкторе, при генерации экземпляра, значение увеличится на 1.
    private static int nextUIN = 1;
    // "Ну, там типа календарь, там get(Year), да.... Для простоты..." (С) И.Овчинников. :)))
    // Запись вебинара от 24/01/2020, 1:57:46
    // В целом, с календарём понятно, почитал, подразобрался.
    private static final int currentYear = 2020;
    private String name, position; //в нейм "покладём" все ФИО разом.
    private int birthYear, phone, salary, UIN;

    //Задание 2
    Rescuer(String name, int age, String position, int phone, int salary) {
        this.name = name;
        this.birthYear = currentYear - age;
        this.position = position;
        this.phone = phone;
        this.salary = salary;
        this.UIN = nextUIN++; //Увеличим nextUIN для получения следующим экземпляром класса корректного значения.
    }

    //Задание 3.
    public String getName() {return name;}
    public int getAge() {return currentYear - birthYear;}
    public String getPosition() {return position;}
    public int getPhone() {return phone;}
    public int getSalary() {return salary;}
    public int getUIN() {return UIN;}

    //Эти сеттеры оставлены, т.к. данные могут меняться, хоть в ДЗ они и не используются кроме setSalary
    public void setName(String name) {this.name = name;} // А вдруг в ЗАГСе сменил имя за время работы программы.
    public void setPosition(String position) {this.position = position;}
    public void setPhone(int phone) {this.phone = phone;}
    public void setSalary(int salary) {this.salary = salary;}
}