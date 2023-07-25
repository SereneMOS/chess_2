import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import pieces.EmptySpace;
import pieces.PiecesInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GUI extends Application {
    //TODO make pieces able to capture each other (except for ones on the same team)
    //TODO create a turn order, probably need to do that in Board
    //TODO establish win condition (checkmate)

    ArrayList<Integer> selectedCoordinates = null;
    Button selectedButton = null;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        GridPane center = new GridPane();
        Board board = new Board(8,8);
        HashMap<ArrayList<Integer>, PiecesInterface> boardValues = board.getBoard();
        //loops through every value in the boardValues hashMap and provides them a button
        for (ArrayList<Integer> val : boardValues.keySet()) {
            PiecesInterface current = boardValues.get(val);
            Button button = new Button();
            button.setMinSize(20, 20);
            button.setMaxSize(50, 50);
            if (Objects.equals(current.getColor(), "white")) {
                button.setGraphic(current.getGraphic("white"));
            } else if (Objects.equals(current.getColor(), "black")) {
                button.setGraphic(current.getGraphic("black"));
            } else {
                button.setGraphic(current.getGraphic(""));
            }
            button.setOnAction(event -> {
                //if no space has been previously selected, the button's values populate the coordinates and button
                if (selectedCoordinates == null && !Objects.equals(boardValues.get(val).getValue(), ".")) {
                    selectedCoordinates = val;
                    selectedButton = button;
                    System.out.println(selectedCoordinates);
                } else if (selectedCoordinates != null) {
                    //if the previously selected button was not an empty space and
                    //the newly selected space IS an empty space
                    if (!Objects.equals(boardValues.get(selectedCoordinates).getValue(), ".") &&
                            Objects.equals(current.getValue(), ".")) {
                        if (boardValues.get(selectedCoordinates).isValidMove(selectedCoordinates, val)) {
                            //swap the values of the two spaces in the hashmap
                            boardValues.replace(val, boardValues.get(selectedCoordinates));
                            boardValues.replace(selectedCoordinates, new EmptySpace());
                            //change the graphic of the newly selected space with that of the originally selected one
                            if (Objects.equals(boardValues.get(val).getColor(), "white")) {
                                button.setGraphic(boardValues.get(val).getGraphic("white"));
                            } else if (Objects.equals(boardValues.get(val).getColor(), "black")) {
                                button.setGraphic(boardValues.get(val).getGraphic("black"));
                            }
                            //when a space is selected, the originally selected button needs to update to an empty space
                            selectedButton.setGraphic(boardValues.get(selectedCoordinates).getGraphic("white"));
                            System.out.println("Original cell: " + selectedCoordinates + " , " + boardValues.get(selectedCoordinates));
                            System.out.println("Destination cell: " + val + " , " + boardValues.get(val));
                            //clear the values of the selectedCoordinates and selectedButton
                            selectedCoordinates = null;
                            selectedButton = null;
                        } else {
                            System.out.println("Invalid move");
                            selectedCoordinates = null;
                            selectedButton = null;
                        }
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
