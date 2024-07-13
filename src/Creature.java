import javax.swing.*;

public abstract class Creature {
    private Position position;
    private Pitch pitch;

    public Creature(Position position, Pitch pitch) {
        this.position = position;
        this.pitch = pitch;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public abstract void move(Direction direction);
    public abstract void destroy(Position position);
}