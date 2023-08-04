package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Queen implements PiecesInterface {
    Image white_queen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_queen.png")));
    Image black_queen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/black_queen.png")));
    private final String color;
    private final String value;

    public Queen(String color) {
        this.color = color;
        this.value = "Que";
    }

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

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public ImageView getGraphic(String color) {
        if (Objects.equals(color, "white")) {
            return new ImageView(white_queen);
        } else if (Objects.equals(color, "black")) {
            return new ImageView(black_queen);
        }
        return null;
    }

    @Override
    public String getValue() {
        return value;
    }
}
