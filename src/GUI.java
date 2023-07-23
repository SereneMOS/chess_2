import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GUI extends Application {
    //TODO Get pieces to move on the board
    //TODO add the black pieces in
    Image space = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/space_3.png")));
    Image white_pawn = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_pawn.png")));
    Image white_rook = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_rook.png")));
    Image white_bishop = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_bishop.png")));
    Image white_knight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_knight.png")));
    Image white_queen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_queen.png")));
    Image white_king = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_king.png")));

    ArrayList<Integer> selectedCoordinates = null;
    Button selectedButton = null;

    public ImageView changeButtonGraphic(String type) {
        switch (type) {
            case "." -> {
                return new ImageView(space);
            }
            case "P" -> {
                return new ImageView(white_pawn);
            }
            case "R" -> {
                return new ImageView(white_rook);
            }
            case "K" -> {
                return new ImageView(white_knight);
            }
            case "B" -> {
                return new ImageView(white_bishop);
            }
            case "Q" -> {
                return new ImageView(white_queen);
            }
            case "J" -> {
                return new ImageView(white_king);
            }
        }
        return null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        GridPane center = new GridPane();
        Board board = new Board(8,8);
        HashMap<ArrayList<Integer>, String> boardValues = board.getBoard();
        //loops through every value in the boardValues hashMap and provides them a button
        for (ArrayList<Integer> val : boardValues.keySet()) {
            Button button = new Button();
            button.setMinSize(20, 20);
            button.setMaxSize(50, 50);
            button.setGraphic(changeButtonGraphic(boardValues.get(val)));
            button.setOnAction(event -> {
                //if no space has been previously selected, the button's values populate the coordinates and button
                if (selectedCoordinates == null) {
                    selectedCoordinates = val;
                    selectedButton = button;
                    System.out.println(selectedCoordinates);
                } else {
                    //if the previously selected button was not an empty space and
                    //the newly selected space IS an empty space
                    if (!Objects.equals(boardValues.get(selectedCoordinates), ".") && Objects.equals(boardValues.get(val), ".")) {
                        //swap the values of the two spaces in the hashmap
                        boardValues.replace(val, boardValues.get(selectedCoordinates));
                        boardValues.replace(selectedCoordinates, ".");
                        //change the graphic of the newly selected space with that of the originally selected one
                        button.setGraphic(changeButtonGraphic(boardValues.get(val)));
                        //when a space is selected, the originally selected button needs to update to an empty space
                        selectedButton.setGraphic(changeButtonGraphic("."));
                        System.out.println("Original cell: " + selectedCoordinates + " , " + boardValues.get(selectedCoordinates));
                        System.out.println("Destination cell: " + val + " , " + boardValues.get(val));
                        //clear the values of the selectedCoordinates and selectedButton
                        selectedCoordinates = null;
                        selectedButton = null;
                    }
                }
            });
            //note that the row/col are swapped here
            center.add(button, val.get(1), val.get(0));
            center.setHgap(11);
            center.setVgap(11);
        }
        BorderPane borderPane = new BorderPane(center);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
