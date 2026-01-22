package entity.island;

import config.Settings;

import java.util.ArrayList;
import java.util.List;

public final class Island {
    private static Island instance;

    private final Integer islandWidth;
    private final Integer islandHeight;
    private final Location[][] locations;
    private final List<Location> locationsList;

    private Island() {
        this.islandWidth = Settings.MapConfig.MAP_SIZE_X;
        this.islandHeight = Settings.MapConfig.MAP_SIZE_Y;
        this.locations = new Location[islandHeight][islandWidth];
        this.locationsList = new ArrayList<>();

        for (int y = 0; y < islandHeight; y++) {
            for (int x = 0; x < islandWidth; x++) {
                Location newLocation = new Location(x, y);
                locations[y][x] = newLocation;
                locationsList.add(newLocation);
            }
        }
    }

    public static synchronized Island getInstance() {
        if (instance == null) {
            instance = new Island();
        }
        return instance;
    }

    public Integer getIslandWidth() {
        return islandWidth;
    }

    public Integer getIslandHeight() {
        return islandHeight;
    }

    public Location getLocation(Integer cordX, Integer cordY) {
        if ((cordX >= 0 && cordX < islandWidth) && (cordY >= 0 && cordY < islandHeight)) {
            return locations[cordY][cordX];
        }
        return null;
    }

    public List<Location> getAllLocations() {
        return locationsList;
    }
}
