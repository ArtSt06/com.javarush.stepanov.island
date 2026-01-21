package entity.animal;

import config.Settings;
import config.type.PlantSpecies;
import config.type.Species;
import entity.LivingOrganism;
import config.type.AnimalSpecies;
import entity.island.Island;
import entity.island.Location;
import repository.AnimalFactory;
import util.Randomizer;

import java.util.*;

public abstract class Animal extends LivingOrganism {
    private final AnimalSpecies species;

    private Double currentSatiety;

    private Integer reproductionCooldown;

    protected Animal(AnimalSpecies species) {
        super(species);
        this.species = species;
        this.currentSatiety = Randomizer.nextDouble(0.5, 1.0);
        this.reproductionCooldown = 0;
    }

    public AnimalSpecies getSpecies() {
        return species;
    }

    public Integer getMovementSpeed() {
        return species.getMovementSpeed();
    }

    public Map<Species, Double> getDiet() {
        return species.getDiet();
    }

    public Double getFoodRequired() {
        return species.getFoodRequired();
    }

    public Double getCurrentSatiety() {
        return currentSatiety;
    }

    public void increaseSatiety(Double diff) {
        this.currentSatiety = Math.min(currentSatiety + diff, 1.0);
    }

    public void decreaseSatiety() {
        this.currentSatiety -= Settings.SATIETY_DECREASE_PER_TICK;

        if (currentSatiety <= 0) {
            this.die();
        }
    }

    public void decreaseSatiety(Double diff) {
        this.currentSatiety -= diff;

        if (currentSatiety <= 0) {
            this.die();
        }
    }

    public Integer getReproductionCooldown() {
        return reproductionCooldown;
    }

    public void decreaseReproductionCooldown() {
        this.reproductionCooldown = Math.max(reproductionCooldown - 1, 0);
    }

    public void restartReproductionCooldown() {
        this.reproductionCooldown = Settings.REPRODUCTION_COOLDOWN;
    }

    public Boolean eat() {
        if (this.currentSatiety >= 1.0) {
            return false;
        }

        Double eatingPercent = Randomizer.nextDouble();

        Location currentLocation = this.getLocation();
        List<LivingOrganism> availableFood = new ArrayList<>();

        for (Species species : this.getDiet().keySet()) {
            if (eatingPercent <= this.getDiet().get(species)) {
                if (species instanceof AnimalSpecies animalSpecies) {
                    availableFood.addAll(currentLocation.getAnimalsBySpecies(animalSpecies));
                } else if (species instanceof PlantSpecies plantSpecies) {
                    availableFood.addAll(currentLocation.getPlantsBySpecies(plantSpecies));
                }
                break;
            }
        }

        if (!availableFood.isEmpty()) {
            while (currentSatiety < 0.5) {
                LivingOrganism food = availableFood.get(Randomizer.nextInt(availableFood.size()));
                this.increaseSatiety(food.getWeight() / this.getFoodRequired());
                currentLocation.removeOrganism(food);
            }
            return true;
        }

        return false;
    }

    public void move() {
        Integer movementRange = Randomizer.nextInt(this.getMovementSpeed() + 1);

        if (movementRange == 0) return;

        Location currentLocation = this.getLocation();

        List<Location> accessibleLocations = findAccessibleLocations(currentLocation, movementRange);

        if (accessibleLocations.isEmpty()) {
            return;
        }

        Location destination = accessibleLocations.get(Randomizer.nextInt(accessibleLocations.size()));

        int allAnimalsPopulation = destination.getAllAnimalsCount();

        int animalsOfThisType = destination.getAnimalsBySpecies(this.getSpecies()).size();

        if (allAnimalsPopulation < Settings.MAX_ANIMALS_ON_CELL && animalsOfThisType < this.getMaxNumberOnCell()) {
            if (destination.addAnimal(this)) {
                this.decreaseSatiety(Settings.SATIETY_DECREASE_AFTER_MOVEMENT);
                currentLocation.removeOrganism(this);
            }
        }
    }

    private List<Location> findAccessibleLocations(Location currentLocation, Integer movementRange) {
        Island island = Island.getInstance();
        Integer currentX = currentLocation.getCoordinateX();
        Integer currentY = currentLocation.getCoordinateY();

        int maxX = Math.min(currentX + movementRange, island.getIslandWidth());
        int minX = Math.max(currentX - movementRange, 0);
        int maxY = Math.min(currentY + movementRange, island.getIslandHeight());
        int minY = Math.max(currentY - movementRange, 0);

        List<Location> locations = new ArrayList<>();

        for (int y = minY; y < maxY; y++) {
            Integer deltaY = Math.abs((currentY - y));
            for (int x = minX; x < maxX; x++) {
                Integer deltaX = Math.abs((currentX - x));
                Integer routeLength = deltaX + deltaY;
                if (routeLength <= movementRange && routeLength != 0) {
                    locations.add(Island.getInstance().getLocation(x, y));
                }
            }
        }
        return locations;
    }

    public void reproduce() {
        if (!this.canReproduce()) {
            return;
        }

        Optional<Animal> partner = this.findPotentialPartner();

        if (partner.isPresent()) {
            Location currentLocation = this.getLocation();

            if (Randomizer.nextDouble() <= Settings.REPRODUCTION_CHANCE) {
                Animal offspring = AnimalFactory.createAnimal(this.getSpecies());
                offspring.restartReproductionCooldown();
                this.decreaseSatiety(Settings.SATIETY_DECREASE_AFTER_REPRODUCTION);
                this.restartReproductionCooldown();
                partner.get().decreaseSatiety(Settings.SATIETY_DECREASE_AFTER_REPRODUCTION);
                partner.get().restartReproductionCooldown();
                currentLocation.addAnimal(offspring);
            }
        }
    }

    public Boolean canReproduce() {
        if (this.getCurrentSatiety() < Settings.SATIETY_ENOUGH_FOR_REPRODUCTION ||
                this.getReproductionCooldown() != 0) {
            return false;
        }

        Location currentLocation = this.getLocation();

        return currentLocation.canSupportMoreSuchAnimals(this.getSpecies());
    }

    public Optional<Animal> findPotentialPartner() {
        Location currentLocation = this.getLocation();

        return currentLocation.getAnimalsBySpecies(this.getSpecies()).stream()
                .filter(animal -> animal.canReproduce() && animal != this)
                .findFirst();
    }
}
