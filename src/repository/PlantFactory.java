package repository;

import config.type.PlantSpecies;
import entity.plant.Berry;
import entity.plant.Grass;
import entity.plant.Plant;
import entity.plant.Tree;


public class PlantFactory {

    public static Plant createPlant(PlantSpecies species) {
        return switch (species) {
            case GRASS -> new Grass();
            case BERRY -> new Berry();
            case TREE -> new Tree();
        };
    }
}
