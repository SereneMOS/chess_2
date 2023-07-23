package pieces;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public interface PiecesInterface {
    boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation);
    String getColor();
    ImageView getGraphic(String color);
    String getValue();
}
