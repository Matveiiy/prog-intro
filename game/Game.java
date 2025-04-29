package game;

public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(Board board) {
        while (true) {
            final int result1 = moveRepeat(board, player1, 1);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = moveRepeat(board, player2, 2);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int moveRepeat(final Board board, final Player player, final int no) {
        int res = 3;
        while (res == 3) {
            res = move(board, player, no);
        }
        return res;
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(new PositionProxy(board.getPosition()), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else if (result == Result.REPEAT) {
            log("Repeat move");
            return 3;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
