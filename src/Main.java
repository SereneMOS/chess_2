/**
 * Main creates an instance of the board and the players
 * The board is populated with pieces
 * The players have their pieces added to a list
 */

public class Main {
    public boolean someone_has_won() {
        return false;
    }

    public void begin_game() {
        Board board = new Board(8, 8);
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}