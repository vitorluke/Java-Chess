
package chess_pieces;
public class pawn extends piece {

    public pawn(boolean white) {
        super(white);
    }

    @Override
    public char getSimbol() {
        if (isWhite()) {
            return 'P';
        } else {
            return 'p';
        }
    }

    @Override
    public boolean validMoviment(
        int origin_x,
        int origin_y,
        int destine_x,
        int destine_y
    ) {
        int direction;
        int initial_row;

        if (isWhite()) {
            direction = -1;
            initial_row = 6;
        } else {
            direction = 1;
            initial_row = 1;
        }

        // Não pode permanecer na mesma posição.
        if (origin_x == destine_x &&
            origin_y == destine_y) {
            return false;
        }

        // Movimento para frente na mesma coluna.
        if (origin_y == destine_y) {

            // Uma casa para frente.
            if (destine_x == origin_x + direction) {
                return true;
            }

            // Duas casas no primeiro movimento.
            if (origin_x == initial_row &&
                destine_x == origin_x + 2 * direction) {
                return true;
            }
        }

        return false;
    }
}
