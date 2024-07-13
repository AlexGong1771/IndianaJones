public class Pitch {
    private int size;
    private Position[][] positions;

    public Pitch(int size) {
        this.size = size;
        this.positions = new Position[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                positions[i][j] = new Position(i, j, this);
            }
        }
    }

    public int getSize() {
        return size;
    }
}
