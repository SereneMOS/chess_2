package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public class King implements PiecesInterface {
    Image white_king = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_king.png")));
    Image black_king = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_king.png")));
    private final String color;
    private final String value;

    public King(String color) {
        this.color = color;
        this.value = "King";
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation) {
        if (Objects.equals(color, "white")) {
            if (outgoingLocation.get(0) - incomingLocation.get(0) == 1 || outgoingLocation.get(0) - incomingLocation.get(0) == -1
             || outgoingLocation.get(1) - incomingLocation.get(1) == 1 || outgoingLocation.get(1) - incomingLocation.get(1) == -1) {
                return true;
            }
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
            return new ImageView(white_king);
        } else if (Objects.equals(color, "black")) {
            return new ImageView(black_king);
        }
        return null;
    }

    @Override
    public String getValue() {
        return value;
    }
}
