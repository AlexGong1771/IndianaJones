import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Main game class
public class IndianaJonesGame extends JFrame {
    private static final int GRID_SIZE = 10;
    private JPanel[][] gridPanels;
    private IndianaJones indianaJones;
    private List<Ghost> ghosts;
    private Treasure treasure;
    private List<Wall> walls;
    private Pitch pitch;
    private Timer ghostMoveTimer;

    private ImagePanel ImagePanel = new ImagePanel();
    public IndianaJonesGame() {
        setTitle("Indiana Jones Treasure Hunt");

        add(ImagePanel);
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        pitch = new Pitch(GRID_SIZE);
        gridPanels = new JPanel[GRID_SIZE][GRID_SIZE];
        initializeGrid();

        indianaJones = new IndianaJones(new Position(0, 0, pitch), pitch);
        ghosts = new ArrayList<>();
        initializeGhosts();

        walls = new ArrayList<>();
        initializeWalls();

        // Initialize the treasure at a fixed position (could randomize later)
        treasure = new Treasure(new Position(5, 5, pitch));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 3));

        JButton upButton = new JButton("Left");
        JButton downButton = new JButton("Right");
        JButton leftButton = new JButton("Up");
        JButton rightButton = new JButton("Down");

        upButton.addActionListener(new MoveActionListener(Direction.UP));
        downButton.addActionListener(new MoveActionListener(Direction.DOWN));
        leftButton.addActionListener(new MoveActionListener(Direction.LEFT));
        rightButton.addActionListener(new MoveActionListener(Direction.RIGHT));

        controlPanel.add(new JLabel()); // Placeholder
        controlPanel.add(upButton);
        controlPanel.add(new JLabel()); // Placeholder
        controlPanel.add(leftButton);
        controlPanel.add(downButton);
        controlPanel.add(rightButton);

        add(controlPanel, BorderLayout.SOUTH);

        ghostMoveTimer = new Timer(1000, e -> {
            moveGhosts();
            updateGrid();
        });
        ghostMoveTimer.start();
    }

    private void initializeGrid() {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK) );
                panel.setBackground(new Color(196, 152, 70, 163));
                gridPanels[i][j] = panel;
                gridPanel.add(panel);
            }
        }
        add(gridPanel, BorderLayout.CENTER);
    }

    private void initializeGhosts() {
        ghosts.add(new Ghost(new Position(3, 3, pitch), pitch));
        ghosts.add(new Ghost(new Position(6, 6, pitch), pitch));
        ghosts.add(new Ghost(new Position(5, 5, pitch), pitch));

    }

    private void initializeWalls() {
        int[][] wallPositions = {
                {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7},
                {0, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1},
                {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7},
                {2, 7}, {3, 7}, {4, 7}, {5, 7}, {6, 7},
                {3, 3}, {3, 4}, {3, 5},
                {5, 3}, {5, 4}, {6, 5},
                {2, 5}, {6, 3},
        };

        for (int[] pos : wallPositions) {
            walls.add(new Wall(new Position(pos[0], pos[1], pitch), pitch));
        }

    }

    private boolean isWall(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().isEqual(position)) {
                return true;
            }
        }
        return false;
    }

    private void updateGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridPanels[i][j].removeAll();
            }
        }

        Position indianaPosition = indianaJones.getPosition();
        gridPanels[indianaPosition.getX()][indianaPosition.getY()].add(new JLabel(indianaJones.getIcon()));

        for (Ghost ghost : ghosts) {
            Position ghostPosition = ghost.getPosition();
            gridPanels[ghostPosition.getX()][ghostPosition.getY()].add(new JLabel(ghost.getIcon()));
        }
        for (var wall : walls) {
            Position wallPosition = wall.getPosition();
            gridPanels[wallPosition.getX()][wallPosition.getY()].add(new JLabel(wall.getIcon()));
        }

        // Add the treasure to the grid
        Position treasurePosition = treasure.getPosition();
        gridPanels[treasurePosition.getX()][treasurePosition.getY()].add(new JLabel(treasure.getIcon()));

        // Check if Indiana Jones has collected the treasure
        if (indianaPosition.isEqual(treasurePosition)) {
            JOptionPane.showMessageDialog(this, "You found the treasure!");
            System.exit(0); // Exit the application
        }

        revalidate();
        repaint();
    }

    private void moveGhosts() {
        for (Ghost ghost : ghosts) {
            ghost.moveRandom();
            if (ghost.getPosition().isEqual(indianaJones.getPosition())) {
                // Handle collision with Indiana Jones
                indianaJones.setPosition(new Position(0, 0, pitch)); // Reset Indiana Jones to start
                ghost.destroy(ghost.getPosition()); // Reset ghost to start
            }
        }
    }

    private class MoveActionListener implements ActionListener {
        private Direction direction;

        public MoveActionListener(Direction direction) {
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Position newPosition = direction.moving(indianaJones.getPosition());
            if (!isWall(newPosition)) { // Check if the new position is not a wall
                indianaJones.move(direction);
            }
            updateGrid();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IndianaJonesGame game = new IndianaJonesGame();
            game.setVisible(true);
        });
    }
}
