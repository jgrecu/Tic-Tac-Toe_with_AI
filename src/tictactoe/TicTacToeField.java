package tictactoe;

import java.util.Random;

public class TicTacToeField {
    final CellState[][] field;

//    public TicTacToeField(CellState[][] field) {
//        this.field = new CellState[3][3];
//        for (int i = 0; i < 3; i++) {
//            System.arraycopy(field[i], 0, this.field[i], 0, 3);
//        }
//    }


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

    private boolean checkCoordinatesAI(String input) {
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                stringBuilder.append(" ").append(field[i][j].getName());
                if (j < 2) {
                    stringBuilder.append(" |");
                } else {
                    stringBuilder.append("\n");
                }
            }
            if (i < 2) {
                stringBuilder.append("---+---+---\n");
            }
        }
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
            CellState opponent = CellState.getOpponent(player);
            if (canBeWinCoordinates(player) != null) {
                coord = canBeWinCoordinates(player);

            } else {
                if (canBeWinCoordinates(opponent) != null) {
                    coord = canBeWinCoordinates(opponent);
                } else {
                    coord = getRandomMove();
                }
            }

            if (checkCoordinatesAI(coord)) {
                System.out.println("Making move level \"medium\"");
                break;
            }
        }
    }

    public void hardAiMove() {
        char charPlayer = whosTurnIsIt();
        CellState player = CellState.get(charPlayer);

        while (true) {
            String coord = "";
            coord = findBestMove(field, player);

            if (checkCoordinatesAI(coord)) {
                System.out.println("Making move level \"hard\"");
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

    private int minimax(CellState[][] board, CellState player, boolean isMaximize, CellState startPlayer, int depth) {

        switch (getGameState()) {
            case X_WIN:
                return startPlayer == CellState.X ? 10 - depth : depth - 10;
            case O_WIN:
                return startPlayer == CellState.O ? 10 - depth : depth - 10;
            case DRAW:
                return 0;
        }

        int bestScore = isMaximize ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == CellState.FREE) {
                    board[i][j] = player;
                    int score = minimax(board, CellState.getOpponent(player), !isMaximize, startPlayer, depth + 1);
                    board[i][j] = CellState.FREE;
                    bestScore = isMaximize ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }
        return bestScore;
    }

    private String findBestMove(CellState[][] board, CellState player) {
        int bestScore = Integer.MIN_VALUE;
        int row = -1;
        int col = -1;

        // Traverse all cells, evaluate minimax function for all empty cells. And return the cell with optimal value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == CellState.FREE) {
                    // Make the move
                    board[i][j] = player;

                    // compute evaluation function for this move.
                    int score = minimax(board, CellState.getOpponent(player), false, player, 1);

                    // Undo the move
                    board[i][j] = CellState.FREE;

                    // If the value of the current move is more than the best value, then update best
                    if (score > bestScore) {
                        bestScore = score;
                        row = i;
                        col = j;
                    }
                }
            }
        }
        return (row + 1) + " " + (col + 1);
    }
}
