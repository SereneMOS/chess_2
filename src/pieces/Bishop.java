package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Bishop implements PiecesInterface {
    Image white_bishop = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_bishop.png")));
    Image black_bishop = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_bishop.png")));
    private final String color;
    private final String value;

    public Bishop(String color) {
        this.color = color;
        this.value = "Bishop";
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation, HashMap<ArrayList<Integer>, PiecesInterface> board) {
        // "\" movement
        if ((outgoingLocation.get(0) - incomingLocation.get(0)) == (outgoingLocation.get(1) - incomingLocation.get(1))) {
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
            // "/" movement
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

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public ImageView getGraphic() {
        if (Objects.equals(this.color, "white")) {
            return new ImageView(white_bishop);
        } else if (Objects.equals(this.color, "black")) {
            return new ImageView(black_bishop);
        }
        return null;
    }

    @Override
    public String getValue() {
        return value;
    }
}
