import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;

public class boardstatemaker {
    private static int id;
    private static HashSet<String> indexed;
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter export = new PrintWriter("boardstates.txt");
        board start = new board();
        id = 0;
        indexed = new HashSet<>();
        export.println("-");
        next_turn(start, 1, export);
        export.close();
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
                    export.println(id+".");
                    id++;
                    
                    export.println(cur.hash());
                    // comment out line above and uncomment below if you want to generate hashes instead
                    // export.println(cur.hash());
                    
                    export.println("-");
                    next_turn(cur, nt, export);
                }
            }
        }
    }   
}