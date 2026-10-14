public abstract class piece {
    private final boolean white;

    public piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite(){
        return white;
    }

    public abstract char getSimbol();

    public abstract boolean validMoviment(
        int origin_x,
        int origin_y,
        int destine_x,
        int destine_y
    );
}