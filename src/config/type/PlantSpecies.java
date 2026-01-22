package config.type;

import java.util.EnumSet;

public enum PlantSpecies implements Species {
    GRASS("\uD83C\uDF3F", 1.0, 200),
    BERRY("\uD83C\uDF53", 0.2, 115), // Добавлен новый вид растений
    TREE("\uD83C\uDF33", 80.0, 8); // Добавлен новый вид растений

    private final String emoji;
    private final Double weight;
    private final Integer maxNumberOnCell;

    public static final EnumSet<PlantSpecies> SPECIES = EnumSet.of(
            GRASS, BERRY, TREE
    );

    PlantSpecies(String emoji, Double weight, Integer maxNumberOnCell) {
        this.emoji = emoji;
        this.weight = weight;
        this.maxNumberOnCell = maxNumberOnCell;
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
}
