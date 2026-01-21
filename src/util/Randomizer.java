package util;

import java.util.concurrent.ThreadLocalRandom;

public final class Randomizer {
    private Randomizer() {
    };

    public static Integer nextInt(int max) {
        return ThreadLocalRandom.current().nextInt(max);
    }

    public static Integer nextInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static Double nextDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    public static Double nextDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
