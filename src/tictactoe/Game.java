package tictactoe;

import java.util.Scanner;

public class Game {
    private static final String BAD_PARAMETERS = "Bad parameters!";
    private final Scanner scanner = new Scanner(System.in);
    private final TicTacToeField field = new TicTacToeField();
    private final String[] commands = new String[3];
    private boolean userTurn;
    private boolean justComputer = false;

    public void play() {
        printMenu();
        while (true) {
            System.out.println("Input command: ");
            String string = scanner.nextLine();
            if (checkInput(string)) {
                userTurn = "user".equals(commands[1]);
                break;
            }
        }
        printMenuPlay();
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
        while (field.getGameState() == GameState.NOT_FINISHED) {
            if (justComputer) {
                playComputer();
            } else {
                playUser();
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
        }
    }

    private void playComputer() {
        if ("hard".equals(commands[1]) || "hard".equals(commands[2])) {
            field.hardAiMove();
        } else if ("medium".equals(commands[1]) || "medium".equals(commands[2])) {
            field.mediumAiMove();
        } else {
            field.easyAiMove();
        }
    }

    private boolean checkInput(String input) {
        final String TYPE_REGEX = "(easy|medium|hard|user)";
        String[] parts = input.toLowerCase().split("\\s+");
        if ("exit".equals(parts[0])) {
            System.exit(0);
        }
        if (parts.length == 3 && "start".equals(parts[0]) && parts[1].matches(TYPE_REGEX) && parts[2].matches(TYPE_REGEX)) {
            this.commands[0] = parts[0];
            this.commands[1] = parts[1];
            this.commands[2] = parts[2];
            this.justComputer = !"user".equals(parts[1]) && !"user".equals(parts[2]);
            return true;
        }
        System.out.println(BAD_PARAMETERS);
        return false;
    }

    private void playUser() {
        if (userTurn) {
            getCoordinates();
        } else {
            playComputer();
        }
        userTurn = !userTurn;
    }

    public static void printMenu() {
        System.out.println("Tic-Tac-Toe with AI\n\n" +
                "accepted commands are \"start user easy/medium/hard\" or \"start easy/medium/hard user\", i.e." +
                "\"start hard user\"\n");
    }

    public static void printMenuPlay() {
        System.out.println("The coordinates are \"X Y\" from top left corner \"1 1\" to bottom right corner \"3 3\"\n");
    }
}
