package task;

import config.Settings;
import entity.plant.Plant;
import util.Randomizer;

public class PlantLifecycleTask {
    public static void startTask(Plant plant) {
        if (Randomizer.nextDouble() <= Settings.PLANTS_GROWTH_CHANCE) {
            plant.grow();
        }
    }
}
