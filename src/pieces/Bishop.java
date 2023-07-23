package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public class Bishop implements PiecesInterface {
    Image white_bishop = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_bishop.png")));
    Image black_bishop = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_bishop.png")));
    private final String color;
    private final String value;

    public Bishop(String color) {
        this.color = color;
        this.value = "Bis";
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation) {
        if ((outgoingLocation.get(0) - incomingLocation.get(0)) == (outgoingLocation.get(1) - incomingLocation.get(1))) {
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
            return new ImageView(white_bishop);
        } else if (Objects.equals(color, "black")) {
            return new ImageView(black_bishop);
        }
        return null;
    }

    @Override
    public String getValue() {
        return value;
    }
}
