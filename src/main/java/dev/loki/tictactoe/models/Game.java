package dev.loki.tictactoe.models;

import dev.loki.tictactoe.exception.EmptyMoveException;
import dev.loki.tictactoe.exception.GameInvalidationException;
import dev.loki.tictactoe.exception.InvalidMoveException;
import dev.loki.tictactoe.strategies.WinningStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class Game {
    @Setter
    private Board board;
    @Setter
    private List<Player> players;
    @Setter
    private GameState gameState;
    @Setter
    private Player winner;
    private int nextPlayerMoveIndex;
    private List<Move> moves;

    private WinningStrategy winningStrategy;

    Game(int dimension, List<Player> players) {
        this.board = new Board(dimension);
        this.players = players;
        this.nextPlayerMoveIndex = 0;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.winningStrategy = new WinningStrategy() {
            @Override
            public boolean checkWinner(Board board, Move move) {
                return false;
            }

            @Override
            public void undoMove(Move lastMove, int dimension) {

            }
        };
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public WinningStrategy getWinningStrategy() {
        return winningStrategy;
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean validate() {
            return true;
        }

        public Game build() throws Exception {
            if (!validate()) {
                new GameInvalidationException("Invalid game");
            }
            return new Game(dimension, players);
        }

    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public void displayBoard(Board board) {
        board.displayBoard();
    }

    private boolean checkWinner(Board board, Move move) {
        return winningStrategy.checkWinner(board, move);
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        return row < board.getDimension() && row >= 0 && col < getBoard().getDimension() && col >= 0 &&
                board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerMoveIndex);

        System.out.println("It is " + currentPlayer.getName() + "'s move.");
        Move move = currentPlayer.makeMove(board);

        System.out.println(currentPlayer.getName() + " has made a move at Row: " + move.getCell().getRow() +
                ", col: " + move.getCell().getCol() + ".");

        //Validate the move before we apply the move on Board.
        if (!validateMove(move)) {
            System.out.println("Invalid move by player: " + currentPlayer.getName());
            throw new InvalidMoveException("Invalid move made by player: " + currentPlayer.getName());
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell finalCellToMakeMove = board.getBoard().get(row).get(col);
        finalCellToMakeMove.setCellState(CellState.FILLED);
        finalCellToMakeMove.setPlayer(currentPlayer);

        Move finalMove = new Move(currentPlayer, finalCellToMakeMove);
        moves.add(finalMove);

        nextPlayerMoveIndex += 1;
        nextPlayerMoveIndex %= players.size();
        if (checkWinner(board, finalMove)) {
            gameState = GameState.ENDED;
            winner = currentPlayer;
        } else if (moves.size() == board.getDimension() * board.getDimension()) {
            gameState = GameState.DRAW;
        }
    }

    public void undo() throws EmptyMoveException {
        if (!moves.isEmpty()) {
            Move lastMove = moves.remove(moves.size() - 1);
            board.undoMove(lastMove);

            nextPlayerMoveIndex = (nextPlayerMoveIndex - 1 + players.size()) % players.size();
            ((WinningStrategy) winningStrategy).undoMove(lastMove, board.getDimension());
        } else {
            throw new EmptyMoveException("Undo operation can't be performed as the moves list is empty");
        }
    }
}
