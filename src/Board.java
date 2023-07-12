import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    /**
     * Class defining the board
     * Methods for populating the board with pieces and toString
     * Rosaline Flowers
     */
    private final int ROWS;
    private final int COL;
    HashMap<ArrayList<Integer>, String> board = new HashMap<>();

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
        ArrayList<Integer> place = new ArrayList<>();
        place.add(0); place.add(0);
        board.replace(place, "R");
        place.clear();
        place.add(0); place.add(1);
        board.replace(place, "K");
        place.clear();
        place.add(0); place.add(2);
        board.replace(place, "B");
        place.clear();
        place.add(0); place.add(3);
        board.replace(place, "Q");
        place.clear();
        place.add(0); place.add(4);
        board.replace(place, "J");
        place.clear();
        place.add(0); place.add(5);
        board.replace(place, "B");
        place.clear();
        place.add(0); place.add(6);
        board.replace(place, "K");
        place.clear();
        place.add(0); place.add(7);
        board.replace(place, "R");
        place.clear();
        for (int p = 0; p < 8; p++) {
            place.add(1); place.add(p);
            board.replace(place, "P");
            place.clear();
        }
        place.add(7); place.add(0);
        board.replace(place, "R");
        place.clear();
        place.add(7); place.add(1);
        board.replace(place, "K");
        place.clear();
        place.add(7); place.add(2);
        board.replace(place, "B");
        place.clear();
        place.add(7); place.add(3);
        board.replace(place, "Q");
        place.clear();
        place.add(7); place.add(4);
        board.replace(place, "J");
        place.clear();
        place.add(7); place.add(5);
        board.replace(place, "B");
        place.clear();
        place.add(7); place.add(6);
        board.replace(place, "K");
        place.clear();
        place.add(7); place.add(7);
        board.replace(place, "R");
        place.clear();
        for (int p = 0; p < 8; p++) {
            place.add(6); place.add(p);
            board.replace(place, "P");
            place.clear();
        }
    }

    public HashMap<ArrayList<Integer>, String> getBoard() {
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
            }
            printer.append("\n");
        }
        return printer.toString();
    }
}
