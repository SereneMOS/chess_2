package pieces;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.HashMap;

public interface PiecesInterface {
    /**
     * Returns whether a piece's attempted move is valid
     * The outgoing and incoming locations are used to determine if the path of the piece is correct
     * The board is used to determine if there are any pieces in the way of the path of motion
     * @param outgoingLocation the coordinates the piece is leaving from
     * @param incomingLocation the coordinates the piece is going to
     * @param board the hashmap containing the data of the chess board
     * @return bool
     */
    boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation, HashMap<ArrayList<Integer>, PiecesInterface> board);

    /**
     * Returns the color of the piece
     * @return String color
     */
    String getColor();

    /**
     * Returns the piece's associated graphic, which changes based on its color
     * @return Image
     */
    ImageView getGraphic();

    /**
     * Returns the string value of the piece (basically its name)
     * @return String value
     */
    String getValue();
}
