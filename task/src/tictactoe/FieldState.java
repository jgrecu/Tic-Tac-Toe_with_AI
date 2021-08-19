package tictactoe;

public enum FieldState {
    X, O, FREE;

    static FieldState get(char symbol) {
        switch (symbol) {
            case 'X':
                return X;
            case 'O':
                return O;
            case ' ': case '_':
                return FREE;
            default:
                return null;
        }
    }

    static char getChar(FieldState fieldState) {
        switch (fieldState) {
            case X:
                return 'X';
            case O:
                return 'O';
            case FREE:
                return ' ';
        }
        return ' ';
    }
}
