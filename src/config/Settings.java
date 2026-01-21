package config;

import java.util.concurrent.TimeUnit;

public final class Settings {
    public static final Integer MAP_SIZE_X = 100;

    public static final Integer MAP_SIZE_Y = 20;

    public static final Integer MAX_ANIMALS_ON_CELL = 2545;

    public static final Double SATIETY_DECREASE_PER_TICK = 0.1;

    public static final Double SATIETY_ENOUGH_FOR_REPRODUCTION = 0.95;

    public static final Double SATIETY_DECREASE_AFTER_REPRODUCTION = 0.65;

    public static final Double SATIETY_DECREASE_AFTER_MOVEMENT = 0.35;

    public static final Double REPRODUCTION_CHANCE = 0.15;

    public static final Integer REPRODUCTION_COOLDOWN = 7;

    public static final Double MOVEMENT_CHANCE = 0.85;

    public static final Double PLANTS_GROWTH_CHANCE = 0.05;

    public static final Integer SCHEDULER_POOL_SIZE = 8;

    public static final Integer SCHEDULER_INITIAL_DELAY = 1;

    public static final Integer SCHEDULER_DELAY = 3;

    public static final TimeUnit SCHEDULER_TIMEUNIT = TimeUnit.SECONDS;
}
