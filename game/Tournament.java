package game;

import java.util.*;


public class Tournament {
    List<Player> players;

    public Tournament(List<Player> players) {
        this.players = players;
    }

    public int playGame(int player1, int player2, Board b) {
        int result;
        System.out.print("Player " + String.valueOf(player1) + " and player " + String.valueOf(player2) + " are playing... ");
        do {
            result = new Game(false, players.get(player1), players.get(player2)).play(b.createEmptyClone());
        } while (result == 0);
        System.out.println("Player " + (result == 1 ? player1 : player2) + " won");
        return result;
    }

    private List<Integer> playRound(Board b, List<Integer> curRound, int[] ans, int ngames, int nround) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < ngames; ++i) {
            int player1 = curRound.get(curRound.size() - 1);
            curRound.remove(curRound.size() - 1);
            int player2 = curRound.get(curRound.size() - 1);
            curRound.remove(curRound.size() - 1);
            if (playGame(player1, player2, b) == 1) {
                res.add(player1);
                ans[player2] = nround;
            } else {
                res.add(player2);
                ans[player1] = nround;
            }
        }
        res.addAll(curRound);
        return res;
    }

    public void play(Board b) {
        if (players.isEmpty()) return;
        int[] place = new int[players.size()];
        List<Integer> nextRound = new ArrayList<Integer>(players.size());

        int nround = players.isEmpty() ? 0 : 31 - Integer.numberOfLeadingZeros(players.size() - 1);
        int nextPow2 = 1 << nround;
        nround += 2;

        for (int i = 0; i < players.size(); ++i) nextRound.add(i);
        Collections.shuffle(nextRound);

        while (nextRound.size() != 1) {
            List<Integer> curRound = nextRound;
            nextRound = playRound(b, curRound, place, curRound.size() - nextPow2, nround);
            nextPow2 /= 2;
            nround -= 1;
        }
        place[nextRound.get(0)] = 1;
        List<AbstractMap.Entry<Integer, Integer>> res = new ArrayList<>(players.size());
        for (int i = 0; i < players.size(); ++i) {
            res.add(new AbstractMap.SimpleEntry<Integer, Integer>(place[i], i + 1));
        }
        res.sort(new Comparator<AbstractMap.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> l, Map.Entry<Integer, Integer> r) {
                return l.getKey().compareTo(r.getKey());
            }
        });
        System.out.println("_____________________________________");
        int start = 1, p = 1, change = 1;
        for (int i = 0; i < players.size(); ++i) {
            String ans = String.valueOf(start) + "-" + String.valueOf(change);
            if (start == change) {
                ans = String.valueOf(start);
            }
            System.out.println("Player " + String.valueOf(res.get(i).getValue()) + " place: " + ans);
            --p;
            if (p == 0) {
                start = change + 1;
                change = change * 2;
                change = Math.min(players.size(), change);
                p = change - start + 1;
            }

        }
    }

    public String toString() {
        return "";
    }
}
