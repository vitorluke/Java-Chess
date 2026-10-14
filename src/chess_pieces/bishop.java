
package chess_pieces;

public class bishop extends piece {

    public bishop(boolean white) {
        super(white);
    }

    @Override
    public char getSimbol() {
        if (isWhite()) {
            return 'B';
        } else {
            return 'b';
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

        // O bispo deve se mover na diagonal.
        return origin_x != destine_x &&
               row_difference == column_difference;
    }
}
