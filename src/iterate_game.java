import java.io.FileNotFoundException;

public class iterate_game {
    public static void main(String[] args) throws FileNotFoundException, IncompatibleStateException {
        int iterations = 10;
        for (int i = 0; i < iterations; i++) {
            game.main(args);
        }
    }
}
