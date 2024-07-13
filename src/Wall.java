import javax.swing.*;

public class Wall extends Creature {
    private ImageIcon icon;

    public Wall(Position position, Pitch pitch) {
        super(position, pitch);
        this.icon = new ImageIcon("C:\\Users\\42194\\workspace\\IndianaJones\\src\\wall.jpg"); // Path to wall image
    }

    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public void move(Direction direction) {
        // Walls do not move
    }

    @Override
    public void destroy(Position position) {
        // Logic for destroying a wall if needed (not typically used in this context)
    }
}
