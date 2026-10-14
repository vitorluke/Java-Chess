
package chess_pieces;

public class queen extends piece {

    public queen(boolean white) {
        super(white);
    }

    @Override
    public char getSimbol() {
        if (isWhite()) {
            return 'D';
        } else {
            return 'd';
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

        int row_difference = Math.abs(destine_x - origin_x);
        int column_difference = Math.abs(destine_y - origin_y);

        // Movimento horizontal, vertical ou diagonal.
        return origin_x == destine_x ||
               origin_y == destine_y ||
               row_difference == column_difference;
    }
}
