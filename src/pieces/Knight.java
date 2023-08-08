package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Knight implements PiecesInterface {
    Image white_knight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_knight.png")));
    Image black_knight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_knight.png")));
    private final String color;
    private final String value;

    public Knight(String color) {
        this.color = color;
        this.value = "Knight";
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation, HashMap<ArrayList<Integer>, PiecesInterface> board) {
        if ((outgoingLocation.get(0) - incomingLocation.get(0) == -2 && (outgoingLocation.get(1) - incomingLocation.get(1) == 1
         || outgoingLocation.get(1) - incomingLocation.get(1) == -1)) || (outgoingLocation.get(0) - incomingLocation.get(0) == 2 &&
                (outgoingLocation.get(1) - incomingLocation.get(1) == 1 || outgoingLocation.get(1) - incomingLocation.get(1) == -1))
        || (outgoingLocation.get(0) - incomingLocation.get(0) == -1 && (outgoingLocation.get(1) - incomingLocation.get(1) == 2
                || outgoingLocation.get(1) - incomingLocation.get(1) == -2)) || (outgoingLocation.get(0) - incomingLocation.get(0) == 1
        && (outgoingLocation.get(1) - incomingLocation.get(1) == 2 || outgoingLocation.get(1) - incomingLocation.get(1) == -2))) {
            return true;
        }
        return false;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public ImageView getGraphic() {
        if (Objects.equals(this.color, "white")) {
            return new ImageView(white_knight);
        } else if (Objects.equals(this.color, "black")) {
            return new ImageView(black_knight);
        }
        return null;
    }

    @Override
    public String getValue() {
        return value;
    }
}
