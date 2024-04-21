public class debug {
    public static void main(String[] args) {
        board game = new board("022102101",0);
        System.out.println(game.is_winner());
        System.out.println(game.who_winner());
        
        board game1 = new board("102101022",0);
        System.out.println(game1.is_winner());
        System.out.println(game1.who_winner());
    }
}