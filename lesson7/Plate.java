package homework;

public class Plate {
    private int food;

    Plate (int food) {
        this.food = food;
    }

    public void decreaseFood(int n) {
        if(this.food >= n) {
            this.food -= n;
        } else {
            System.out.println("Обнаружена попытка съесть еды больше, " +
                    "чем в тарелке имеется. Действие запрещено!");
        }
    }

    public int getFood() {
        return this.food;
    }

    public void addFood(int n) {
        if(n > 0) {
            this.food += n;
        } else {
            System.out.println("Нельзя добавить 0 или меньше еды!");
        }
    }

    public void info() {
        System.out.println("Plate: " + this.food);
    }
}
