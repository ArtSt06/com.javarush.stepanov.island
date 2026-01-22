package task;

import entity.plant.Plant;

public class PlantLifecycleTask {
    public static void startTask(Plant plant) {
        plant.grow();
    }
}
