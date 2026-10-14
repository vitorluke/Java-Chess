package game;
public class chess_board{
    private final char[][] spaces = new char[8][8];

    public void init_board(){
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0;j < 8; j++)
            {
                if( i > 1 && i < 6)
                {
                    spaces[i][j] = '.';
                } 
                else if(i == 1 || i == 6)
                {
                    spaces[i][j] = 'P';
                }
            }
        }
    

    spaces[0][0] = 'T';
    spaces[7][0] = 'T';
    spaces[0][1] = 'C';
    spaces[7][1] = 'C';
    spaces[0][2] = 'B';
    spaces[7][2] = 'B';
    spaces[0][3] = 'D';
    spaces[7][3] = 'D';
    spaces[0][4] = 'R';
    spaces[7][4] = 'R';
    spaces[0][5] = 'B';
    spaces[7][5] = 'B';
    spaces[0][6] = 'C';
    spaces[7][6] = 'C';
    spaces[0][7] = 'T';
    spaces[7][7] = 'T';

    for(int i = 6; i < 8; i ++)
    {
        for(int j = 0; j < 8;j++)
        {
            spaces[i][j] = Character.toLowerCase(spaces[i][j]);
        }
    }

    }

    public void print_board() {
       for (int i = 0; i < 8; i++) {
         for (int j = 0; j < 8; j++) {
             System.out.print(spaces[i][j] + " ");
         }
         System.out.println();
    }
}
}