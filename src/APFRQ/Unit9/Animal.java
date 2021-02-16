package APFRQ.Unit9;

public class Animal {

    private String type;
    private String species;
    private String name;

    public Animal(String type, String species, String name){
        this.type = type;
        this.species = species;
        this.name = name;
    }

    public String toString(){
        return name + " the " + species + " is a " +type;
    }

    public static void main(String[] args) {
        Animal lisa = new Animal("carnivore", "lion", "lisa");
        System.out.println(lisa);

        //Herbivore herbivore = new Herbivore("orangutan", "anthony");
    }

    class Herbivore extends Animal{
        public Herbivore(String species, String name){
            super("herbivore", species, name);
        }
    }



}
