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

        field[row - 1][col - 1] = FieldState.get(whosTurnIsIt());
        return true;
    }

    public char whosTurnIsIt() {
        int x = 0;
        int o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == FieldState.X) {
                    x++;
                }
                if (field[i][j] == FieldState.O) {
                    o++;
                }
            }
        }
        if (x == o) {
            return 'X';
        } else if (x > o) {
            return 'O';
        }
        return 'X';
    }

    public char checkSolved() {
        char winner = '0';
        for (int i = 0; i < 3; i++) {
            // check the rows
            if (field[i][0] != FieldState.FREE && field[i][0] == field[i][1] && field[i][1] == field[i][2]) {
                return FieldState.getChar(field[i][0]);
            }
            // check the columns
            if (field[0][i] != FieldState.FREE && field[0][i] == field[1][i] && field[1][i] == field[2][i]) {
                return FieldState.getChar(field[0][i]);
            }
        }
        // check left top to bottom diagonal
        if (field[0][0] != FieldState.FREE && field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
            return FieldState.getChar(field[0][0]);
        }
        // check right top to bottom diagonal
        if (field[0][2] != FieldState.FREE && field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
            return FieldState.getChar(field[0][2]);
        }
        return winner;
    }

    public boolean isBoardFull() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(FieldState.getChar(field[i][j]) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%s", "|");
            for (int j = 0; j < 3; j++) {
                System.out.printf("%s%c"," ", FieldState.getChar(field[i][j]));
            }
            System.out.printf("%s%n", " |");
        }
        System.out.println("---------");
        //System.out.println(Arrays.deepToString(field));
    }
}
