package lesson5;

public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Employee() {
        this.fullName = "";
        this.position = "";
        this.email = "";
        this.phone = "";
        this.salary = 0;
        this.age = 0;
    }

    public Employee(String fullName, String position, String email,
                    String phone, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPosition() {
        return this.position;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getSalary() {
        return this.salary;
    }

    public int getAge() {
        return this.age;
    }

    public void info() {
        System.out.println("Работник " + this.fullName + ", " +
                this.position + ", " + this.email + ", " +
                this.phone + ", " + this.salary + ", " +
                this.age + ".");
    }
}
