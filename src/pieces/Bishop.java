package pieces;

import java.util.ArrayList;

public class Bishop implements PiecesInterface{
    private final String color;

    public Bishop(String color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation) {
        return false;
    }
}
