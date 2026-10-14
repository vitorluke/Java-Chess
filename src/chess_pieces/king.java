
package chess_pieces;

public class king extends piece {

    public king(boolean white) {
        super(white);
    }

    @Override
    public char getSimbol() {
        if (isWhite()) {
            return 'R';
        } else {
            return 'r';
        }
    }

    @Override
    public boolean validMoviment(
        int origin_x,
        int origin_y,
        int destine_x,
        int destine_y
    ) {
        int row_difference = Math.abs(destine_x - origin_x);
        int column_difference = Math.abs(destine_y - origin_y);

        // O rei pode se mover apenas uma casa.
        return Math.max(row_difference, column_difference) == 1;
    }
}
