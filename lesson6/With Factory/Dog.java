package homework62;

public class Dog extends Animal {
    Dog(int id) {
        this.id = id;
        this.name = "";
    }

    Dog(int id, String name) {
        this.id = id;
        this.name = name;
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
