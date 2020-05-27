package lesson5;

public class Main {

    public static void main(String[] args) {
        Employee[] employees = new Employee[5];

        employees[0] = new Employee("Иванов Алекснадр Сергеевич",
                "Инженер-аналитик", "sanya@gmail.com",
                "+7 (983) 123-4567", 78999, 21);
        employees[1] = new Employee("Петров Андрей Сергеевич",
                "Инженер-программист", "andrey@gmail.com",
                "+7 (913) 987-6543", 99789, 33);
        employees[2] = new Employee("Сидоров Иван Михайлович",
                "Инженер", "ivan@gmail.com",
                "+7 (923) 777-0515", 67896, 45);
        employees[3] = new Employee("Смирнов Олег Петрович",
                "Системный администратор", "oleg@gmail.com",
                "+7 (903) 567-4562", 35001, 29);
        employees[4] = new Employee("Прохин Петр Иванович",
                "Бухгалтер", "petr@gmail.com",
                "+7 (913) 628-0050", 9999999, 51);

        for(Employee employee : employees) {
            if(employee.getAge() > 40) {
                employee.info();
            }
        }
    }
}
