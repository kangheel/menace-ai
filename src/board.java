import java.util.HashSet;

public class board {
    // variables
    private int[] board_eq0 = new int[9];
    // 90 cw
    private int[] board_eq1 = new int[9];
    // 180 cw
    private int[] board_eq2 = new int[9];
    // 270 cw (90 ccw)
    private int[] board_eq3 = new int[9];
    int spaces;
    
    // constructor
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
        update_vars();

        spaces = 0;
        for (int i = 0; i < board_eq0.length; i++) {
            if (board_eq0[i] == 0) {
                spaces++;
            }
        }
    }

    // default constructor
    public board() {
        for (int i = 0; i < board_eq0.length; i++) {
            board_eq0[i] = 0;
        }
        update_vars();
        spaces = 9;
    }

    // copy constructor
    public board(board copy) {
        this.board_eq0 = copy.board_eq0.clone();
        this.spaces = copy.spaces;
        update_vars();
    }
    
    // hash constructor
    public board(String hash) {
        int[] row1 = new int[] {hash.charAt(0)-'0',hash.charAt(1)-'0',hash.charAt(2)-'0'};
        int[] row2 = new int[] {hash.charAt(3)-'0',hash.charAt(4)-'0',hash.charAt(5)-'0'};
        int[] row3 = new int[] {hash.charAt(6)-'0',hash.charAt(7)-'0',hash.charAt(8)-'0'};

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

        update_vars();
    }

    // toString
    public String toString() {
        String str = "";
        for (int i = 0; i < board_eq0.length; i++) {
            if (i % 3 == 0 && i != 0) {
                str = str.substring(0, str.length()-2);
                str += "\n";
            }
            str += board_eq0[i]+" | ";
        }
        return str.substring(0,str.length()-2);
    }

    // generates hash
    public String hash() {
        String str = "";
        for (int i = 0; i < board_eq0.length; i++) {
            str += board_eq0[i];
        }
        return str;
    }

    // generates hash given int[] board
    private String hash(int[] b) {
        String str = "";
        for (int i = 0; i < b.length; i++) {
            str += b[i];
        }
        return str;
    }

    // rotate board clockwise by 90 deg
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

    // same as reflect_x when combined with rotations
    private int[] reflect_y(int[] b) {
        int[] row1 = new int[] {b[2], b[1], b[0]};
        int[] row2 = new int[] {b[5], b[4], b[3]};
        int[] row3 = new int[] {b[8], b[7], b[6]};
        
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

    // reflect around x axis (rows)
    private int[] reflect_x(int[] b) {
        int[] row1 = new int[] {b[6], b[7], b[8]};
        int[] row2 = new int[] {b[3], b[4], b[5]};
        int[] row3 = new int[] {b[0], b[1], b[2]};
        
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

    // reflect around y = x (reflect x then reflect y)
    private int[] reflect_yx(int[] b) {
        int[] row1 = new int[] {b[8], b[5], b[2]};
        int[] row2 = new int[] {b[7], b[4], b[1]};
        int[] row3 = new int[] {b[6], b[3], b[0]};
        
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

    // updates board states
    private void update_vars() {
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
        update_vars();
        spaces--;

        return m == 1 ? 2 : 1;
    }

    public String boardhashes() {
        HashSet<String> hashes = new HashSet<>();
        hashes.add(hash(board_eq0));
        hashes.add(hash(board_eq1));
        hashes.add(hash(board_eq2));
        hashes.add(hash(board_eq3));

        hashes.add(hash(reflect_x(board_eq0)));
        hashes.add(hash(reflect_x(board_eq1)));
        hashes.add(hash(reflect_x(board_eq2)));
        hashes.add(hash(reflect_x(board_eq3)));

        // str += hash(reflect_y(board_eq0))+",";
        // str += hash(reflect_y(board_eq1))+",";
        // str += hash(reflect_y(board_eq2))+",";
        // str += hash(reflect_y(board_eq3))+",";

        hashes.add(hash(reflect_yx(board_eq0)));
        hashes.add(hash(reflect_yx(board_eq1)));
        hashes.add(hash(reflect_yx(board_eq2)));
        hashes.add(hash(reflect_yx(board_eq3)));
        
        String str = "";
        for (String hash : hashes) {
            str += hash + ",";
        }
        return str.substring(0, str.length()-1);
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