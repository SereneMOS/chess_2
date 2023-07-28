package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Pawn implements PiecesInterface {
    Image white_pawn = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_pawn.png")));
    Image black_pawn = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_pawn.png")));
    private final String color;
    private final String value;

    public Pawn(String color) {
        this.color = color;
        this.value = "Pawn";
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation, HashMap<ArrayList<Integer>, PiecesInterface> board) {
        //this configuration doesn't currently account for the first turn, two spaces rule
        if (Objects.equals(color, "white")) {
            return outgoingLocation.get(0) - incomingLocation.get(0) == 1 && outgoingLocation.get(1) - incomingLocation.get(1) == 0;
        } else if (Objects.equals(color, "black")) {
            return outgoingLocation.get(0) - incomingLocation.get(0) == -1 && outgoingLocation.get(1) - incomingLocation.get(1) == 0;
        }
        return false;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public ImageView getGraphic(String color) {
        if (Objects.equals(color, "white")) {
            return new ImageView(white_pawn);
        } else if (Objects.equals(color, "black")) {
            return new ImageView(black_pawn);
        }
        return null;
    }

    @Override
    public String getValue() {
        return value;
    }
}
