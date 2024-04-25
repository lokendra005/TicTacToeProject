package dev.loki.tictactoe.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

public class Board {
    private int dimension;
    private List<List<Cell>> board;

    public Board(int dimension) {
        this.dimension = dimension;
        board = new ArrayList<>();

        for (int i = 0; i < dimension; i++) {
            board.add(new ArrayList<>());

            for (int j = 0; j < dimension; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Cell cell = board.get(i).get(j);
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    System.out.print("|  |");
                } else {
                    System.out.print("|" + cell.getPlayer().getSymbol().getSymbol() + "|");
                }
            }
            System.out.println();
        }
    }

    public void undoMove(Move move) {
        int removedRow = move.getCell().getRow();
        int removedCol = move.getCell().getCol();

        Cell cell = board.get(removedRow).get(removedCol);
        cell.setPlayer(null);
        cell.setCellState(CellState.EMPTY);
    }
}
