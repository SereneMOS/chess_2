import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private final int ROWS;
    private final int COL;
    HashMap<ArrayList, String> board = new HashMap<>();

    public Board(int rows, int columns) {
        this.ROWS = rows;
        this.COL = columns;
        //create an empty board of the provided dimensions
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COL; j++) {
                ArrayList<Integer> coords = new ArrayList<>();
                coords.add(i);
                coords.add(j);
                board.put(coords, ".");
            }
        }

    }

    public HashMap<ArrayList, String> getBoard() {
        return board;
    }

    public String toString() {
        StringBuilder printer = new StringBuilder();
        for (int i = 0; i < this.ROWS; i++) {
            for (int j = 0; j < this.COL; j++) {
                ArrayList<Integer> coords = new ArrayList<>();
                coords.add(i);
                coords.add(j);
                printer.append(board.get(coords));
                printer.append(" ");
            }
            printer.append("\n");
        }
        return printer.toString();
    }
}
