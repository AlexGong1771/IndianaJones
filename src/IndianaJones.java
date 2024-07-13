import javax.swing.*;

public class IndianaJones extends Creature {
    private ImageIcon icon;

    public IndianaJones(Position position, Pitch pitch) {
        super(position, pitch);
        this.icon = new ImageIcon("C:\\Users\\42194\\workspace\\IndianaJones\\src\\IndianaJones.jpg"); // Add actual image path
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

    @Override
    public void destroy(Position position) {
        // Destruction logic (e.g., removing walls)
    }
}