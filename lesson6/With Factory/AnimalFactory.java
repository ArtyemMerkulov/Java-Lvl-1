package homework62;

public class AnimalFactory {
    private static int total;
    private static int totalCat;
    private static int totalDog;

    public Animal getAnimal(AnimalTypes type) throws OutOfMemoryError {
        Animal toReturn = null;

        switch(type) {
            case CAT:
                try {
                    totalCat++;
                    toReturn = new Cat(totalCat);
                } catch (OutOfMemoryError e) {
                    System.out.println("Can not create Cat object!");
                    System.out.println(e.getMessage());
                } finally {
                    if(toReturn != null) {
                        updateTotal();
                    } else {
                        totalCat--;
                    }
                }
                break;
            case DOG:
                try {
                    totalDog++;
                    toReturn = new Dog(totalDog);
                } catch (OutOfMemoryError e) {
                    System.out.println("Can not create Dog object!");
                    System.out.println(e.getMessage());
                } finally {
                    if(toReturn != null) {
                        updateTotal();
                    } else {
                        totalDog--;
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong animal type: " + type);
        }

        return toReturn;
    }

    public Animal getAnimal(AnimalTypes type, String name) {
        Animal toReturn = null;
        switch(type) {
            case CAT:
                try {
                    totalCat++;
                    toReturn = new Cat(totalCat, name);
                } catch (OutOfMemoryError e) {
                    System.out.println("Can not create Cat object!");
                    System.out.println(e.getMessage());
                } finally {
                    if(toReturn != null) {
                        updateTotal();
                    } else {
                        totalCat--;
                    }
                }
                break;
            case DOG:
                try {
                    totalDog++;
                    toReturn = new Dog(totalDog, name);
                } catch (OutOfMemoryError e) {
                    System.out.println("Can not create Dog object!");
                    System.out.println(e.getMessage());
                } finally {
                    if(toReturn != null) {
                        updateTotal();
                    } else {
                        totalDog--;
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong animal type: " + type);
        }

        return toReturn;
    }

    private static void updateTotal() {
        total++;
    }

    public static int getTotal() {
        return total;
    }
}
