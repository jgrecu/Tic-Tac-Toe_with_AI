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
        boolean isGameOver = false;
        while (!isGameOver) {
            getCoordinates();
            field.printBoard();

            if (FieldState.get(field.checkSolved()) == FieldState.X || FieldState.get(field.checkSolved()) == FieldState.O) {
                char winner = field.checkSolved();
                System.out.println(winner + " wins");
                scanner.close();
                isGameOver = true;
            } else if (field.isBoardFull() && field.checkSolved() == '0') {
                System.out.println("Draw");
                scanner.close();
                isGameOver = true;
            }
        }
    }
}
