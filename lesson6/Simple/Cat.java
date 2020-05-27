package homework6;

public class Cat extends Animal {

    private static int total;

    Cat() {
        total++;
        this.id = total;
        this.name = "";

        System.out.println("Создан кот №" + this.getId() + ".");
    }

    Cat(String name) {
        this();
        this.name = name;
    }

    public static int getTotal(){
        return total;
    }

    @Override
    public void run(double obstacleLength) {
        if(obstacleLength < 0) {
            System.out.println("Кот " + name + " не двинулся с " +
                    "места, т. к. не возможно пробежать " + obstacleLength + " м.");
        } else if(obstacleLength > 200) {
            System.out.println("Кот " + name + " не пробежит " + obstacleLength +
                    " м., т. к. слишком далеко.");
        } else {
            System.out.println("Кот " + name + " пробежал " + obstacleLength + " м.");
        }
    }

    @Override
    public void swim(double obstacleLength) {
        System.out.println("Кот " + name + " не двинулся с места, т. к. коты не умеют плавать.");
    }
}
