package homework6;

public abstract class Animal {

    private static int total;
    protected int id;
    protected String name;

    Animal() {
        total++;
    }

    public static int getTotal() {
        return total;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void run(double obstacleLength);

    public abstract void swim(double obstacleLength);
}
