import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class playervs {
    public static void main(String[] args) throws IncompatibleStateException, FileNotFoundException {
        board b = new board();
        Scanner sc = new Scanner(System.in);

        HashMap<String, String> menace_one = new HashMap<>();
        HashMap<String, Integer> menace_one_moves = new HashMap<>();
        game.import_menace_data("menace_one.txt", menace_one);
        
        int turn = 1;
        int i = 0;
        while (! b.is_over() && i < 9) {
            System.out.println(b);
            System.out.println();
            
            if (turn == 1) {
                turn = game.get_menace_move(b, turn, menace_one, menace_one_moves);
            }
            else if (turn == 2) {
                String[] pos = sc.nextLine().split(",");
                turn = b.next_turn(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), turn);
            }
            i++;
        }
        int winner = b.who_winner();
        System.out.println(b);
        
        if (winner == -1) {
            System.out.println("The game is a draw.");
        }
        else {
            System.out.println(winner + " wins the game.");
        }
    }
}
