package entity.type;

public enum DirectionsType {
    UP(0, -1),

    DOWN(0, 1),

    LEFT(-1, 0),

    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    DirectionsType(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
