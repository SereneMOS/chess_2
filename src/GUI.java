import javafx.application.Application;
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
    //TODO Acquire chess board tile assets and tie them together in the GUI
    Image space = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/space_3.png")));
    Image white_pawn = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_pawn.png")));
    Image white_rook = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_rook.png")));
    Image white_bishop = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_bishop.png")));
    Image white_knight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_knight.png")));
    Image white_queen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_queen.png")));
    Image white_king = new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/white_king.png")));
    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        GridPane center = new GridPane();
        Board board = new Board(8,8);
        HashMap<ArrayList<Integer>, String> boardValues = board.getBoard();
        for (ArrayList<Integer> val : boardValues.keySet()) {
            Button button = new Button();
            button.setMinSize(20, 20);
            button.setMaxSize(50, 50);
            switch (boardValues.get(val)) {
                case "." -> button.setGraphic(new ImageView(space));
                case "P" -> button.setGraphic(new ImageView(white_pawn));
                case "R" -> button.setGraphic(new ImageView(white_rook));
                case "K" -> button.setGraphic(new ImageView(white_knight));
                case "B" -> button.setGraphic(new ImageView(white_bishop));
                case "Q" -> button.setGraphic(new ImageView(white_queen));
                case "J" -> button.setGraphic(new ImageView(white_king));
            }
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
