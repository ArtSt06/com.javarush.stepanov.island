package entity.island;

import config.Settings;
import config.type.AnimalSpecies;
import config.type.PlantSpecies;
import entity.LivingOrganism;
import entity.animal.Animal;
import entity.plant.Plant;

import java.util.EnumMap;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Location {
    private final Island island;

    private final Integer coordinateX;
    private final Integer coordinateY;
    private final EnumMap<AnimalSpecies, List<Animal>> animalsMap = new EnumMap<>(AnimalSpecies.class);
    private final EnumMap<PlantSpecies, List<Plant>> plantsMap = new EnumMap<>(PlantSpecies.class);

    final ReentrantLock lock = new ReentrantLock(true);

    public Location(Integer cordX, Integer cordY, Island island) {
        this.coordinateX = cordX;
        this.coordinateY = cordY;
        this.island = island;
    }

    public Integer getCoordinateX() {
        return coordinateX;
    }

    public Integer getCoordinateY() {
        return coordinateY;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public List<Animal> getAnimalsBySpecies(AnimalSpecies species) {
        if (species == null) {
            throw new IllegalArgumentException("No such animal species");
        }

        return animalsMap.getOrDefault(species, new ArrayList<Animal>() {});
    }

    public Integer getAllAnimalsCount() {
        int count = 0;
        for (AnimalSpecies animalSpecies : animalsMap.keySet()) {
            count += getAnimalsBySpecies(animalSpecies).size();
        }
        return count;
    }

    public List<Plant> getPlantsBySpecies(PlantSpecies species) {
        if (species == null) {
            throw new IllegalArgumentException("No such plant species");
        }

        return plantsMap.getOrDefault(species, new ArrayList<Plant>() {});
    }


    public Integer getAllPlantsCount() {
        int count = 0;
        for (PlantSpecies plantSpecies : plantsMap.keySet()) {
            count += getPlantsBySpecies(plantSpecies).size();
        }
        return count;
    }

    public Boolean canSupportMoreSuchAnimals(AnimalSpecies species) {
        if (getAllAnimalsCount() >= Settings.MAX_ANIMALS_ON_CELL) {
            return false;
        }

        int animalsOfThisType = getAnimalsBySpecies(species).size();

        if (animalsOfThisType >= species.getMaxNumberOnCell()) {
            return false;
        }

        return true;
    }

    public Boolean addAnimal(Animal animal) {
        if (animal == null || !this.canSupportMoreSuchAnimals(animal.getSpecies())) {
            return false;
        }

        if (animalsMap.containsKey(animal.getSpecies())) {
            animalsMap.get(animal.getSpecies()).add(animal);
        } else {
            animalsMap.computeIfAbsent(animal.getSpecies(), a -> new ArrayList<>()).add(animal);
        }
        animal.setLocation(this);

        return true;
    }

    public Boolean addPlant(Plant plant) {
        if (plant == null) {
            return false;
        }

        int plantsOfThisType = getPlantsBySpecies(plant.getSpecies()).size();

        if (plantsOfThisType < plant.getMaxNumberOnCell()) {
            plantsMap.computeIfAbsent(plant.getSpecies(), p -> new ArrayList<>()).add(plant);
            plant.setLocation(this);
            return true;
        }

        return false;
    }

    public List<LivingOrganism> getAllOrganisms() {
        List<LivingOrganism> organisms = new ArrayList<>();
        for (List<Animal> animalsList : animalsMap.values()) {
            organisms.addAll(animalsList);
        }
        for (List<Plant> plantsList : plantsMap.values()) {
            organisms.addAll(plantsList);
        }
        return organisms;
    }

    public void removeOrganism(LivingOrganism organism) {
        if (organism == null || organism.isAlive()) {
            return;
        }

        if (organism instanceof Plant plant) {
            List<Plant> plantList = plantsMap.get(plant.getSpecies());
            if (plantList != null) {
                plantList.remove(plant);
            }
        }

        if (organism instanceof Animal animal) {
            List<Animal> animalList = animalsMap.get(animal.getSpecies());
            if (animalList != null) {
                animalList.remove(animal);
            }
        }
    }

    public void removeAllDeadOrganisms() {
        List<LivingOrganism> allOrganisms = new ArrayList<>(getAllOrganisms());

        List<LivingOrganism> deadOnes = allOrganisms.stream()
                .filter(organism -> !organism.isAlive())
                .toList();
        for (LivingOrganism deadOrganism : deadOnes) {
            removeOrganism(deadOrganism);
        }
    }
}
