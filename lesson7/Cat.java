package homework;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate plate) {
        if(this.appetite <= plate.getFood() && !this.satiety) {
            plate.decreaseFood(this.appetite);
            this.satiety = true;
        } else if(this.satiety) {
            System.out.println("Коту уже сыт и есть не будет!");
        } else {
            System.out.println("Коту мало еды, поэтому он есть не стал. " +
                    "Насыпте больше еды в тарелку!");
        }
    }

    public void info() {
        if(this.satiety) {
            System.out.println("Кот сыт!");
        } else {
            System.out.println("Кот голоден!");
        }
    }
}
