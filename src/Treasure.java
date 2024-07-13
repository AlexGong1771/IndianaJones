import javax.swing.*;

public class Treasure {
    private Position position;
    private ImageIcon icon;

    public Treasure(Position position) {
        this.position = position;
        this.icon = new ImageIcon("C:\\Users\\42194\\workspace\\IndianaJones\\src\\Treasure.jpg"); // Add actual image path
    }

    public Position getPosition() {
        return position;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
