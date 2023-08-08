import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

/**
 * GUI class which creates the visual layout the user interacts with
 * The start method creates the scene, borderpane, and all the content within
 * Rosaline Flowers
 */
public class GUI extends Application {
    //TODO establish win condition (checkmate)
    //TODO gui round 2
    //TODO add in the pawn's first move double jump rule and diagonal only capture
    //TODO make it so that pawns can turn into other pieces when they reach the other side of the board

    ArrayList<Integer> selectedCoordinates = null;
    Button selectedButton = null;
    Board board = new Board(8,8);
    HashMap<ArrayList<Integer>, PiecesInterface> boardValues = board.getBoard();

    /**
     * start
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //stage and title
        Stage stage = new Stage();
        stage.setTitle("Chess 2");
        //center grid pane which contains the actual chess board
        GridPane center = new GridPane();
        center.setHgap(11);
        center.setVgap(11);
        center.setPadding(new Insets(7,7,7,7));
        //content that is on the left side of the border
        Label turnLabel = new Label("Current turn: " + board.getTurn());
        Label selectedPieceLabel = new Label("Selected Piece: None");
        ScrollPane recentMoves = new ScrollPane();
        recentMoves.setMaxHeight(100);
        recentMoves.setPrefWidth(150);
        recentMoves.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        FlowPane movesList = new FlowPane();
        movesList.setOrientation(Orientation.VERTICAL);
        //content that is on the right side of the border
        FlowPane blackCapturesFlow = new FlowPane();
        blackCapturesFlow.setPrefWrapLength(150);
        blackCapturesFlow.setHgap(7);
        blackCapturesFlow.setMaxHeight(10);
        blackCapturesFlow.setMinHeight(10);
        FlowPane whiteCapturesFlow = new FlowPane();
        whiteCapturesFlow.setPrefWrapLength(150);
        whiteCapturesFlow.setHgap(7);
        //this loop runs through every spot on the chess board and creates a button for it
        for (ArrayList<Integer> val : boardValues.keySet()) {
            //button is created and given its initial graphic
            Button button = new Button();
            button.setMinSize(20, 20);
            button.setMaxSize(50, 50);
            button.setGraphic(boardValues.get(val).getGraphic());

            //logic for when the button is pressed
            button.setOnAction(event -> {
                //if no previous space has been selected and the currently selected space is not empty
                if (selectedCoordinates == null && !Objects.equals(boardValues.get(val).getValue(), ".")) {
                    //if it is the turn of the color that was selected
                    if (Objects.equals(boardValues.get(val).getColor(), board.getTurn())) {
                        //this current button and its coordinates are set as selected
                        selectedCoordinates = val;
                        selectedButton = button;
                        System.out.println("Selected coords" + selectedCoordinates);
                        selectedPieceLabel.setText("Selected Piece: " + boardValues.get(val).getValue());
                    } else {
                        System.out.println("Other color's turn");
                    }

                    //if the current button is the same as the one already selected
                } else if (selectedCoordinates == val) {
                    //the button is deselected
                    selectedCoordinates = null;
                    selectedButton = null;
                    System.out.println("Deselected");
                    selectedPieceLabel.setText("Selected Piece: None");

                    //if there has already been a button selected
                } else if (selectedCoordinates != null) {
                    //runs through the selectedCoordinates movement criteria to determine if it should be allowed to move
                    if (boardValues.get(selectedCoordinates).isValidMove(selectedCoordinates, val, boardValues)) {
                        //checks if the current incoming and outgoing pieces are not the same color
                        if (!Objects.equals(boardValues.get(selectedCoordinates).getColor(), boardValues.get(val).getColor())) {

                            //if the current piece is white and the previously selected on is black
                            if (Objects.equals(boardValues.get(val).getColor(), "white")) {
                                //the current piece is captured and its graphic is added to the capture panel
                                blackCapturesFlow.getChildren().add(boardValues.get(val).getGraphic());
                                board.getPlayer("black").enemyPieceCaptured(boardValues.get(val));
                                board.getPlayer("white").pieceLost(boardValues.get(val));
                                //if the current piece is black and the previously selected on is white
                            } else if (Objects.equals(boardValues.get(val).getColor(), "black")) {
                                //the current piece is captured and its graphic is added to the capture panel
                                whiteCapturesFlow.getChildren().add(boardValues.get(val).getGraphic());
                                board.getPlayer("white").enemyPieceCaptured(boardValues.get(val));
                                board.getPlayer("black").pieceLost(boardValues.get(val));
                            }

                            //swaps the values of the two spaces in the hashmap
                            boardValues.replace(val, boardValues.get(selectedCoordinates));
                            boardValues.replace(selectedCoordinates, new EmptySpace());
                            //update the graphic of the current button, so it is accurate
                            button.setGraphic(boardValues.get(val).getGraphic());
                            //the originally selected button needs to update to an empty space
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
            //note that the row/col are swapped here as the button is added to the grid pane
            center.add(button, val.get(1), val.get(0));
        }
        //all the content on the right side of the border pane
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

        //all the content on the left side of the border pane
        VBox leftBox = new VBox();
        leftBox.setPrefWidth(150);
        leftBox.setFillWidth(false);
        turnLabel.setFont(Font.font("Constantia", 15));
        selectedPieceLabel.setFont(Font.font("Constantia", 15));
        recentMoves.setContent(movesList);
        leftBox.getChildren().add(turnLabel);
        leftBox.getChildren().add(selectedPieceLabel);
        leftBox.getChildren().add(recentMoves);

        //all the content at the top of the border pane
        HBox topBox = new HBox();
        Label titleLabel = new Label("Chess 2");
        titleLabel.setTextFill(Color.BLACK);
        topBox.getChildren().add(titleLabel);
        topBox.setAlignment(Pos.CENTER);
        titleLabel.setUnderline(true);
        titleLabel.setFont(Font.font("Constantia", 30));

        //the border pane and its formatting
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
