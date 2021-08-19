package tictactoe;

public class TicTacToeField {
    final FieldState[][] field;

    public TicTacToeField(FieldState[][] field) {
        this.field = new FieldState[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(field[i], 0, this.field[i], 0, 3);
        }
    }

    public TicTacToeField(String str) {
        field = new FieldState[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                field[row][col] = FieldState.get(str.charAt(row * 3 + col));
            }
        }
    }

    public boolean equalTo(TicTacToeField other) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] != other.field[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkCoordinates(String input) {
        String[] rowStr = input.split(" ");

        if (!rowStr[0].matches("\\d+") || rowStr.length > 1 && !rowStr[1].matches("\\d+")) {
            System.out.println("You should enter numbers!");
            return false;
        }
        if (Integer.parseInt(rowStr[0]) > 3 || Integer.parseInt(rowStr[1]) > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        int row = Integer.parseInt(rowStr[0]);
        int col = Integer.parseInt(rowStr[1]);

        if (field[row - 1][col - 1] != FieldState.FREE) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%s", "| ");
            for (int j = 0; j < 3; j++) {
                System.out.printf("%s%c"," ", FieldState.getChar(field[i][j]));
            }
            System.out.printf("%s%n", " |");
        }
        System.out.println("---------");
    }
}
