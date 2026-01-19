package entity.island;

public class Island {
    private final Integer islandWidth;
    private final Integer islandHeight;
    private final Location[][] locations;

    public Island(Integer width, Integer height) {
        this.islandWidth = width;
        this.islandHeight = height;
        this.locations = new Location[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                locations[y][x] = new Location(x, y);
            }
        }
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
}
