package service;

import config.type.AnimalSpecies;
import config.type.PlantSpecies;
import entity.island.Island;
import entity.island.Location;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class StatisticService {
    private static StatisticService instance;

    private final Island island;

    private StatisticService() {
        this.island = Island.getInstance();
    }

    public static synchronized StatisticService getInstance() {
        if (instance == null) {
            instance = new StatisticService();
        }
        return instance;
    }

    public void showStatistics() {
        List<Location> locations = island.getAllLocations();
        final Map<AnimalSpecies, Integer> animals = new EnumMap<>(AnimalSpecies.class);
        final Map<PlantSpecies, Integer> plants = new EnumMap<>(PlantSpecies.class);

        for (Location location : locations) {
            location.getLock().lock();
            try {
                for (AnimalSpecies animalSpecies : AnimalSpecies.values()) {
                    animals.put(animalSpecies, location.getAnimalsBySpecies(animalSpecies).size() + animals.getOrDefault(animalSpecies, 0));
                }

                for (PlantSpecies plantSpecies : PlantSpecies.values()) {
                    plants.put(plantSpecies, location.getPlantsBySpecies(plantSpecies).size() + plants.getOrDefault(plantSpecies, 0));
                }
            } finally {
                location.getLock().unlock();
            }
        }

        System.out.println("Животные:");
        for (Map.Entry<AnimalSpecies, Integer> entry : animals.entrySet()) {
            System.out.printf("%s: %d ", entry.getKey().getEmoji(), entry.getValue());
        }
        System.out.println();
        System.out.println("Растения:");
        for (Map.Entry<PlantSpecies, Integer> entry : plants.entrySet()) {
            System.out.printf("%s: %d ", entry.getKey().getEmoji(), entry.getValue());
        }
        System.out.println();
    }
}
