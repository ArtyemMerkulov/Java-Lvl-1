package homework6;

public class Dog extends Animal {

    private static int total;

    Dog() {
        total++;
        this.id = total;
        this.name = "";

        System.out.println("Создана собака №" + this.getId() + ".");
    }

    Dog(String name) {
        this();
        this.name = name;
    }

    public static int getTotal(){
        return total;
    }

    @Override
    public void run(double obstacleLength) {
        if(obstacleLength < 0) {
            System.out.println("Собака " + name + " не двинулась с " +
                    "места, т. к. не возможно пробежать " + obstacleLength + " м.");
        } else if(obstacleLength > 500) {
            System.out.println("Собака " + name + " не пробежит " + obstacleLength +
                    " м., т. к. слишком далеко.");
        } else {
            System.out.println("Собака " + name + " пробежала " + obstacleLength + " м.");
        }
    }

    @Override
    public void swim(double obstacleLength) {
        if(obstacleLength < 0) {
            System.out.println("Собака " + name + " не двинулась с " +
                    "места, т. к. не возможно проплыть " + obstacleLength + " м.");
        } else if(obstacleLength > 10) {
            System.out.println("Собака " + name + " не проплывет " + obstacleLength +
                    " м., т. к. слишком далеко.");
        } else {
            System.out.println("Собака " + name + " проплыла " + obstacleLength + " м.");
        }
    }
}
