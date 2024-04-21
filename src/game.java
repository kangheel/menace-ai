import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class game {
    // private static HashMap<String, String> menace_one;
    // private static HashMap<String, String> menace_two;
    public static void main(String[] args) throws FileNotFoundException, IncompatibleStateException {
        board game = new board();

        HashMap<String, String> menace_one = new HashMap<>();
        HashMap<String, String> menace_two = new HashMap<>();

        HashMap<String, String> menace_one_moves = new HashMap<>();
        HashMap<String, String> menace_two_moves = new HashMap<>();

        import_menace_data("menace_one.txt", menace_one);
        import_menace_data("menace_two.txt", menace_two);
        
        // run game
        int turn = 1;
        while (! game.is_over()) {
            // System.out.println(game);
            // System.out.println();
            
            if (turn == 1) {
                turn = get_menace_move(game, turn, menace_one, menace_one_moves);
            }
            else if (turn == 2) {
                turn = get_menace_move(game, turn, menace_two, menace_two_moves);
            }
        }
        int winner = game.who_winner();
        
        if (winner == -1) {
            System.out.println("The game is a draw.");
        }
        else {
            System.out.println(game);
            System.out.println(winner + " wins the game.");
        }
    }
    private static int get_menace_move(board game, int turn, HashMap<String, String> menace, HashMap<String, String> menace_moves) throws IncompatibleStateException {
        Scanner sc = new Scanner(System.in);
        String key = "AAAAAAAAA";
        for (String hash : game.boardhashes().split(",")) {
            key = menace.getOrDefault(hash, "AAAAAAAAA");
            if (! key.equals("AAAAAAAAA")) {
                board cur = new board(hash, 0);
                String morphed_key = cur.morph_hash(game.hash(), key);
                board choices = new board(morphed_key,1);
                int[] move = choices.get_random_move(choices.sum());
                // System.out.println(choices);
                // System.out.println(Arrays.toString(move));
                // sc.nextLine();
                return game.next_turn(move[0], move[1], turn);
            }
        }
        return -1;
    }
    private static void import_menace_data(String fileName, HashMap<String, String> map) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));
        input.nextLine();
        while (input.hasNextLine()) {
            String[] info = input.nextLine().split(",");
            String state = info[0];
            String freq = info[1];
            map.put(state, freq);
            input.nextLine();
        }
        input.close();
    }
}