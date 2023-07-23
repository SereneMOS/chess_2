package pieces;

import java.util.ArrayList;

public class Rook implements PiecesInterface{
    private final String color;

    public Rook(String color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation) {
        return false;
    }
}
