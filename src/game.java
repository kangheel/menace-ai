public class game {
    public static void main(String[] args) {
        // board game1 = new board();
        // System.out.println(game1);

        // board game2 = new board(new int[] {1,0,1}, new int[] {0,1,2}, new int[] {2,2,1});
        // System.out.println(game2);
        // System.out.println(game2.is_winner());

        board game3 = new board(new int[] {1,0,0}, new int[] {2,2,1}, new int[] {1,2,1});
        System.out.println(game3);
        System.out.println(game3.is_winner());

        System.out.println(game3.next_turn(0, 0, 1));
        
        System.out.println(game3.next_turn(0, 1, 2));
        System.out.println(game3);
        System.out.println(game3.is_winner());
    }
}