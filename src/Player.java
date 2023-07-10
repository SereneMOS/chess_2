import java.util.ArrayList;

public class Player {
    private ArrayList<String> PIECES;
    private ArrayList<String> CAPTURED_PIECES;
    public String SELECTED_PIECE;
    public int ID;

    public Player(int id) {
        this.SELECTED_PIECE = null;
        this.CAPTURED_PIECES = null;
        this.PIECES = populateInitialPieces();
        this.ID = id;
    }

    public void enemyPieceCaptured(String capturedPiece) {
        CAPTURED_PIECES.add(capturedPiece);
    }

    public void pieceLost(String lostPiece) {
        PIECES.remove(lostPiece);
    }

    public void setSelectedPiece(String piece) {
        SELECTED_PIECE = piece;
    }

    public ArrayList<String> populateInitialPieces() {
        ArrayList<String> startingPieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            startingPieces.add("P");
        }
        startingPieces.add("R");
        startingPieces.add("R");
        startingPieces.add("K");
        startingPieces.add("K");
        startingPieces.add("B");
        startingPieces.add("B");
        startingPieces.add("Q");
        startingPieces.add("K");
        return startingPieces;
    }

    public ArrayList<String> getPIECES() {
        return PIECES;
    }
}
