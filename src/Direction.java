public enum Direction {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);
    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public Position moving(Position old) {
        return old.newPosition(deltaX, deltaY);
    }
}