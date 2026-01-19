package config;

public enum PlantSpecies {
    PLANT("\uD83C\uDF3F", 1.0, 200);

    private final String emoji;
    private final Double weight;
    private final Integer maxNumberOnCell;

    PlantSpecies(String emoji, Double weight, Integer maxNumberOnCell) {
        this.emoji = emoji;
        this.weight = weight;
        this.maxNumberOnCell = maxNumberOnCell;
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
}
