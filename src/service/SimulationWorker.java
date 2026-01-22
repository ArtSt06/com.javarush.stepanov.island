package service;

import config.Settings;
import entity.island.Island;
import entity.island.Location;
import task.LocationTask;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class SimulationWorker {
    private final Island island = Island.getInstance();
    private final StatisticService statisticService = StatisticService.getInstance();

    private final ScheduledExecutorService ticker = Executors.newSingleThreadScheduledExecutor();

    private final ExecutorService workers = Executors.newFixedThreadPool(Settings.SimulationConfig.SCHEDULER_POOL_SIZE);

    private int tactCount = 0;

    public void start() {
        ticker.scheduleAtFixedRate(
                this::tick,
                Settings.SimulationConfig.SCHEDULER_INITIAL_DELAY,
                Settings.SimulationConfig.SCHEDULER_DELAY,
                Settings.SimulationConfig.SCHEDULER_TIMEUNIT
        );

        System.out.println("Simulation is starting... \n");
    }

    private void tick() {
        tactCount++;

        System.out.printf("TICK #%d started\n", tactCount);

        long tickStart = System.currentTimeMillis();

        final List<Location> locations = island.getAllLocations();
        final CountDownLatch counter = new CountDownLatch(locations.size());

        for (Location location : locations) {
            workers.submit(() -> {
                try {
                    new LocationTask(location, island).run();
                } finally {
                    counter.countDown();
                }
            });
        }

        try {
            counter.await();
            statisticService.showStatistics();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long tickEnd = System.currentTimeMillis();

        System.out.printf("TICK #%d ended, took %d ms\n\n", tactCount, tickEnd - tickStart);
    }
}
