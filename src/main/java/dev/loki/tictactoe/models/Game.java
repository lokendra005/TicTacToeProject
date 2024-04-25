package dev.loki.tictactoe.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class Game {
    private Board board;
    private ArrayList<Player> ListOfPlayers;
    private int nextPlayerMoveIndex;
    Player winner;
    ArrayList<Move> ListOfMoves;
    GameState gameState;
}
