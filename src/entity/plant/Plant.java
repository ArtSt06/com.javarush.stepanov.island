package entity.plant;

import entity.LivingOrganism;
import config.PlantSpecies;

public class Plant extends LivingOrganism {
    public Plant() {
        super(PlantSpecies.PLANT.getWeight(), PlantSpecies.PLANT.getMaxNumberOnCell());
    }
}
