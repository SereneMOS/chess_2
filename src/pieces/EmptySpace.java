package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class EmptySpace implements PiecesInterface {
    Image space = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/space_3.png")));
    private final String value;

    public EmptySpace() {
        this.value = ".";
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation, HashMap<ArrayList<Integer>, PiecesInterface> board) {
        return false;
    }

    @Override
    public String getColor() {
        return null;
    }

    @Override
    public ImageView getGraphic() {
        return new ImageView(space);
    }

    @Override
    public String getValue() {
        return value;
    }
}
