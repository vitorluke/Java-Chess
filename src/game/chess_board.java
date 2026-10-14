
package game;

import chess_pieces.*;

public class chess_board {

    private final piece[][] spaces = new piece[8][8];
    private boolean whiteToMove = true;

    public void init_board() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                spaces[r][c] = null;
            }
        }

        spaces[0][0] = new tower(false);
        spaces[0][1] = new knight(false);
        spaces[0][2] = new bishop(false);
        spaces[0][3] = new queen(false);
        spaces[0][4] = new king(false);
        spaces[0][5] = new bishop(false);
        spaces[0][6] = new knight(false);
        spaces[0][7] = new tower(false);

        for (int c = 0; c < 8; c++) {
            spaces[1][c] = new pawn(false);
            spaces[6][c] = new pawn(true);
        }

        spaces[7][0] = new tower(true);
        spaces[7][1] = new knight(true);
        spaces[7][2] = new bishop(true);
        spaces[7][3] = new queen(true);
        spaces[7][4] = new king(true);
        spaces[7][5] = new bishop(true);
        spaces[7][6] = new knight(true);
        spaces[7][7] = new tower(true);

        whiteToMove = true;
    }

    public char get_symbol_at(int r, int c) {
        if (spaces[r][c] == null) {
            return '.';
        }
        return spaces[r][c].getSimbol();
    }

    public boolean is_white_turn() {
        return whiteToMove;
    }

    public boolean is_game_over() {
        return false;
    }

    public String get_game_result() {
        if (is_check(whiteToMove)) {
            return "Xeque! Vez das "
                    + (whiteToMove ? "brancas" : "pretas");
        }

        return "Vez das " + (whiteToMove ? "brancas" : "pretas");
    }

    private boolean insideBoard(int r, int c) {
        return r >= 0 && r < 8 && c >= 0 && c < 8;
    }

    private boolean isSliding(piece p) {
        return p instanceof tower
                || p instanceof bishop
                || p instanceof queen;
    }

    private boolean pathIsClear(int r1, int c1, int r2, int c2) {
        int dr = Integer.compare(r2, r1);
        int dc = Integer.compare(c2, c1);

        int r = r1 + dr;
        int c = c1 + dc;

        while (r != r2 || c != c2) {
            if (spaces[r][c] != null) {
                return false;
            }

            r += dr;
            c += dc;
        }

        return true;
    }

    private boolean validMovement(int r1, int c1, int r2, int c2) {
        piece p = spaces[r1][c1];
        piece target = spaces[r2][c2];

        if (p == null) return false;

        if (target != null && target.isWhite() == p.isWhite()) {
            return false;
        }

        // O rei não pode ser capturado diretamente.
        if (target instanceof king) return false;

        int dr = r2 - r1;
        int dc = c2 - c1;

        if (p instanceof pawn) {
            int direction = p.isWhite() ? -1 : 1;
            int initialRow = p.isWhite() ? 6 : 1;

            // Avança uma casa.
            if (dc == 0 && dr == direction && target == null) {
                return true;
            }

            // Avança duas casas na primeira jogada.
            if (dc == 0 && r1 == initialRow
                    && dr == 2 * direction && target == null
                    && spaces[r1 + direction][c1] == null) {
                return true;
            }

            // Captura na diagonal.
            return Math.abs(dc) == 1
                    && dr == direction
                    && target != null;
        }

        if (!p.validMoviment(r1, c1, r2, c2)) {
            return false;
        }

        // Torre, bispo e dama não podem pular peças.
        return !isSliding(p) || pathIsClear(r1, c1, r2, c2);
    }

    private int[] findKing(boolean white) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (spaces[r][c] instanceof king
                        && spaces[r][c].isWhite() == white) {
                    return new int[]{r, c};
                }
            }
        }

        return null;
    }

    private boolean squareIsAttacked(int row, int col, boolean byWhite) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                piece p = spaces[r][c];

                if (p == null || p.isWhite() != byWhite) {
                    continue;
                }

                if (p instanceof pawn) {
                    int direction = p.isWhite() ? -1 : 1;

                    if (row == r + direction
                            && Math.abs(col - c) == 1) {
                        return true;
                    }
                } else if (p.validMoviment(r, c, row, col)) {
                    if (!isSliding(p) || pathIsClear(r, c, row, col)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean is_check(boolean white) {
        int[] position = findKing(white);

        return position == null
                || squareIsAttacked(
                    position[0], position[1], !white
                );
    }

    public boolean move_piece(int r1, int c1, int r2, int c2) {
        if (!insideBoard(r1, c1) || !insideBoard(r2, c2)
                || (r1 == r2 && c1 == c2)) {
            return false;
        }

        piece moving = spaces[r1][c1];

        if (moving == null || moving.isWhite() != whiteToMove) {
            return false;
        }

        if (!validMovement(r1, c1, r2, c2)) {
            return false;
        }

        piece captured = spaces[r2][c2];

        // Executa temporariamente a jogada.
        spaces[r2][c2] = moving;
        spaces[r1][c1] = null;

        // Impede deixar o próprio rei em xeque.
        if (is_check(moving.isWhite())) {
            spaces[r1][c1] = moving;
            spaces[r2][c2] = captured;
            return false;
        }

        // Promoção automática para dama.
        if (moving instanceof pawn && (r2 == 0 || r2 == 7)) {
            spaces[r2][c2] = new queen(moving.isWhite());
        }

        whiteToMove = !whiteToMove;
        return true;
    }

    public void print_board() {
        System.out.println("  a b c d e f g h");

        for (int r = 0; r < 8; r++) {
            System.out.print((8 - r) + " ");

            for (int c = 0; c < 8; c++) {
                System.out.print(get_symbol_at(r, c) + " ");
            }

            System.out.println(8 - r);
        }
    }
}
