
package chess_pieces;

public class tower extends piece {

    public tower(boolean white) {
        super(white);
    }

    @Override
    public char getSimbol() {
        if (isWhite()) {
            return 'T';
        } else {
            return 't';
        }
    }

    @Override
    public boolean validMoviment(
        int origin_x,
        int origin_y,
        int destine_x,
        int destine_y
    ) {
        // Não pode permanecer na mesma posição.
        if (origin_x == destine_x &&
            origin_y == destine_y) {
            return false;
        }

        // A torre só se move na horizontal
        // ou na vertical.
        return origin_x == destine_x ||
               origin_y == destine_y;
    }
}
