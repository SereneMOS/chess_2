import pieces.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that instantiates the fields and methods for Player objects
 * These objects simulate the individual players, as well as their collection of pieces
 * Rosaline Flowers
 */
public class Player {
    private final ArrayList<PiecesInterface> pieces;
    private final ArrayList<PiecesInterface> capturedPieces;
    public String color;

    /**
     * Player constructor
     * @param color the color (or team, really) of the player
     */
    public Player(String color) {
        this.capturedPieces = null;
        this.pieces = populateInitialPieces();
        this.color = color;
    }

    /**
     * Adds a captured enemy piece to the player's list of captured pieces
     * @param capturedPiece the piece that was captured
     */
    public void enemyPieceCaptured(PiecesInterface capturedPiece) {
        capturedPieces.add(capturedPiece);
    }

    /**
     * Removes a piece from the player when it is captured
     * @param lostPiece the piece that was lost
     */
    public void pieceLost(PiecesInterface lostPiece) {
        pieces.remove(lostPiece);
    }

    /**
     * Adds all the pieces the player will be starting with to their list
     * The pieces' color changes depending on the player's
     * @return ArrayList<PiecesInterface> startingPieces
     */
    public ArrayList<PiecesInterface> populateInitialPieces() {
        ArrayList<PiecesInterface> startingPieces = new ArrayList<>();
        if (Objects.equals(color, "white")) {
            for (int i = 0; i < 8; i++) {
                startingPieces.add(new Pawn("white"));
            }
            startingPieces.add(new Rook("white"));
            startingPieces.add(new Rook("white"));
            startingPieces.add(new Knight("white"));
            startingPieces.add(new Knight("white"));
            startingPieces.add(new Bishop("white"));
            startingPieces.add(new Bishop("white"));
            startingPieces.add(new Queen("white"));
            startingPieces.add(new King("white"));
        } else if (Objects.equals(color, "black")) {
            for (int i = 0; i < 8; i++) {
                startingPieces.add(new Pawn("black"));
            }
            startingPieces.add(new Rook("black"));
            startingPieces.add(new Rook("black"));
            startingPieces.add(new Knight("black"));
            startingPieces.add(new Knight("black"));
            startingPieces.add(new Bishop("black"));
            startingPieces.add(new Bishop("black"));
            startingPieces.add(new Queen("black"));
            startingPieces.add(new King("black"));
        }
        return startingPieces;
    }

    /**
     * Returns the player's current list of pieces
     * @return ArrayList<PiecesInterface> pieces
     */
    public ArrayList<PiecesInterface> getPieces() {
        return pieces;
    }

    /**
     * Returns the list of pieces the player has captured
     * @return ArrayList<PiecesInterface> capturedPieces
     */
    public ArrayList<PiecesInterface> getCapturedPieces(){return capturedPieces;}

    /**
     * Returns the player's color
     * @return String color
     */
    public String getColor(){return color;}
}
