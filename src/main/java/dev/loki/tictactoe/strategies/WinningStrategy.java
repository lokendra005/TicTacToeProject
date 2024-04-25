package dev.loki.tictactoe.strategies;

import dev.loki.tictactoe.models.Board;
import dev.loki.tictactoe.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);

    void undoMove(Move lastMove, int dimension);
}
