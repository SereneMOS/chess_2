package pieces;

import java.util.ArrayList;

public class Knight implements PiecesInterface{
    private final String color;

    public Knight(String color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation) {
        return false;
    }
}
