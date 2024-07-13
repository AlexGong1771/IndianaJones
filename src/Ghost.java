import javax.swing.*;
import java.util.Random;

public class Ghost extends Creature {
    private ImageIcon icon;
    private Random random;

    public Ghost(Position position, Pitch pitch) {
        super(position, pitch);
        this.icon = new ImageIcon("C:\\Users\\42194\\workspace\\IndianaJones\\src\\ghoast.jpg"); // Add actual image path
        this.random = new Random();
    }

    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public void move(Direction direction) {
        Position newPosition = direction.moving(this.getPosition());
        if (newPosition.isValidPosition() && !newPosition.isEqual(this.getPosition())) {
            this.setPosition(newPosition);
        }
    }

    public void moveRandom() {
        Direction[] directions = Direction.values();
        Direction randomDirection = directions[random.nextInt(directions.length)];
        move(randomDirection);
    }

    @Override
    public void destroy(Position position) {
        // Logic for respawning the ghost
        this.setPosition(new Position(0, 0, this.getPitch()));
    }
}
