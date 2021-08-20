package tictactoe;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final TicTacToeField field = new TicTacToeField();

    public void play() {
        field.printBoard();
        startGame();
    }

    private void getCoordinates() {
        while (true) {
            System.out.println("Enter the coordinates: ");
            String input = scanner.nextLine();
            if (field.checkCoordinates(input)) {
                break;
            }
        }
    }

    private void startGame() {
        int player = 0;
        while (field.getGameState() == GameState.NOT_FINISHED) {
            if (player % 2 == 0) {
                getCoordinates();
            } else {
                field.easyAiMove();
            }
            field.printBoard();

            if (field.getGameState() == GameState.O_WIN || field.getGameState() == GameState.X_WIN) {
                String winner = field.getGameState() == GameState.X_WIN ? "X":"O";
                System.out.println(winner + " wins");
                scanner.close();
            } else if (field.getGameState() ==  GameState.DRAW) {
                System.out.println("Draw");
                scanner.close();
            }
            player++;
        }
    }


}
