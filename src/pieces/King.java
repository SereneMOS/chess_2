package pieces;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class for King objects, which extends PiecesInterface
 * This class contains all the logic and properties that a king piece in the game has
 * Rosaline Flowers
 */
public class King implements PiecesInterface {
    Image white_king = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_king.png")));
    Image black_king = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_king.png")));
    private final String color;
    private final String value;

    /**
     * Constructor for the king
     * @param color the king's color
     */
    public King(String color) {
        this.color = color;
        this.value = "King";
    }

    /**
     * Determines if the king is moving only one space in any direction
     * If he is trying to move more than that, the method returns false
     * @param outgoingLocation the coordinates the piece is leaving from
     * @param incomingLocation the coordinates the piece is going to
     * @param board the hashmap containing the data of the chess board
     * @return bool
     */
    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation, HashMap<ArrayList<Integer>, PiecesInterface> board) {
                //upwards and downwards movement
        return ((outgoingLocation.get(0) - incomingLocation.get(0) == 1 || outgoingLocation.get(0) - incomingLocation.get(0) == -1)
                && (outgoingLocation.get(1) - incomingLocation.get(1) <= 1) && (outgoingLocation.get(1) - incomingLocation.get(1) >= -1))
                //left and right movement
                || (outgoingLocation.get(1) - incomingLocation.get(1) == 1 && outgoingLocation.get(0) - incomingLocation.get(0) == 0)
                || (outgoingLocation.get(1) - incomingLocation.get(1) == -1 && outgoingLocation.get(0) - incomingLocation.get(0) == 0);
    }

    /**
     * Returns the color of the piece
     * @return String color
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * Returns the king's image, changing it based on its color
     * @return Image
     */
    @Override
    public ImageView getGraphic() {
        if (Objects.equals(this.color, "white")) {
            return new ImageView(white_king);
        } else if (Objects.equals(this.color, "black")) {
            return new ImageView(black_king);
        }
        return null;
    }

    /**
     * Returns the string value of the piece (basically its name)
     * @return String value
     */
    @Override
    public String getValue() {
        return value;
    }
}
