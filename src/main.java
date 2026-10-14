import game.chess_board;
public class main { 
    public static void main(String[] args) { 

        boolean game_state = true;
        int contador = 0;
        chess_board board = new chess_board();
        board.init_board();
        board.print_board();


        while(game_state) {
            if (contador == 5){
                game_state = false;
            }
            contador ++;
            
        }
        
    } 
} 