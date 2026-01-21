package app;

import config.type.AnimalSpecies;
import config.type.PlantSpecies;
import entity.island.Island;
import entity.island.Location;
import repository.AnimalFactory;
import repository.PlantFactory;
import service.SimulationWorker;
import util.Randomizer;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        final Island island = Island.getInstance();
        populateIsland(island);

        final SimulationWorker simulationWorker = new SimulationWorker();
        simulationWorker.start();
    }

    public static void populateIsland(Island island) {
        List<Location> locations = island.getAllLocations();

        for (Location location : locations) {
            for (AnimalSpecies species : AnimalSpecies.SPECIES) {
                int animalCount = Randomizer.nextInt(1, species.getMaxNumberOnCell());

                for (int i = 0; i < animalCount; i++) {
                    location.addAnimal(AnimalFactory.createAnimal(species));
                }
            }

            for (PlantSpecies species : PlantSpecies.SPECIES) {
                int plantCount = Randomizer.nextInt(1, species.getMaxNumberOnCell());

                for (int i = 0; i < plantCount; i++) {
                    location.addPlant(PlantFactory.createPlant(species));
                }
            }
        }
    }
}