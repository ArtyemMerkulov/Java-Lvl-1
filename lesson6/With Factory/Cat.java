package homework62;

public class Cat extends Animal {
    Cat(int id) {
        this.id = id;
        this.name = "";
    }

    Cat(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void run(double obstacleLength) {
        if(obstacleLength < 0) {
            System.out.println("Кот " + this.name + " не двинулся с " +
                    "места, т. к. не возможно пробежать " + obstacleLength + " м.");
        } else if(obstacleLength > 200) {
            System.out.println("Кот " + this.name + " не пробежит " + obstacleLength +
                    " м., т. к. слишком далеко.");
        } else {
            System.out.println("Кот " + this.name + " пробежал " + obstacleLength + " м.");
        }
    }

    @Override
    public void swim(double obstacleLength) {
        System.out.println("Кот " + this.name + " не двинулся с места, " +
                "т. к. коты не умеют плавать.");
    }
}
