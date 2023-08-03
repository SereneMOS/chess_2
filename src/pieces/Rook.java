package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Rook implements PiecesInterface {
    Image white_rook = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_rook.png")));
    Image black_rook = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_rook.png")));
    private final String color;
    private final String value;

    public Rook(String color) {
        this.color = color;
        this.value = "Rook";
    }

    @Override
    public boolean isValidMove(ArrayList<Integer> outgoingLocation, ArrayList<Integer> incomingLocation, HashMap<ArrayList<Integer>, PiecesInterface> board) {
        //horizontal movement
        if ((outgoingLocation.get(0) - incomingLocation.get(0) == 0 && outgoingLocation.get(1) - incomingLocation.get(1) !=0)) {
            //check every space between the outgoing and incoming locations and if any of them are not blank, return false
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
            // vertical movement
        } else if (outgoingLocation.get(1) - incomingLocation.get(1) == 0 && outgoingLocation.get(0) - incomingLocation.get(0) != 0) {
            if (incomingLocation.get(0) > outgoingLocation.get(0)) {
                for (int i = outgoingLocation.get(0) + 1; i < incomingLocation.get(0); i++) {
                    ArrayList<Integer> current = new ArrayList<>();
                    current.add(i);
                    current.add(outgoingLocation.get(1) + i);
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
        }
        return false;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public ImageView getGraphic(String color) {
        if (Objects.equals(color, "white")) {
            return new ImageView(white_rook);
        } else if (Objects.equals(color, "black")) {
            return new ImageView(black_rook);
        }
        return null;
    }

    @Override
    public String getValue() {
        return value;
    }

}
