package pieces;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class for Queen objects, which extends PiecesInterface
 * This class contains all the logic and properties that a queen piece in the game has
 * Rosaline Flowers
 */
public class Queen implements PiecesInterface {
    Image white_queen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_queen.png")));
    Image black_queen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_queen.png")));
    private final String color;
    private final String value;

    /**
     * Constructor for the queen
     * @param color the queen's color
     */
    public Queen(String color) {
        this.color = color;
        this.value = "Queen";
    }

    /**
     * Determines if the queen is moving either diagonally or in a straight line, as well as if that path is clear
     * If any of those things is not true, the method returns false
     * @param outgoingLocation the coordinates the piece is leaving from
     * @param incomingLocation the coordinates the piece is going to
     * @param board the hashmap containing the data of the chess board
     * @return bool
     */
    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation, HashMap<ArrayList<Integer>, PiecesInterface> board) {
        if (outgoingLocation.get(0) - incomingLocation.get(0) == 0 && outgoingLocation.get(1) - incomingLocation.get(1) !=0) {
            if (incomingLocation.get(1) > outgoingLocation.get(1)) {
                for (int i = outgoingLocation.get(1) + 1; i < incomingLocation.get(1); i++) {
                    ArrayList<Integer> current = new ArrayList<>();
                    current.add(outgoingLocation.get(0));
                    current.add(i);
                    if (!Objects.equals(board.get(current).getValue(), ".")) {
                        return false;
                    }
                }
                return true;
            } else if (outgoingLocation.get(1) > incomingLocation.get(1)) {
                for (int i = outgoingLocation.get(1) - 1; i > incomingLocation.get(1); i--) {
                    ArrayList<Integer> current = new ArrayList<>();
                    current.add(outgoingLocation.get(0));
                    current.add(i);
                    if (!Objects.equals(board.get(current).getValue(), ".")) {
                        return false;
                    }
                }
                return true;
            }
        } else if ((outgoingLocation.get(1) - incomingLocation.get(1)) == 0 && (outgoingLocation.get(0) - incomingLocation.get(0) != 0)) {
            if (incomingLocation.get(0) > outgoingLocation.get(0)) {
                for (int i = outgoingLocation.get(0) + 1; i < incomingLocation.get(0); i++) {
                    ArrayList<Integer> current = new ArrayList<>();
                    current.add(i);
                    current.add(outgoingLocation.get(1));
                    if (!Objects.equals(board.get(current).getValue(), ".")) {
                        return false;
                    }
                }
                return true;
            } else if (outgoingLocation.get(0) > incomingLocation.get(0)) {
                for (int i = outgoingLocation.get(0) - 1; i > incomingLocation.get(0); i--) {
                    ArrayList<Integer> current = new ArrayList<>();
                    current.add(i);
                    current.add(outgoingLocation.get(1));
                    if (!Objects.equals(board.get(current).getValue(), ".")) {
                        return false;
                    }
                }
                return true;
            }
        } else if ((outgoingLocation.get(0) - incomingLocation.get(0)) == (outgoingLocation.get(1) - incomingLocation.get(1))) {
            //upward and left
            if (incomingLocation.get(0) < outgoingLocation.get(0)) {
                for (int i = -1; i > incomingLocation.get(0) - outgoingLocation.get(0); i--) {
                    ArrayList<Integer> current = new ArrayList<>();
                    current.add(outgoingLocation.get(0) + i);
                    current.add(outgoingLocation.get(1) + i);
                    if (!Objects.equals(board.get(current).getValue(), ".")) {
                        return false;
                    }
                }
                return true;
                //downward and right
            } else if (incomingLocation.get(0) > outgoingLocation.get(0)) {
                for (int i = 1; i < incomingLocation.get(0) - outgoingLocation.get(0); i++) {
                    ArrayList<Integer> current = new ArrayList<>();
                    current.add(outgoingLocation.get(0) + i);
                    current.add(outgoingLocation.get(1) + i);
                    if (!Objects.equals(board.get(current).getValue(), ".")) {
                        return false;
                    }
                }
                return true;
            }
        } else if ((incomingLocation.get(0) - outgoingLocation.get(0)) == -1 * (incomingLocation.get(1) - outgoingLocation.get(1))) {
            //upward and right
            if (incomingLocation.get(0) < outgoingLocation.get(0)) {
                int x = outgoingLocation.get(1);
                for (int i = outgoingLocation.get(0) - 1; i > incomingLocation.get(0); i--) {
                    ArrayList<Integer> current = new ArrayList<>();
                    current.add(i);
                    x++;
                    current.add(x);
                    if (!Objects.equals(board.get(current).getValue(), ".")) {
                        return false;
                    }
                }
                return true;
                //downward and left
            } else if (incomingLocation.get(0) > outgoingLocation.get(0)) {
                int x = outgoingLocation.get(1);
                for (int i = outgoingLocation.get(0) + 1; i < incomingLocation.get(0); i++) {
                    ArrayList<Integer> current = new ArrayList<>();
                    current.add(i);
                    x--;
                    current.add(x);
                    if (!Objects.equals(board.get(current).getValue(), ".")) {
                        return false;
                    }
                }
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
     * Returns the queen's image, changing it based on its color
     * @return Image
     */
    @Override
    public ImageView getGraphic() {
        if (Objects.equals(this.color, "white")) {
            return new ImageView(white_queen);
        } else if (Objects.equals(this.color, "black")) {
            return new ImageView(black_queen);
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
