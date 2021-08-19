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
            isGameOver = true;
        }
    }
}
