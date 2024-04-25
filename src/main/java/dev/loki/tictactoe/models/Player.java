package dev.loki.tictactoe.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class Player {

    private String name;
    private Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public Move makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please tell the row index to make a move");
        int rowNumber = scanner.nextInt();

        System.out.println("Please tell the col index to make a move");
        int colNumber = scanner.nextInt();

        return new Move(this, new Cell(rowNumber, colNumber));
    }
}

