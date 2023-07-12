import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GUI extends Application {
    //TODO Acquire chess board tile assets and tie them together in the GUI
    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        GridPane center = new GridPane();
        Board board = new Board(8,8);
        HashMap<ArrayList<Integer>, String> boardValues = board.getBoard();
        for (ArrayList<Integer> val : boardValues.keySet()) {
            String buttonText = null;
            switch (boardValues.get(val)) {
                case "." -> buttonText = ".";
                case "P" -> buttonText = "P";
                case "R" -> buttonText = "R";
                case "K" -> buttonText = "K";
                case "B" -> buttonText = "B";
                case "Q" -> buttonText = "Q";
                case "J" -> buttonText = "J";
            }
            Button button = new Button(buttonText);
            //note that the row/col are swapped here
            center.add(button, val.get(1), val.get(0));
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
