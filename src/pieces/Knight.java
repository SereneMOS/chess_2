package pieces;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class for Knight objects, which extends PiecesInterface
 * This class contains all the logic and properties that a knight piece in the game has
 * Rosaline Flowers
 */
public class Knight implements PiecesInterface {
    Image white_knight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_knight.png")));
    Image black_knight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_knight.png")));
    private final String color;
    private final String value;

    /**
     * Constructor for the Knight
     * @param color the knight's color
     */
    public Knight(String color) {
        this.color = color;
        this.value = "Knight";
    }

    /**
     * Determines if the knight is moving in its intended L patterns, if not, the method returns false
     * @param outgoingLocation the coordinates the piece is leaving from
     * @param incomingLocation the coordinates the piece is going to
     * @param board the hashmap containing the data of the chess board
     * @return bool
     */
    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation, HashMap<ArrayList<Integer>, PiecesInterface> board) {
        return (outgoingLocation.get(0) - incomingLocation.get(0) == -2 && (outgoingLocation.get(1) - incomingLocation.get(1) == 1
                || outgoingLocation.get(1) - incomingLocation.get(1) == -1)) || (outgoingLocation.get(0) - incomingLocation.get(0) == 2 &&
                (outgoingLocation.get(1) - incomingLocation.get(1) == 1 || outgoingLocation.get(1) - incomingLocation.get(1) == -1))
                || (outgoingLocation.get(0) - incomingLocation.get(0) == -1 && (outgoingLocation.get(1) - incomingLocation.get(1) == 2
                || outgoingLocation.get(1) - incomingLocation.get(1) == -2)) || (outgoingLocation.get(0) - incomingLocation.get(0) == 1
                && (outgoingLocation.get(1) - incomingLocation.get(1) == 2 || outgoingLocation.get(1) - incomingLocation.get(1) == -2));
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
     * Returns the knight's image, changing it based on its color
     * @return Image
     */
    @Override
    public ImageView getGraphic() {
        if (Objects.equals(this.color, "white")) {
            return new ImageView(white_knight);
        } else if (Objects.equals(this.color, "black")) {
            return new ImageView(black_knight);
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
