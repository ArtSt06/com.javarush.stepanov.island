package task;

import entity.LivingOrganism;
import entity.animal.Animal;
import entity.island.Island;
import entity.island.Location;
import entity.plant.Plant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationTask implements Runnable {
    final Location location;
    final Island island;

    public LocationTask(Location location, Island island) {
        this.location = location;
        this.island = island;
    }

    @Override
    public void run() {
        location.getLock().lock();
        try {
            List<LivingOrganism> organisms = new ArrayList<>(location.getAllOrganisms());
            Collections.shuffle(organisms);

            organisms.forEach(organism -> doTask(organism));

            location.removeAllDeadOrganisms();
        } finally {
            location.getLock().unlock();
        }
    }

    private void doTask(LivingOrganism organism) {
        if (organism instanceof Animal animal) {
            AnimalLifecycleTask.startTask(animal);
        } else if (organism instanceof Plant plant) {
            PlantLifecycleTask.startTask(plant);
        }
    }
}
