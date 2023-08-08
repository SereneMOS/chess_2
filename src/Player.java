import pieces.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private final ArrayList<PiecesInterface> pieces;
    private final ArrayList<PiecesInterface> captured_pieces;
    public String color;

    public Player(String color) {
        this.captured_pieces = null;
        this.pieces = populateInitialPieces();
        this.color = color;
    }

    public void enemyPieceCaptured(PiecesInterface capturedPiece) {
        captured_pieces.add(capturedPiece);
    }

    public void pieceLost(PiecesInterface lostPiece) {
        pieces.remove(lostPiece);
    }

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

    public ArrayList<PiecesInterface> getPieces() {
        return pieces;
    }

    public ArrayList<PiecesInterface> getCapturedPieces(){return captured_pieces;}

    public String getColor(){return color;}
}
