import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;

public class boardstatemaker {
    private static HashSet<String> indexed;
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter menace_one = new PrintWriter("menace_one.txt");
        board menace_one_board = new board();
        indexed = new HashSet<>();
        menace_one.println("-");
        menace_one.println("000000000,555555555");
        menace_one.println("-");
        next_turn(menace_one_board, 1, menace_one);
        menace_one.close();

        PrintWriter menace_two = new PrintWriter("menace_two.txt");
        board menace_two_board = new board();
        indexed = new HashSet<>();
        menace_two.println("-");
        menace_two.println("000000000,555555555");
        menace_two.println("-");
        next_turn(menace_two_board, 1, menace_two);
        menace_two.close();
    }

    private static void next_turn(board start, int turn, PrintWriter export) {
        if (start.spaces == 0 || start.is_winner()) {
            return;
        }
        String[] hashes = start.boardhashes().split(",");
        for (String hash : hashes) {
            indexed.add(hash);
        }

        for (int j = 0; j < 9; j++) {
            board cur = new board(start);
            int r = j / 3;
            int c = j % 3;
            int nt = cur.next_turn(r, c, turn);
            if ((! indexed.contains(cur.hash())) && nt != -1) {
                // export.println("Depth " + (9-cur.spaces));
                String[] chashes = cur.boardhashes().split(",");
                for (String chash : chashes) {
                    indexed.add(chash);
                }
                if (! cur.is_winner()) {
                    String h = cur.hash();
                    String pr = "";
                    for (int i = 0; i < h.length(); i++) {
                        pr += h.charAt(i) == '0' ? "5" : "0";
                    }
                    export.println(h+","+pr);
                    // comment out line above and uncomment below if you want to generate hashes instead
                    // export.println(cur.hash());
                    
                    export.println("-");
                    next_turn(cur, nt, export);
                }
            }
        }
    }   
}