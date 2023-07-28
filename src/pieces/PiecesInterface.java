package pieces;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public interface PiecesInterface {
    boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation, HashMap<ArrayList<Integer>, PiecesInterface> board);
    String getColor();
    ImageView getGraphic(String color);
    String getValue();
}
