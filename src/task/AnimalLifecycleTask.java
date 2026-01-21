package task;

import config.Settings;
import entity.animal.Animal;
import util.Randomizer;

public class AnimalLifecycleTask {
    public static void startTask(Animal animal) {
        if (animal.eat() && animal.isAlive()) {
            animal.reproduce();
        }

        if (Randomizer.nextDouble() < Settings.MOVEMENT_CHANCE) {
            animal.move();
        }

        animal.decreaseSatiety();
        animal.decreaseReproductionCooldown();
    }
}
