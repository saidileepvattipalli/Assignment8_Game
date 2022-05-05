import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PegSolitaire pegSolitaire = new PegSolitaire("Result.txt");
        System.out.println("Game Solved, please check the .txt file for results.");
    }
}