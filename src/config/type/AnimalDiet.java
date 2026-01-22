package config.type;

import java.util.*;
import java.util.stream.Collectors;

public enum AnimalDiet {

    WOLF_DIET(
            Map.entry(AnimalSpecies.HORSE, 0.10),
            Map.entry(AnimalSpecies.DEER, 0.15),
            Map.entry(AnimalSpecies.RABBIT, 0.60),
            Map.entry(AnimalSpecies.MOUSE, 0.80),
            Map.entry(AnimalSpecies.GOAT, 0.60),
            Map.entry(AnimalSpecies.SHEEP, 0.70),
            Map.entry(AnimalSpecies.BOAR, 0.15),
            Map.entry(AnimalSpecies.BUFFALO, 0.10),
            Map.entry(AnimalSpecies.DUCK, 0.40)
    ),
    BOA_DIET(
            Map.entry(AnimalSpecies.FOX, 0.15),
            Map.entry(AnimalSpecies.RABBIT, 0.20),
            Map.entry(AnimalSpecies.MOUSE, 0.40),
            Map.entry(AnimalSpecies.DUCK, 0.10)
    ),
    FOX_DIET(
            Map.entry(AnimalSpecies.RABBIT, 0.70),
            Map.entry(AnimalSpecies.MOUSE, 0.90),
            Map.entry(AnimalSpecies.DUCK, 0.60),
            Map.entry(AnimalSpecies.CATERPILLAR, 0.40)
    ),
    BEAR_DIET(
            Map.entry(AnimalSpecies.BOA, 0.80),
            Map.entry(AnimalSpecies.HORSE, 0.40),
            Map.entry(AnimalSpecies.DEER, 0.80),
            Map.entry(AnimalSpecies.RABBIT, 0.80),
            Map.entry(AnimalSpecies.MOUSE, 0.90),
            Map.entry(AnimalSpecies.GOAT, 0.70),
            Map.entry(AnimalSpecies.SHEEP, 0.70),
            Map.entry(AnimalSpecies.BOAR, 0.50),
            Map.entry(AnimalSpecies.BUFFALO, 0.20),
            Map.entry(AnimalSpecies.DUCK, 0.10),

            Map.entry(PlantSpecies.BERRY, 0.20) // Добавлен новый тип еды
    ),
    EAGLE_DIET(
            Map.entry(AnimalSpecies.FOX, 0.10),
            Map.entry(AnimalSpecies.RABBIT, 0.90),
            Map.entry(AnimalSpecies.MOUSE, 0.90),
            Map.entry(AnimalSpecies.DUCK, 0.80)
    ),
    HORSE_DIET,
    GOAT_DIET,
    SHEEP_DIET,
    BUFFALO_DIET(
            Map.entry(PlantSpecies.GRASS, 1.00)
    ),
    CATERPILLAR_DIET,
    RABBIT_DIET,
    DEER_DIET(
            Map.entry(PlantSpecies.GRASS, 1.00),

            Map.entry(PlantSpecies.BERRY, 0.35) // Добавлен новый тип еды
    ),
    MOUSE_DIET(
            Map.entry(PlantSpecies.GRASS, 1.00),
            Map.entry(AnimalSpecies.CATERPILLAR, 0.90)
    ),
    DUCK_DIET(
            Map.entry(PlantSpecies.GRASS, 1.00),
            Map.entry(AnimalSpecies.CATERPILLAR, 0.90)
    ),
    BOAR_DIET(
            Map.entry(PlantSpecies.GRASS, 1.00),
            Map.entry(AnimalSpecies.MOUSE, 0.50),
            Map.entry(AnimalSpecies.CATERPILLAR, 0.90)
    );

    private final Map<Species, Double> diet;

    @SafeVarargs
    AnimalDiet(Map.Entry<Species, Double> ... entries) {
        Map<Species, Double> unsortedDiet = new HashMap<>();

        for (Map.Entry<Species, Double> entry : entries) {
            if (!(entry.getValue() > 0 && entry.getValue() <= 1)) {
                continue;
            }
            unsortedDiet.put(entry.getKey(), entry.getValue());
        }

        this.diet = unsortedDiet.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Map<Species, Double> getDiet() {
        return diet;
    }
}
