import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class validate_boardstates {
    private static HashSet<String> indexed;
    public static void main(String[] args) throws FileNotFoundException {
        indexed = new HashSet<>();
        Scanner scanner = new Scanner(new File("boardstates.txt"));
        while (scanner.hasNextLine()) {
            String curhash = scanner.nextLine();
            board curboard = new board(curhash);

            String[] hashes = curboard.boardhashes().split(",");
            for (String hash : hashes) {
                if (indexed.contains(hash)) {
                    System.out.println("duplicate state: " + hash);
                }
                indexed.add(hash);
            }
        }
    }
}
