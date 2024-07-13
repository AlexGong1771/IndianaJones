public class Position {
    private int x, y;
    private Pitch pitch;

    public Position(int x, int y, Pitch pitch) {
        this.x = x;
        this.y = y;
        this.pitch = pitch;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isValidPosition() {
        return x >= 0 && x < pitch.getSize() && y >= 0 && y < pitch.getSize();
    }

    public boolean isEqual(Position other) {
        return x == other.x && y == other.y;
    }

    public Position newPosition(int moveX, int moveY) {
        return new Position(x + moveX, y + moveY, pitch);
    }
}