package config;

import java.util.EnumSet;
import java.util.Map;

public enum AnimalSpecies {

    WOLF("Wolf", "\uD83D\uDC3A", 50.0, 30, 3, 8.0),
    BOA("Boa", "\uD83D\uDC0D", 15.0, 30, 1, 3.0),
    FOX("Fox", "\uD83E\uDD8A", 8.0, 30, 2, 2.0),
    BEAR("Bear", "\uD83D\uDC3B", 500.0, 5, 2, 80.0),
    EAGLE("Eagle", "\uD83E\uDD85", 6.0, 20, 3, 1.0),

    HORSE("Horse", "\uD83D\uDC0E", 400.0, 20, 4, 60.0),
    DEER("Deer", "\uD83E\uDD8C", 300.0, 20, 4, 50.0),
    RABBIT("Rabbit", "\uD83D\uDC07", 2.0, 150, 2, 0.45),
    MOUSE("Mouse", "\uD83D\uDC01", 0.05, 500, 1, 0.01),
    GOAT("Goat", "\uD83D\uDC10", 60.0, 140, 3, 10.0),
    SHEEP("Sheep", "\uD83D\uDC11", 70.0, 140, 3, 15.0),
    BOAR("Boar", "\uD83D\uDC17", 400.0, 50, 2, 50.0),
    BUFFALO("Buffalo", "\uD83D\uDC03", 700.0, 10, 3, 100.0),
    DUCK("Duck", "\uD83E\uDD86", 1.0, 200, 4, 0.15),
    CATERPILLAR("Caterpillar", "\uD83D\uDC1B", 0.01, 1000, 0, 0.0);

    private final String animalName;
    private final String emoji;
    private final Double weight;
    private final Integer maxNumberOnCell;
    private final Integer movementSpeed;
    private final Double maxSatiety;
    private final Map<AnimalSpecies, Double> diet;

//    private static final EnumSet<AnimalSpecies> CARNIVORES = EnumSet.of(
//            WOLF, BOA, FOX, BEAR, EAGLE
//    );
//    private static final EnumSet<AnimalSpecies> HERBIVORES = EnumSet.of(
//            HORSE, DEER, RABBIT, MOUSE, GOAT, SHEEP, BOAR, BUFFALO, DUCK, CATERPILLAR
//    );
//
//    public static final EnumSet<AnimalSpecies> SPECIES = EnumSet.of(
//            WOLF, BOA, FOX, BEAR, EAGLE,
//            HORSE, DEER, RABBIT, MOUSE, GOAT, SHEEP, BOAR, BUFFALO, DUCK, CATERPILLAR
//    );

    AnimalSpecies(String animalName, String emoji, Double weight, Integer maxNumberOnCell, Integer movementSpeed, Double maxSatiety) {
        this.animalName = animalName;
        this.emoji = emoji;
        this.weight = weight;
        this.maxNumberOnCell = maxNumberOnCell;
        this.movementSpeed = movementSpeed;
        this.maxSatiety = maxSatiety;
        this.diet = null;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getEmoji() {
        return emoji;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getMaxNumberOnCell() {
        return maxNumberOnCell;
    }

    public Integer getMovementSpeed() {
        return movementSpeed;
    }

    public Double getMaxSatiety() {
        return maxSatiety;
    }

    public Map<AnimalSpecies, Double> getDiet() {
        return diet;
    }
}
