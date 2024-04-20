public class board {
    // variables
    private int[] board_eq0 = new int[9];
    private int[] board_eq1 = new int[9];
    private int[] board_eq2 = new int[9];
    private int[] board_eq3 = new int[9];
    
    // init
    public board(int[] row1, int[] row2, int[] row3) {
        // row 1 init
        for (int i = 0; i < row1.length; i++) {
            board_eq0[0+i] = row1[i];
        }

        // row 2 init
        for (int i = 0; i < row2.length; i++) {
            board_eq0[3+i] = row2[i];
        }

        // row 3 init
        for (int i = 0; i < row3.length; i++) {
            board_eq0[6+i] = row3[i];
        }
        update_board_eqs();
    }

    // default init
    public board() {
        for (int i = 0; i < board_eq0.length; i++) {
            board_eq0[i] = 0;
        }
        update_board_eqs();
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < board_eq0.length; i++) {
            if (i % 3 == 0 && i != 0) {
                str = str.substring(0, str.length()-2);
                str += "\n";
            }
            str += board_eq0[i]+" | ";
        }
        return str.substring(0,str.length()-2)+"\n";
    }

    private int[] rotate_cw(int[] b) {
        int[] row1 = new int[] {b[0+3*2], b[0+3*1], b[0+3*0]};
        int[] row2 = new int[] {b[1+3*2], b[1+3*1], b[1+3*0]};
        int[] row3 = new int[] {b[2+3*2], b[2+3*1], b[2+3*0]};
        
        int[] board = new int[9];
        for (int i = 0; i < row1.length; i++) {
            board[0+i] = row1[i];
        }

        // row 2 init
        for (int i = 0; i < row2.length; i++) {
            board[3+i] = row2[i];
        }

        // row 3 init
        for (int i = 0; i < row3.length; i++) {
            board[6+i] = row3[i];
        }

        return board;
    }

    private void update_board_eqs() {
        board_eq1 = rotate_cw(board_eq0);
        board_eq2 = rotate_cw(board_eq1);
        board_eq3 = rotate_cw(board_eq2);
        
        // System.out.println(boardstates());
    }

    // helper functions for is_winner
    private boolean is_horizontal_winner(int[] board) {
        return board[0] != 0 && board[0] == board[1] && board[1] == board[2];
    }

    private boolean is_midhorizontal_winner(int[] board) {
        return board[3] != 0 && board[3] == board[4] && board[4] == board[5];
    }

    private boolean is_diag_winner(int[] board) {
        return board[0] != 0 && board[0] == board[4] && board[4] == board[8];
    }

    private boolean is_vertical_winner(int[] board) {
        return board[0] != 0 && board[0] == board[3] && board[3] == board[6];
    }

    public boolean is_winner() {
        boolean b0win = is_horizontal_winner(board_eq0) || is_midhorizontal_winner(board_eq0) || is_diag_winner(board_eq0) || is_vertical_winner(board_eq0);
        boolean b1win = is_horizontal_winner(board_eq1) || is_midhorizontal_winner(board_eq1)|| is_diag_winner(board_eq1) || is_vertical_winner(board_eq1);
        boolean b2win = is_horizontal_winner(board_eq2) || is_midhorizontal_winner(board_eq2)|| is_diag_winner(board_eq2) || is_vertical_winner(board_eq2);
        boolean b3win = is_horizontal_winner(board_eq3) || is_midhorizontal_winner(board_eq3)|| is_diag_winner(board_eq3) || is_vertical_winner(board_eq3);

        return b0win || b1win || b2win || b3win;
    }

    // updates the board with move at (r,c)
    // returns -1 if unsucessful
    //          0 if 0's turn next
    //          1 if 1's turn next 
    public int next_turn(int r, int c, int m) {
        if ((m != 1 && m != 2) || board_eq0[3*r+c] != 0) {
            return -1;
        }
        board_eq0[3*r+c] = m;
        update_board_eqs();

        return m == 1 ? 2 : 1;
    }

    /* prints board states */
    
    // public String boardstates() {
    //     String str0 = "";
    //     for (int i = 0; i < board_eq0.length; i++) {
    //         if (i % 3 == 0 && i != 0) {
    //             str0 = str0.substring(0, str0.length()-2);
    //             str0 += "\n";
    //         }
    //         str0 += board_eq0[i]+" | ";
    //     }
    //     str0 = str0.substring(0,str0.length()-2)+"\n\n";

    //     String str1 = "";
    //     for (int i = 0; i < board_eq1.length; i++) {
    //         if (i % 3 == 0 && i != 0) {
    //             str1 = str1.substring(0, str1.length()-2);
    //             str1 += "\n";
    //         }
    //         str1 += board_eq1[i]+" | ";
    //     }
    //     str1 = str1.substring(0,str1.length()-2)+"\n\n";

    //     String str2 = "";
    //     for (int i = 0; i < board_eq2.length; i++) {
    //         if (i % 3 == 0 && i != 0) {
    //             str2 = str2.substring(0, str2.length()-2);
    //             str2 += "\n";
    //         }
    //         str2 += board_eq2[i]+" | ";
    //     }
    //     str2 = str2.substring(0,str2.length()-2)+"\n\n";

    //     String str3 = "";
    //     for (int i = 0; i < board_eq3.length; i++) {
    //         if (i % 3 == 0 && i != 0) {
    //             str3 = str3.substring(0, str3.length()-2);
    //             str3 += "\n";
    //         }
    //         str3 += board_eq3[i]+" | ";
    //     }
    //     str3 = str3.substring(0,str3.length()-2)+"\n\n";
    //     return str0+str1+str2+str3;
    // }
}