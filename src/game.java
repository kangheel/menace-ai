import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class game {
    // private static HashMap<String, String> menace_one;
    // private static HashMap<String, String> menace_two;
    private static final int REWARD = 2;
    private static final int CONSEQ = -1;
    public static void main(String[] args) throws FileNotFoundException, IncompatibleStateException {
        board game = new board();

        HashMap<String, String> menace_one = new HashMap<>();
        HashMap<String, String> menace_two = new HashMap<>();

        HashMap<String, Integer> menace_one_moves = new HashMap<>();
        HashMap<String, Integer> menace_two_moves = new HashMap<>();

        import_menace_data("menace_one.txt", menace_one);
        import_menace_data("menace_two.txt", menace_two);
        
        // run game
        int turn = 1;
        int i = 0;
        while (! game.is_over() && i < 9) {
            // System.out.println(game);
            // System.out.println();
            
            if (turn == 1) {
                turn = get_menace_move(game, turn, menace_one, menace_one_moves);
            }
            else if (turn == 2) {
                turn = get_menace_move(game, turn, menace_two, menace_two_moves);
            }
            i++;
        }
        int winner = game.who_winner();
        System.out.println(game);
        
        if (winner == -1) {
            System.out.println("The game is a draw.");
        }
        else {
            System.out.println(winner + " wins the game.");
            change_weights(menace_one, menace_one_moves, 1, winner);
            change_weights(menace_two, menace_two_moves, 2, winner);

            PrintWriter menace_one_export = new PrintWriter("menace_one.txt");
            for (String hash : menace_one.keySet()) {
                menace_one_export.println("-");
                menace_one_export.println(hash+","+menace_one.get(hash));
            }
            menace_one_export.close();

            PrintWriter menace_two_export = new PrintWriter("menace_two.txt");
            for (String hash : menace_two.keySet()) {
                menace_two_export.println("-");
                menace_two_export.println(hash+","+menace_two.get(hash));
            }
            menace_two_export.close();
        }
    }
    public static void change_weights(HashMap<String, String> menace, HashMap<String, Integer> menace_moves, int player, int winner) {
        for (String hashes : menace_moves.keySet()) {
            int move = menace_moves.get(hashes);
            board cur = new board(menace.get(hashes),1, REWARD, CONSEQ);
            if (player == winner) {
                cur.reward_freq(move);
            }
            else {
                cur.conseq_freq(move);
            }
            menace.put(hashes, cur.hash());
        }
    }
    public static int get_menace_move(board game, int turn, HashMap<String, String> menace, HashMap<String, Integer> menace_moves) throws IncompatibleStateException {
        String key = "AAAAAAAAA";
        for (String hash : game.boardhashes().split(",")) {
            key = menace.getOrDefault(hash, "AAAAAAAAA");
            if (! key.equals("AAAAAAAAA")) {
                board cur = new board(hash, 0);
                String morphed_key = cur.morph_hash(game.hash(), key);
                board choices = new board(morphed_key,1);
                int[] move = choices.get_random_move(choices.sum());
                menace_moves.put(hash, move[0]*3+move[1]);
                // System.out.println(choices);
                // System.out.println(Arrays.toString(move));
                // sc.nextLine();
                return game.next_turn(move[0], move[1], turn);
            }
        }
        return -1;
    }
    public static void import_menace_data(String fileName, HashMap<String, String> map) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));
        while (input.hasNextLine()) {
            input.nextLine();
            // String l = input.nextLine();
            // System.out.println(l);

            String[] info = input.nextLine().split(",");
            String state = info[0];
            String freq = info[1];
            map.put(state, freq);
        }
        input.close();
    }
}