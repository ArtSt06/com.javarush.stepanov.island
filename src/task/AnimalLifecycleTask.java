package task;

import config.Settings;
import entity.animal.Animal;
import util.Randomizer;

public class AnimalLifecycleTask {
    public static void startTask(Animal animal) {
        if (animal.eat()) {
            animal.reproduce();

            if (!animal.isAlive()) {
                return;
            }
        }

        if (Randomizer.nextDouble() < Settings.AnimalConfig.MOVEMENT_CHANCE) {
            animal.move();

            if (!animal.isAlive()) {
                return;
            }
        }

        animal.decreaseSatiety();
        animal.decreaseReproductionCooldown();
    }
}
