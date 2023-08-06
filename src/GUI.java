import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pieces.EmptySpace;
import pieces.PiecesInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GUI extends Application {
    //TODO create a more expansive GUI, with turn order, pieces captured, and a title
    //TODO update documentation and styling across the project
    //TODO establish win condition (checkmate)
    //TODO modify the gui and button's graphics to be more consistent with a real chess board
    //TODO add in the pawn's first move double jump rule

    ArrayList<Integer> selectedCoordinates = null;
    Button selectedButton = null;
    Board board = new Board(8,8);
    HashMap<ArrayList<Integer>, PiecesInterface> boardValues = board.getBoard();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        GridPane center = new GridPane();
        Label turnLabel = new Label("Current turn: " + board.getTurn());
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
                if (selectedCoordinates == null && !Objects.equals(boardValues.get(val).getValue(), ".")) {
                    //if no space has been previously selected, the button's values populate the coordinates and button
                    if (Objects.equals(boardValues.get(val).getColor(), board.getTurn())) {
                        selectedCoordinates = val;
                        selectedButton = button;
                        System.out.println(selectedCoordinates);
                    } else {
                        System.out.println("Other color's turn");
                    }
                } else if (selectedCoordinates == val) {
                    //deselect the current piece/button
                    selectedCoordinates = null;
                    selectedButton = null;
                    System.out.println("Deselected");
                } else if (selectedCoordinates != null) {
                    if (boardValues.get(selectedCoordinates).isValidMove(selectedCoordinates, val, boardValues)) {
                        //if the selected location has a piece already, then it will be replaced if that piece is not the same color
                        if (!Objects.equals(boardValues.get(selectedCoordinates).getColor(), boardValues.get(val).getColor())) {
                            button.setGraphic(boardValues.get(val).getGraphic(boardValues.get(selectedCoordinates).getColor()));
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
                            selectedButton.setGraphic(boardValues.get(selectedCoordinates).getGraphic("none"));
                            System.out.println("Original cell: " + selectedCoordinates + " , " + boardValues.get(selectedCoordinates));
                            System.out.println("Destination cell: " + val + " , " + boardValues.get(val));
                            //clear the values of the selectedCoordinates and selectedButton
                            selectedCoordinates = null;
                            selectedButton = null;
                            board.changeTurn();
                            turnLabel.setText("Current turn: " + board.getTurn());
                        } else {
                            System.out.println("Choose another piece");
                        }
                    } else {
                        System.out.println("Invalid move");
                    }
                } else if (Objects.equals(boardValues.get(val).getValue(), ".")) {
                    System.out.println("Empty space");
                }
            });
            //note that the row/col are swapped here
            center.add(button, val.get(1), val.get(0));
            center.setHgap(11);
            center.setVgap(11);
        }
        VBox currentTurn = new VBox();
        currentTurn.getChildren().add(turnLabel);
        BorderPane borderPane = new BorderPane(center);
        borderPane.setRight(currentTurn);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
