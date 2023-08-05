import pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Board {
    /**
     * Class defining the board
     * Methods for populating the board with pieces and toString
     * Rosaline Flowers
     */
    private final int ROWS;
    private final int COL;
    HashMap<ArrayList<Integer>, PiecesInterface> board = new HashMap<>();
    private String turn;

    public Board(int rows, int columns) {
        this.ROWS = rows;
        this.COL = columns;
        //create an empty board of the provided dimensions
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COL; j++) {
                ArrayList<Integer> coords = new ArrayList<>();
                coords.add(i);
                coords.add(j);
                board.put(coords, new EmptySpace());
            }
        }
        ArrayList<Integer> place = new ArrayList<>();
        place.add(0); place.add(0);
        board.replace(place, new Rook("black"));
        place.clear();
        place.add(0); place.add(1);
        board.replace(place, new Knight("black"));
        place.clear();
        place.add(0); place.add(2);
        board.replace(place, new Bishop("black"));
        place.clear();
        place.add(0); place.add(3);
        board.replace(place, new Queen("black"));
        place.clear();
        place.add(0); place.add(4);
        board.replace(place, new King("black"));
        place.clear();
        place.add(0); place.add(5);
        board.replace(place, new Bishop("black"));
        place.clear();
        place.add(0); place.add(6);
        board.replace(place, new Knight("black"));
        place.clear();
        place.add(0); place.add(7);
        board.replace(place, new Rook("black"));
        place.clear();
        for (int p = 0; p < 8; p++) {
            place.add(1); place.add(p);
            board.replace(place, new Pawn("black"));
            place.clear();
        }
        place.add(7); place.add(0);
        board.replace(place, new Rook("white"));
        place.clear();
        place.add(7); place.add(1);
        board.replace(place, new Knight("white"));
        place.clear();
        place.add(7); place.add(2);
        board.replace(place, new Bishop("white"));
        place.clear();
        place.add(7); place.add(3);
        board.replace(place, new Queen("white"));
        place.clear();
        place.add(7); place.add(4);
        board.replace(place, new King("white"));
        place.clear();
        place.add(7); place.add(5);
        board.replace(place, new Bishop("white"));
        place.clear();
        place.add(7); place.add(6);
        board.replace(place, new Knight("white"));
        place.clear();
        place.add(7); place.add(7);
        board.replace(place, new Rook("white"));
        place.clear();
        for (int p = 0; p < 8; p++) {
            place.add(6); place.add(p);
            board.replace(place, new Pawn("white"));
            place.clear();
        }
        turn = "white";
    }

    public HashMap<ArrayList<Integer>, PiecesInterface> getBoard() {
        return board;
    }

    public void changeTurn() {
        if (Objects.equals(turn, "white")) {
            turn = "black";
        } else if (Objects.equals(turn, "black")) {
            turn = "white";
        }
    }

    public String getTurn() {
        return turn;
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
