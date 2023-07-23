package pieces;

import java.util.ArrayList;

public class King implements PiecesInterface{
    private final String color;

    public King(String color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation) {
        return false;
    }
}
