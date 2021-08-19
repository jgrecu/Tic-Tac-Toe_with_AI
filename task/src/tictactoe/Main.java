package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        boolean isGameOver = false;
        System.out.println("Enter the cells: ");
        String input = scanner.nextLine();
        TicTacToeField field = new TicTacToeField(input);
        field.printBoard();
        while (!isGameOver) {
            System.out.println("Enter the coordinates: ");
            String coord = scanner.nextLine();
            if (!field.checkCoordinates(coord)) {
                continue;
            }
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
            } else {
                System.out.println("Game not finished");
                isGameOver = true;
            }
        }
    }
}
