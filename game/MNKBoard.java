package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {

    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );
    private final Cell[][] cells;
    private int m, n, k;
    private Cell turn;
    private int moveNumber = 0;

    public MNKBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        this.turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Board createEmptyClone() {
        return new MNKBoard(this.m, this.n, this.k);
    }

    @Override
    public Cell getCell(int r, int c) {
        return cells[r][c];
    }

    private boolean existsCell(int r, int c) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }

    private int checkDir(int sx, int sy, int dx, int dy) {
        int cnt = 0;
        for (int i = 0; i <= k && existsCell(sx, sy); ++i) {
            if (this.cells[sx][sy] != this.getCell()) return cnt;
            cnt++;
            sx += dx;
            sy += dy;
        }
        return cnt;
    }

    private int check(int sx, int sy, int dx, int dy) {
        final int a = checkDir(sx, sy, dx, dy);
        final int b = checkDir(sx, sy, -dx, -dy);
        final int res = a + b - 1;
        if (res >= k) return k;
        return (a <= 4 && b <= 4 && res >= 4) ? 1 : 0;
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        int res1 = check(move.getRow(), move.getColumn(), 0, 1);
        int res2 = check(move.getRow(), move.getColumn(), 1, 0);
        int res3 = check(move.getRow(), move.getColumn(), 1, 1);
        int res4 = check(move.getRow(), move.getColumn(), -1, 1);
        turn = (turn == Cell.X) ? Cell.O : Cell.X;
        if (res1 == k) return Result.WIN;
        if (res2 == k) return Result.WIN;
        if (res3 == k) return Result.WIN;
        if (res4 == k) return Result.WIN;
        if (++moveNumber == n * m) return Result.DRAW;
        if (res1 == 1 || res2 == 1 || res3 == 1 || res4 == 1) {
            turn = move.getValue();
            return Result.REPEAT;
        }
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(Move move) {
        return existsCell(move.getRow(), move.getColumn())
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && move.getValue() == getCell();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(" ");
        for (int i = 0; i < n; ++i) res.append(i);
        for (int i = 0; i < m; ++i) {
            res.append('\n');
            res.append(i);
            for (int j = 0; j < n; ++j) {
                res.append(SYMBOLS.get(cells[i][j]));
            }
        }
        return res.toString();
    }

    /*
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" 012");
        for (int r = 0; r < 3; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < 3; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
    */

    @Override
    public Cell getCell() {
        return turn;
    }
}
