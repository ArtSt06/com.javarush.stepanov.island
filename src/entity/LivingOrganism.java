package entity;

import entity.island.Location;

public abstract class LivingOrganism {
    private final Double weight;
    private final Integer maxNumberOnCell;
    private Location currentLocation;
    private Boolean isAlive;

    protected LivingOrganism(Double weight, Integer maxNumberOnCell) {
        this.weight = weight;
        this.maxNumberOnCell = maxNumberOnCell;
        this.isAlive = true;
    }

    public Double getWeight() {
        return this.weight;
    }

    public Integer getMaxNumberOnCell() {
        return this.maxNumberOnCell;
    }

    public void setLocation(Location location) {
        this.currentLocation = location;
    }

    public Boolean isAlive() {
        return this.isAlive;
    }

    public void die() {
        this.isAlive = false;
    }
}
