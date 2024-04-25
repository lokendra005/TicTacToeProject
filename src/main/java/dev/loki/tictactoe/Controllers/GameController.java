package dev.loki.tictactoe.Controllers;

import java.util.List;
import java.util.List;

import dev.loki.tictactoe.exception.EmptyMoveException;
import dev.loki.tictactoe.exception.InvalidMoveException;
import dev.loki.tictactoe.models.Game;
import dev.loki.tictactoe.models.GameState;
import dev.loki.tictactoe.models.Player;
import lombok.Getter;
import lombok.Setter;


import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.exceptions;

@Getter
@Setter
//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.exceptions;

public class GameController {

        public Game startGame(int dimension, List<Player> players) throws Exception {
            return Game.getBuilder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .build();
        }

        public void makeMove(Game game) throws InvalidMoveException {
            game.makeMove();
        }

        public void displayBoard(Game game) {
            game.displayBoard(game.getBoard());
        }

        public Player getWinner(Game game) {
            return game.getWinner();
        }

        public void undo(Game game) throws EmptyMoveException {
            game.undo();
        }

        public GameState getGameState(Game game) {
            return game.getGameState();
        }
}
