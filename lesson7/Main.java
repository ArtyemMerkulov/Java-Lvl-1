package homework;

public class Main {

    public static void main(String[] args) {
        Cat[] cats = new Cat[] {
                new Cat("Barsik", 15),
                new Cat("Foka", 35),
                new Cat("Shurik", 10),
                new Cat("Tishka", 26),
                new Cat("Dusya", 15)
        };

        Plate plate = new Plate(100);
        plate.info();
        System.out.println();

        for(Cat cat : cats) {
            cat.eat(plate);
            plate.info();
            cat.info();
            System.out.println();
        }

        plate.addFood(-1);
        plate.addFood(1);
        cats[4].eat(plate);
        plate.info();
        cats[4].info();
    }
}
