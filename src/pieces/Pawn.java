package pieces;

import java.util.ArrayList;

public class Pawn implements PiecesInterface{
    private final String color;

    public Pawn(String color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation) {
        return false;
    }
}
