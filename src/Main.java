/**
 * Main creates an instance of the board and the players
 * The board is populated with pieces
 * The players have their pieces added to a list
 */

public class Main {
    Player PLAYER_1 = new Player(1);
    Player PLAYER_2 = new Player(2);

    public boolean someoneHasWon() {
        if (PLAYER_1.getPIECES().isEmpty()) {
            return true;
        } else return PLAYER_2.getPIECES().isEmpty();
    }

    public void beginGame() {
        Board board = new Board(8, 8);
    }

    public static void main(String[] args) {
        Board board = new Board(8, 8);
        System.out.println(board);
        System.out.println("Operational");
    }
}