package config;

import java.util.concurrent.TimeUnit;

public final class Settings {

    public static final class MapConfig {

        public static final Integer MAP_SIZE_X = 100;

        public static final Integer MAP_SIZE_Y = 20;

        public static final Integer MAX_ANIMALS_ON_CELL = 2545;

    }

    public static final class AnimalConfig {

        public static final Double SATIETY_DROP_PER_TICK = 0.05;

        public static final Double REPRODUCTION_CHANCE = 0.75;

        public static final Integer REPRODUCTION_COOLDOWN = 3;

        public static final Double SATIETY_ENOUGH_FOR_REPRODUCTION = 0.65;

        public static final Double SATIETY_DROP_AFTER_REPRODUCTION = 0.25;

        public static final Double SATIETY_VALUE_BEFORE_STARVATION = 0.20;

        public static final Double MOVEMENT_CHANCE = 0.65;

        public static final Double SATIETY_DROP_AFTER_MOVEMENT = 0.10;

    }

    public static final class SimulationConfig {

        public static final Integer SCHEDULER_POOL_SIZE = 4;

        public static final Integer SCHEDULER_INITIAL_DELAY = 1;

        public static final Integer SCHEDULER_DELAY = 3;

        public static final TimeUnit SCHEDULER_TIMEUNIT = TimeUnit.SECONDS;

    }

}
