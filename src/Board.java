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
    private final Player player1 = new Player("white");
    private final Player player2 = new Player("black");

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

    public String produceProperCoords(ArrayList<Integer> outgoing, ArrayList<Integer> incoming) {
        String properCoords = "";
        String firstCoord = switch (outgoing.get(0)) {
            case 0 -> "8";
            case 1 -> "7";
            case 2 -> "6";
            case 3 -> "5";
            case 4 -> "4";
            case 5 -> "3";
            case 6 -> "2";
            case 7 -> "1";
            default -> "";
        };
        String secondCoord = switch (outgoing.get(1)) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            case 4 -> "e";
            case 5 -> "f";
            case 6 -> "g";
            case 7 -> "h";
            default -> "";
        };
        String thirdCoord = switch (incoming.get(0)) {
            case 0 -> "8";
            case 1 -> "7";
            case 2 -> "6";
            case 3 -> "5";
            case 4 -> "4";
            case 5 -> "3";
            case 6 -> "2";
            case 7 -> "1";
            default -> "";
        };
        String fourthCoord = switch (incoming.get(1)) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            case 4 -> "e";
            case 5 -> "f";
            case 6 -> "g";
            case 7 -> "h";
            default -> "";
        };
        properCoords = firstCoord + secondCoord + " to " + thirdCoord + fourthCoord;
        return properCoords;
    }

    public Player getPlayer(String color) {
        if (Objects.equals(color, "white")) {
            return player1;
        } else if (Objects.equals(color, "black")) {
            return player2;
        }
        return null;
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
