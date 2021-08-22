package tictactoe;

import java.util.Random;

public class TicTacToeField {
    final CellState[][] field;

    public TicTacToeField(CellState[][] field) {
        this.field = new CellState[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(field[i], 0, this.field[i], 0, 3);
        }
    }


    public TicTacToeField() {
        field = new CellState[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                field[row][col] = CellState.FREE;
            }
        }
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

        if (field[row - 1][col - 1] != CellState.FREE) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        CellState player = CellState.get(whosTurnIsIt());
        setCell(row - 1, col - 1, player);
        return true;
    }

    public boolean checkCoordinatesAI(String input) {
        String[] rowStr = input.split(" ");

        if (Integer.parseInt(rowStr[0]) > 3 || Integer.parseInt(rowStr[1]) > 3) {
            return false;
        }

        int row = Integer.parseInt(rowStr[0]);
        int col = Integer.parseInt(rowStr[1]);

        if (field[row - 1][col - 1] != CellState.FREE) {
            return false;
        }

        CellState player = CellState.get(whosTurnIsIt());
        setCell(row - 1, col - 1, player);
        return true;
    }

    public void setCell(int x, int y, CellState cellState) {
        field[x][y] = cellState;
    }

    public char whosTurnIsIt() {
        int x = 0;
        int o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == CellState.X) {
                    x++;
                }
                if (field[i][j] == CellState.O) {
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

    public boolean isWin(CellState player) {
        for (int i = 0; i < 3; i++) {
            // check the rows
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][2] == player) {
                return true;
            }
            // check the columns
            if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[2][i] == player) {
                return true;
            }
        }
        // check left top to bottom diagonal
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2] &&  field[2][2] == player) {
            return true;
        }
        // check right top to bottom diagonal
        if (field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[0][2] == player) {
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(" ".equals(field[i][j].getName())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
//        System.out.println("---------");
//        for (int i = 0; i < 3; i++) {
//            System.out.printf("%s", "|");
//            for (int j = 0; j < 3; j++) {
//                System.out.printf("%s%s"," ", field[i][j].getName());
//            }
//            System.out.printf("%s%n", " |");
//        }
//        System.out.println("---------");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---------").append("\n");
        for (int i = 0; i < 3; i++) {
            stringBuilder.append("| ").append(field[i][0].getName()).append(" ")
                    .append(field[i][1].getName()).append(" ").append(field[i][2].getName()).append(" |\n");
        }
        stringBuilder.append("---------");
        System.out.println(stringBuilder);
    }

    public GameState getGameState() {
        if (isWin(CellState.X)) return GameState.X_WIN;
        if (isWin(CellState.O)) return GameState.O_WIN;
        if (!isBoardFull()) return GameState.NOT_FINISHED;
        else return GameState.DRAW;
    }

    private String getRandomMove() {
        Random rand = new Random();
        int randomChoice = rand.nextInt(9);
        int row = randomChoice / 3 + 1;
        int col = randomChoice % 3 + 1;
        return row + " " + col;
    }

    public void easyAiMove() {
        while (true) {
            String coord = getRandomMove();
            if (checkCoordinatesAI(coord)) {
                System.out.println("Making move level \"easy\"");
                break;
            }
        }
    }

    public void mediumAiMove() {
        while (true) {
            String coord = "";
            char charPlayer = whosTurnIsIt();
            CellState player = CellState.get(charPlayer);
            if (canBeWinCoordinates(player) != null) {
                //System.out.println(canBeWinCoordinates(player));
                coord = canBeWinCoordinates(player);

            } else if (canBeWinCoordinates(CellState.getOpponent(player)) != null) {
                //System.out.println(canBeWinCoordinates(CellState.getOpponent(player)));
                coord = canBeWinCoordinates(CellState.getOpponent(player));
            } else {
                coord = getRandomMove();
            }

            if (checkCoordinatesAI(coord)) {
                System.out.println("Making move level \"medium\"");
                break;
            }
        }
    }

    public String canBeWinCoordinates(CellState player) {
        for (int i = 0; i < 3; i++) {
            // check the rows
            if (field[i][0] == field[i][1] && field[i][1] == player && field[i][2] == CellState.FREE) {
                return (i + 1) + " " + 3;
            } else if (field[i][1] == field[i][2] && field[i][2] == player && field[i][0] == CellState.FREE) {
                return (i + 1) + " " + 1;
            } else if (field[i][0] == field[i][2] && field[i][2] == player && field[i][1] == CellState.FREE) {
                return (i + 1) + " " + 2;
            }
            // check the columns
            if (field[0][i] == field[1][i] && field[1][i] == player && field[2][i] == CellState.FREE) {
                return 3 + " " + (i + 1);
            } else if (field[1][i] == field[2][i] && field[2][i] == player && field[0][i] == CellState.FREE) {
                return 1 + " " + (i + 1);
            } else if (field[0][i] == field[2][i] && field[2][i] == player && field[1][i] == CellState.FREE) {
                return 2 + " " + (i + 1);
            }
        }
        // check left top to bottom diagonal
        if (field[0][0] == field[1][1] && field[1][1] == player && field[2][2] == CellState.FREE) {
            return "3 3";
        } else if (field[1][1] == field[2][2] && field[2][2] == player && field[0][0] == CellState.FREE) {
            return "1 1";
        } else if (field[0][0] == field[2][2] && field[2][2] == player && field[1][1] == CellState.FREE) {
            return "2 2";
        }
        // check right top to bottom diagonal
        if (field[0][2] == field[1][1] && field[0][2] == player && field[2][0] == CellState.FREE) {
            return "3 1";
        } else if (field[1][1] == field[2][0] && field[2][0] == player && field[0][2] == CellState.FREE) {
            return "1 3";
        } else if (field[0][2] == field[2][0] && field[2][0] == player && field[1][1] == CellState.FREE) {
            return "2 2";
        }
        return null;
    }
}
