package homework62;

public abstract class Animal {
    protected int id;
    protected String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    abstract void run(double obstacleLength);
    abstract void swim(double obstacleLength);
}
