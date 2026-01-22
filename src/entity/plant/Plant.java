package entity.plant;

import entity.LivingOrganism;
import config.type.PlantSpecies;
import repository.PlantFactory;
import util.Randomizer;

public class Plant extends LivingOrganism {
    private  final PlantSpecies species;

    public Plant(PlantSpecies species) {
        super(species);
        this.species = species;
    }

    public PlantSpecies getSpecies() {
        return species;
    }

    public void grow() {
        int plantsToGrow = Randomizer.nextInt(1, this.getMaxNumberOnCell() / 2);

        for (int i = 0; i < plantsToGrow; i++) {
            this.getLocation().addPlant(PlantFactory.createPlant(this.species));
        }
    }
}
