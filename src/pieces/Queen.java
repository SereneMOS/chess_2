package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public class Queen implements PiecesInterface {
    Image white_queen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_queen.png")));
    Image black_queen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_queen.png")));
    private final String color;
    private final String value;

    public Queen(String color) {
        this.color = color;
        this.value = "Que";
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation) {
        if ((outgoingLocation.get(0) - incomingLocation.get(0) == 0 && outgoingLocation.get(1) - incomingLocation.get(1) !=0)
                || (outgoingLocation.get(1) - incomingLocation.get(1) == 0 && outgoingLocation.get(0) - incomingLocation.get(0) != 0)
         || (outgoingLocation.get(0) - incomingLocation.get(0)) == (outgoingLocation.get(1) - incomingLocation.get(1))) {
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
            return new ImageView(white_queen);
        } else if (Objects.equals(color, "black")) {
            return new ImageView(black_queen);
        }
        return null;
    }

    @Override
    public String getValue() {
        return value;
    }
}
