package entity.plant;

import entity.LivingOrganism;
import config.type.PlantSpecies;
import repository.PlantFactory;

public class Plant extends LivingOrganism {
    private  final PlantSpecies species;

    public Plant(PlantSpecies species) {
        super(species);
        this.species = species;
    }

    public PlantSpecies getSpecies() {
        return species;
    }

    public String getEmoji() {
        return species.getEmoji();
    }

    public void grow() {
        this.getLocation().addPlant(PlantFactory.createPlant(this.species));
    }
}
