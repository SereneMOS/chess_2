import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pieces.EmptySpace;
import pieces.PiecesInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GUI extends Application {
    //TODO create a more expansive GUI, with turn order, pieces captured, and a title
    //TODO update Player and Board so that their backend logic syncs with the GUI
    //TODO update documentation and styling across the project
    //TODO establish win condition (checkmate)
    //TODO modify the gui and button's graphics to be more consistent with a real chess board
    //TODO add in the pawn's first move double jump rule and diagonal only capture

    ArrayList<Integer> selectedCoordinates = null;
    Button selectedButton = null;
    Board board = new Board(8,8);
    HashMap<ArrayList<Integer>, PiecesInterface> boardValues = board.getBoard();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        stage.setTitle("Chess 2");
        GridPane center = new GridPane();
        center.setHgap(11);
        center.setVgap(11);
        center.setPadding(new Insets(7,7,7,7));

        Label turnLabel = new Label("Current turn: " + board.getTurn());
        Label selectedPieceLabel = new Label("Selected Piece: None");
        ScrollPane recentMoves = new ScrollPane();
        recentMoves.setMaxHeight(100);
        recentMoves.setPrefWidth(150);
        recentMoves.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        FlowPane movesList = new FlowPane();
        movesList.setOrientation(Orientation.VERTICAL);

        FlowPane blackCapturesFlow = new FlowPane();
        blackCapturesFlow.setPrefWrapLength(150);
        blackCapturesFlow.setHgap(7);
        blackCapturesFlow.setMaxHeight(10);
        blackCapturesFlow.setMinHeight(10);
        FlowPane whiteCapturesFlow = new FlowPane();
        whiteCapturesFlow.setPrefWrapLength(150);
        whiteCapturesFlow.setHgap(7);
        //loops through every value in the boardValues hashMap and provides them a button
        for (ArrayList<Integer> val : boardValues.keySet()) {
            Button button = new Button();
            button.setMinSize(20, 20);
            button.setMaxSize(50, 50);
            button.setGraphic(boardValues.get(val).getGraphic());
            button.setOnAction(event -> {
                if (selectedCoordinates == null && !Objects.equals(boardValues.get(val).getValue(), ".")) {
                    //if no space has been previously selected, the button's values populate the coordinates and button
                    if (Objects.equals(boardValues.get(val).getColor(), board.getTurn())) {
                        selectedCoordinates = val;
                        selectedButton = button;
                        System.out.println("Selected coords" + selectedCoordinates);
                        selectedPieceLabel.setText("Selected Piece: " + boardValues.get(val).getValue());
                    } else {
                        System.out.println("Other color's turn");
                    }
                } else if (selectedCoordinates == val) {
                    //deselect the current piece/button
                    selectedCoordinates = null;
                    selectedButton = null;
                    System.out.println("Deselected");
                    selectedPieceLabel.setText("Selected Piece: None");
                } else if (selectedCoordinates != null) {
                    //runs through the selectedCoordinates movement criteria to determine if it should be allowed to move
                    if (boardValues.get(selectedCoordinates).isValidMove(selectedCoordinates, val, boardValues)) {
                        //checks if the current incoming and outgoing pieces are the same color
                        if (!Objects.equals(boardValues.get(selectedCoordinates).getColor(), boardValues.get(val).getColor())) {
                            if (Objects.equals(boardValues.get(val).getColor(), "white")) {
                                blackCapturesFlow.getChildren().add(boardValues.get(val).getGraphic());
                            } else if (Objects.equals(boardValues.get(val).getColor(), "black")) {
                                whiteCapturesFlow.getChildren().add(boardValues.get(val).getGraphic());
                            }
                            //swap the values of the two spaces in the hashmap
                            boardValues.replace(val, boardValues.get(selectedCoordinates));
                            boardValues.replace(selectedCoordinates, new EmptySpace());
                            //update the graphic of the current button, so it is accurate
                            button.setGraphic(boardValues.get(val).getGraphic());
                            //when a space is selected, the originally selected button needs to update to an empty space
                            selectedButton.setGraphic(boardValues.get(selectedCoordinates).getGraphic());
                            //console output
                            System.out.println("Original cell: " + selectedCoordinates + " , " + boardValues.get(selectedCoordinates));
                            System.out.println("Destination cell: " + val + " , " + boardValues.get(val));
                            movesList.getChildren().add(new Label(board.produceProperCoords(selectedCoordinates, val)));
                            //clear the values of the selectedCoordinates and selectedButton
                            selectedCoordinates = null;
                            selectedButton = null;
                            //change the turn and update labels
                            board.changeTurn();
                            turnLabel.setText("Current turn: " + board.getTurn());
                            selectedPieceLabel.setText("Selected Piece: None");
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
        }
        VBox rightBox = new VBox();
        rightBox.setSpacing(200);
        VBox blackCapturesBox = new VBox();
        Label blackCapturesLabel = new Label("White pieces captured:");
        blackCapturesLabel.setFont(Font.font("Constantia", 15));
        blackCapturesBox.getChildren().add(blackCapturesLabel);
        blackCapturesBox.getChildren().add(blackCapturesFlow);
        VBox whiteCapturesBox = new VBox();
        Label whiteCapturesLabel = new Label("Black pieces captured:");
        whiteCapturesLabel.setFont(Font.font("Constantia", 15));
        whiteCapturesBox.getChildren().add(whiteCapturesLabel);
        whiteCapturesBox.getChildren().add(whiteCapturesFlow);
        whiteCapturesBox.setAlignment(Pos.BOTTOM_LEFT);
        rightBox.getChildren().add(blackCapturesBox);
        rightBox.getChildren().add(whiteCapturesBox);

        VBox leftBox = new VBox();
        leftBox.setPrefWidth(150);
        leftBox.setFillWidth(false);
        turnLabel.setFont(Font.font("Constantia", 15));
        selectedPieceLabel.setFont(Font.font("Constantia", 15));
        recentMoves.setContent(movesList);
        leftBox.getChildren().add(turnLabel);
        leftBox.getChildren().add(selectedPieceLabel);
        leftBox.getChildren().add(recentMoves);

        HBox topBox = new HBox();
        Label titleLabel = new Label("Chess 2");
        titleLabel.setTextFill(Color.BLACK);
        topBox.getChildren().add(titleLabel);
        topBox.setAlignment(Pos.CENTER);
        titleLabel.setUnderline(true);
        titleLabel.setFont(Font.font("Constantia", 30));

        BorderPane borderPane = new BorderPane(center);
        center.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        BorderPane.setMargin(center, new Insets(20, 0, 10, 0));
        borderPane.setRight(rightBox);
        BorderPane.setMargin(rightBox, new Insets(10, 10, 0, 20));
        borderPane.setTop(topBox);
        borderPane.setLeft(leftBox);
        BorderPane.setMargin(leftBox, new Insets(10, 20, 0, 10));
        borderPane.setBackground(new Background(new BackgroundFill(Color.DARKGOLDENROD, null ,null)));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
