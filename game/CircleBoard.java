package game;

import java.util.Arrays;
import java.util.Map;

public class CircleBoard implements Board, Position {

    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );
    private final Cell[][] cells;
    private int movesToWin = 0;
    private int d, k, radius;
    private Cell turn;
    private int moveNumber = 0;

    public CircleBoard(int d, int k) {
        this.d = d;
        this.radius = d / 2;
        this.k = k;
        cells = new Cell[d + 1][d + 1];
        for (int i = 0; i <= d; ++i) {
            Arrays.fill(cells[i], Cell.E);
            for (int j = 0; j <= d; ++j) {
                movesToWin += (existsCell(i, j) == true) ? 1 : 0;
            }
        }
        this.turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Board createEmptyClone() {
        return new CircleBoard(this.d, this.k);
    }

    @Override
    public Cell getCell(int r, int c) {
        return cells[r][c];
    }

    private boolean existsCell(int r, int c) {
        final int offx = r - this.radius;
        final int offy = c - this.radius;
        return offx * offx + offy * offy <= this.radius * this.radius;
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
        if (++moveNumber == movesToWin) return Result.DRAW;
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
        res.append(" \t");
        final int bound = d - d % 2;
        for (int i = 0; i <= bound; ++i) res.append(i);
        for (int i = 0; i <= bound; ++i) {
            res.append('\n');
            res.append(i);
            res.append('\t');
            for (int j = 0; j <= bound; ++j) {
                if (existsCell(i, j)) res.append(SYMBOLS.get(cells[i][j]));
                else res.append(" ");
            }
        }
        return res.toString();
    }

    @Override
    public Cell getCell() {
        return turn;
    }
}
