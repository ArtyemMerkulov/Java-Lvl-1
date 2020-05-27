package homework62;

public class Main {

    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();

        Animal cat1 = animalFactory.getAnimal(AnimalTypes.CAT);
        Animal cat2 = animalFactory.getAnimal(AnimalTypes.CAT, "КОТ");

        System.out.println("ID cat1 = " + cat1.getId());
        System.out.println("ID cat2 = " + cat2.getId());

        cat1.run(600);
        cat1.swim(23);
        cat2.run(-3);
        cat2.swim(1);

        System.out.println();

        Animal dog1 = animalFactory.getAnimal(AnimalTypes.DOG);
        Animal dog2 = animalFactory.getAnimal(AnimalTypes.DOG, "ПЕС");

        System.out.println("ID dog1 = " + dog1.getId());
        System.out.println("ID dog2 = " + dog2.getId());

        dog1.run(200);
        dog1.swim(5);
        dog2.run(-3);
        dog2.swim(1);

        System.out.println();

        for(int i = 0; i < 3; i++) {
            Animal cat = animalFactory.getAnimal(AnimalTypes.CAT);
            Animal dog = animalFactory.getAnimal(AnimalTypes.DOG);
        }

        Animal cat3 = animalFactory.getAnimal(AnimalTypes.CAT);
        Animal dog3 = animalFactory.getAnimal(AnimalTypes.DOG);

        System.out.println("ID cat3 = " + cat3.getId());
        System.out.println("ID dog3 = " + dog3.getId());

        System.out.println();

        System.out.println("Всего создано " + AnimalFactory.getTotal() + " животных.");
    }
}
