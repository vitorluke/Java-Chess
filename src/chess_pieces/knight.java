
package chess_pieces;

public class knight extends piece {

    public knight(boolean white) {
        super(white);
    }

    @Override
    public char getSimbol() {
        if (isWhite()) {
            return 'C';
        } else {
            return 'c';
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

        // O cavalo deve se mover em L.
        return (row_difference == 2 && column_difference == 1) ||
               (row_difference == 1 && column_difference == 2);
    }
}
