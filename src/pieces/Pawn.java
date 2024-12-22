package pieces;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class for Pawn objects, which extends PiecesInterface
 * This class contains all the logic and properties that a pawn piece in the game has
 * Rosaline Flowers
 */
public class Pawn implements PiecesInterface {
    Image white_pawn = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_pawn.png")));
    Image black_pawn = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_pawn.png")));
    private final String color;
    private final String value;
    private boolean hasMoved;

    /**
     * The constructor for the pawn
     * @param color the pawn's color
     */
    public Pawn(String color) {
        this.color = color;
        this.value = "Pawn";
        this.hasMoved = false;
    }

    /**
     * Determines if the Pawn is moving forward by only a single space
     * Obviously I need to come back and both make it to where it can't capture forward, and it can move twice on the first move
     * Returns false if the pawn tries to move more than a single space up or down, depending on its color
     * @param outgoingLocation the coordinates the piece is leaving from
     * @param incomingLocation the coordinates the piece is going to
     * @param board the hashmap containing the data of the chess board
     * @return bool
     */
    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation, HashMap<ArrayList<Integer>, PiecesInterface> board) {
        if (Objects.equals(color, "white")) {
            if (outgoingLocation.get(0) - incomingLocation.get(0) == 1 && (outgoingLocation.get(1) - incomingLocation.get(1) == 1
                    || outgoingLocation.get(1) - incomingLocation.get(1) == -1) && Objects.equals(board.get(incomingLocation).getColor(), "black")) {
                return true;
            }
            if (outgoingLocation.get(0) - incomingLocation.get(0) == 2 && outgoingLocation.get(1) - incomingLocation.get(1) == 0
                    && !hasMoved && Objects.equals(board.get(incomingLocation).getValue(), ".")) {
                hasMoved = true;
                return true;
            } else if (outgoingLocation.get(0) - incomingLocation.get(0) == 1 && outgoingLocation.get(1) - incomingLocation.get(1) == 0
                    && Objects.equals(board.get(incomingLocation).getValue(), ".")) {
                hasMoved = true;
                return true;
            }
        } else if (Objects.equals(color, "black")) {
            if (outgoingLocation.get(0) - incomingLocation.get(0) == -1 && (outgoingLocation.get(1) - incomingLocation.get(1) == 1
                    || outgoingLocation.get(1) - incomingLocation.get(1) == -1) && Objects.equals(board.get(incomingLocation).getColor(), "white")) {
                return true;
            }
            if (outgoingLocation.get(0) - incomingLocation.get(0) == -2 && outgoingLocation.get(1) - incomingLocation.get(1) == 0
                    && !hasMoved && Objects.equals(board.get(incomingLocation).getValue(), ".")) {
                hasMoved = true;
                return true;
            } else if (outgoingLocation.get(0) - incomingLocation.get(0) == -1 && outgoingLocation.get(1) - incomingLocation.get(1) == 0
                    && Objects.equals(board.get(incomingLocation).getValue(), ".")) {
                hasMoved = true;
                return true;
            }
        }
        return false;
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
     * Returns the pawn's image, changing it based on its color
     * @return Image
     */
    @Override
    public ImageView getGraphic() {
        if (Objects.equals(this.color, "white")) {
            return new ImageView(white_pawn);
        } else if (Objects.equals(this.color, "black")) {
            return new ImageView(black_pawn);
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
