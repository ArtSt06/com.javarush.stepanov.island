package util;

import java.util.concurrent.ThreadLocalRandom;

public final class Randomizer {
    private Randomizer() {
    };

    public static int nextInt(int max) {
        return ThreadLocalRandom.current().nextInt(max);
    }

    public static int nextInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
