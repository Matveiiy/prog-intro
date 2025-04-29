package game;

public interface Board {
    Position getPosition();

    Board createEmptyClone();

    Cell getCell();

    Result makeMove(Move move);
}
