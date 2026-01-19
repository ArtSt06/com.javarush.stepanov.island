package entity.animal;

import entity.LivingOrganism;
import config.AnimalSpecies;

import java.util.Map;

public abstract class Animal extends LivingOrganism {
    private final String emoji;
    private final Integer movementSpeed;
    private final Double maxSatiety;
    private final Map<AnimalSpecies, Double> diet;

    private Double currentSatiety;

    protected Animal(AnimalSpecies species) {
        super(species.getWeight(), species.getMaxNumberOnCell());
        this.emoji = species.getEmoji();
        this.movementSpeed = species.getMovementSpeed();
        this.maxSatiety = species.getMaxSatiety();
        this.diet = species.getDiet();
        this.currentSatiety = maxSatiety / 2;
    }

    public String getEmoji() {
        return emoji;
    }

    public Integer getMovementSpeed() {
        return movementSpeed;
    }

    public Double getMaxSatiety() {
        return maxSatiety;
    }

    public Double getCurrentSatiety() {
        return currentSatiety;
    }

    public void setCurrentSatiety(Double currentSatiety) {
        this.currentSatiety = currentSatiety;
    }

    public void eat(LivingOrganism food) {

    }

    public void move() {

    }

    public void reproduce() {

    }
}
