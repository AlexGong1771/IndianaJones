import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;

    public ImagePanel() {
        try {
            // Load your image
            backgroundImage = ImageIO.read(new File("C:\\Users\\42194\\workspace\\IndianaJones\\src\\floor.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Image Background Example");
//        ImagePanel panel = new ImagePanel();
//        frame.add(panel);
//        frame.setSize(400, 300);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
}
