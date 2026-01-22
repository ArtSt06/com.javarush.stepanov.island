package entity;

import config.type.Species;
import entity.island.Location;

public abstract class LivingOrganism {
    private final Species species;

    private Location currentLocation;

    private Boolean isAlive;


    protected LivingOrganism(Species species) {
        this.species = species;
        this.isAlive = true;
    }

    public Double getWeight() {
        return species.getWeight();
    }

    public Integer getMaxNumberOnCell() {
        return species.getMaxNumberOnCell();
    }

    public Location getLocation() {
        return currentLocation;
    }

    public void setLocation(Location location) {
        this.currentLocation = location;
    }

    public Boolean isAlive() {
        return isAlive;
    }

    public void die() {
        this.isAlive = false;
        currentLocation.removeOrganism(this);
    }
}
