package config.type;

import java.util.EnumSet;
import java.util.Map;

public enum AnimalSpecies implements Species {

    WOLF("\uD83D\uDC3A", 50.0, 30, 3, 8.0),
    BOA("\uD83D\uDC0D", 15.0, 30, 1, 3.0),
    FOX("\uD83E\uDD8A", 8.0, 30, 2, 2.0),
    BEAR("\uD83D\uDC3B", 500.0, 5, 2, 80.0),
    EAGLE("\uD83E\uDD85", 6.0, 20, 3, 1.0),

    HORSE("\uD83D\uDC0E", 400.0, 20, 4, 60.0),
    DEER("\uD83E\uDD8C", 300.0, 20, 4, 50.0),
    RABBIT("\uD83D\uDC07", 2.0, 150, 2, 0.45),
    MOUSE("\uD83D\uDC01", 0.05, 500, 1, 0.01),
    GOAT("\uD83D\uDC10", 60.0, 140, 3, 10.0),
    SHEEP("\uD83D\uDC11", 70.0, 140, 3, 15.0),
    BOAR("\uD83D\uDC17", 400.0, 50, 2, 50.0),
    BUFFALO("\uD83D\uDC03", 700.0, 10, 3, 100.0),
    DUCK("\uD83E\uDD86", 1.0, 200, 4, 0.15),
    CATERPILLAR("\uD83D\uDC1B", 0.01, 1000, 0, 0.0);

    private final String emoji;
    private final Double weight;
    private final Integer maxNumberOnCell;
    private final Integer movementSpeed;
    private final Double foodRequired;

    public static final EnumSet<AnimalSpecies> SPECIES = EnumSet.of(
            WOLF, BOA, FOX, BEAR, EAGLE,
            HORSE, DEER, RABBIT, MOUSE, GOAT, SHEEP, BOAR, BUFFALO, DUCK, CATERPILLAR
    );

    AnimalSpecies(String emoji, Double weight, Integer maxNumberOnCell, Integer movementSpeed, Double foodRequired) {
        this.emoji = emoji;
        this.weight = weight;
        this.maxNumberOnCell = maxNumberOnCell;
        this.movementSpeed = movementSpeed;
        this.foodRequired = foodRequired;
    }

    @Override
    public String getEmoji() {
        return emoji;
    }

    @Override
    public Double getWeight() {
        return weight;
    }

    @Override
    public Integer getMaxNumberOnCell() {
        return maxNumberOnCell;
    }

    public Integer getMovementSpeed() {
        return movementSpeed;
    }

    public Double getFoodRequired() {
        return foodRequired;
    }

    public Map<Species, Double> getDiet() {
        return getAnimalDietForSpecies(this).getDiet();
    }

    private static AnimalDiet getAnimalDietForSpecies(AnimalSpecies species) {
        return switch (species) {
            case WOLF -> AnimalDiet.WOLF_DIET;
            case BOA -> AnimalDiet.BOA_DIET;
            case FOX -> AnimalDiet.FOX_DIET;
            case BEAR -> AnimalDiet.BEAR_DIET;
            case EAGLE -> AnimalDiet.EAGLE_DIET;
            case HORSE -> AnimalDiet.HORSE_DIET;
            case DEER -> AnimalDiet.DEER_DIET;
            case RABBIT -> AnimalDiet.RABBIT_DIET;
            case MOUSE -> AnimalDiet.MOUSE_DIET;
            case GOAT -> AnimalDiet.GOAT_DIET;
            case SHEEP -> AnimalDiet.SHEEP_DIET;
            case BOAR -> AnimalDiet.BOAR_DIET;
            case BUFFALO -> AnimalDiet.BUFFALO_DIET;
            case DUCK -> AnimalDiet.DUCK_DIET;
            case CATERPILLAR -> AnimalDiet.CATERPILLAR_DIET;
        };
    }
}
