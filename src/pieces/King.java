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
        if (!isInCheckOrMate(incomingLocation, board)) {
            //upwards and downwards movement
            return ((outgoingLocation.get(0) - incomingLocation.get(0) == 1 || outgoingLocation.get(0) - incomingLocation.get(0) == -1)
                    && (outgoingLocation.get(1) - incomingLocation.get(1) <= 1) && (outgoingLocation.get(1) - incomingLocation.get(1) >= -1))
                    //left and right movement
                    || (outgoingLocation.get(1) - incomingLocation.get(1) == 1 && outgoingLocation.get(0) - incomingLocation.get(0) == 0)
                    || (outgoingLocation.get(1) - incomingLocation.get(1) == -1 && outgoingLocation.get(0) - incomingLocation.get(0) == 0);
        } else {
            return false;
        }
    }

    /**
     * First, determine if a king is in check- to do so, determine if any enemy piece has a viable line of attack to that king
     * Second, once the king is determined to be in check, then determine if the king has any move that will allow it to break check
     * If not, then we need to determine if there are any ally pieces which could move in and break check
     * If not, then the king is in checkmate
     * Notes: Only one piece (lol) can actually threaten a king at a time. Other pieces can block off escape options, but only one
     * can actually have a line of attack on a king
     * A king should not be able to move itself into check or checkmate, nor should a piece be able to move such that it puts its king in check
     * The only move a player can make when their king is in check is to get out of it
     * @param currentLocation
     * @param board
     * @return
     */
    public boolean isInCheckOrMate(ArrayList<Integer> currentLocation, HashMap<ArrayList<Integer>, PiecesInterface> board) {
            for (ArrayList<Integer> enemyPiece : board.keySet()) {
                if (!Objects.equals(board.get(enemyPiece).getColor(), this.color) && !Objects.equals(board.get(enemyPiece).getValue(), ".")) {
                    if (Objects.equals(board.get(enemyPiece).getValue(), "King")) {
                        System.out.println("Enemy King");
                    } else {
                        if (board.get(enemyPiece).isValidMove(enemyPiece, currentLocation, board)) {
                            return true;
                        }
                    }
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
