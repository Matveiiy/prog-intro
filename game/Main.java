package game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static Board readCircleBoard(Scanner scan) {
        int d = scan.nextInt(), k = scan.nextInt();
        return new CircleBoard(d, k);
    }

    public static Board readMnkBoard(Scanner scan) {
        int m = scan.nextInt(), n = scan.nextInt(), k = scan.nextInt();
        return new MNKBoard(m, n, k);
    }

    public static void playTournament(Scanner scan, Board b) {
        while (true) {
            try {
                System.out.println("Enter number of players");
                int n = scan.nextInt();
                java.util.List<Player> players = new ArrayList<Player>();
                for (int i = 0; i < n; ++i) players.add(new RandomPlayer());
                Tournament t = new Tournament(players);
                t.play(b);
                return;
            } catch (Exception e) {
                System.out.println("Enter an integer");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Board b;
        while (true) {
            try {
                System.out.println("Circle/MNK board? c/m");
                String s = scan.nextLine();
                if (Objects.equals(s, "c")) {
                    System.out.println("Input diameter and k");
                    b = new CircleBoard(scan.nextInt(), scan.nextInt());
                } else if (Objects.equals(s, "m")) {
                    System.out.println("Input n, m, k");
                    b = new MNKBoard(scan.nextInt(), scan.nextInt(), scan.nextInt());
                } else {
                    throw new InputMismatchException("Incorrect input");
                }
                break;
            } catch (Exception e) {
                System.out.println("Input c or m");
            }
        }
        while (true) {
            try {
                System.out.println("Play Tournament? y/n");
                String s = scan.next();
                if (Objects.equals(s, "y")) {
                    playTournament(scan, b);
                    return;
                } else if (!Objects.equals(s, "n")) throw new InputMismatchException("Incorrect input");
                break;
            } catch (Exception e) {
                System.out.println("Input y or n");
            }
        }
        final Game game = new Game(false, new RandomPlayer(), new HumanPlayer());
        int result;
        do {
            result = game.play(b.createEmptyClone());
            System.out.println("Finished. Winner: " + result);
        } while (result == 0);

    }
}
