package tictactoe;

public enum CellState {
    FREE(" "),
    X("X"),
    O("O");

    final String name;

    CellState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static CellState get(char symbol) {
        switch (symbol) {
            case 'X':
                return X;
            case 'O':
                return O;
            case ' ':
            case '_':
                return FREE;
            default:
                return null;
        }
    }

    static CellState getOpponent(CellState player) {
        if (player == X) {
            return O;
        } else {
            return X;
        }
    }
}
