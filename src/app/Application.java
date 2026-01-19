package app;

import entity.animal.Animal;
import entity.animal.carnivore.Boa;
import entity.animal.carnivore.Carnivore;

public class Application {
    public static void main(String[] args) {
        Boa boa = new Boa();
        System.out.println(boa.getEmoji());
        System.out.println(boa.getWeight());
        System.out.println(boa.getMaxNumberOnCell());
        System.out.println(boa.getMovementSpeed());
        System.out.println(boa.getMaxSatiety());
        System.out.println(boa.getCurrentSatiety());
    }
}