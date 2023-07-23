package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public class Rook implements PiecesInterface {
    Image white_rook = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_rook.png")));
    Image black_rook = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_rook.png")));
    private final String color;
    private final String value;

    public Rook(String color) {
        this.color = color;
        this.value = "Rook";
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation) {
        if ((outgoingLocation.get(0) - incomingLocation.get(0) == 0 && outgoingLocation.get(1) - incomingLocation.get(1) !=0)
         || (outgoingLocation.get(1) - incomingLocation.get(1) == 0 && outgoingLocation.get(0) - incomingLocation.get(0) != 0)) {
            return true;
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
            return new ImageView(white_rook);
        } else if (Objects.equals(color, "black")) {
            return new ImageView(black_rook);
        }
        return null;
    }

    @Override
    public String getValue() {
        return value;
    }

}
