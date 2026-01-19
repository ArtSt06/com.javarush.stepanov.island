package entity.island;

import entity.animal.Animal;
import entity.plant.Plant;

public class Location {
    private final Integer coordinateX;
    private final Integer coordinateY;

    public Location(Integer cordX, Integer cordY) {
        this.coordinateX = cordX;
        this.coordinateY = cordY;
    }

    public boolean addAnimal(Animal animal) {
        return false;
    }

    public boolean addPlant(Plant plant) {
        return false;
    }
}
