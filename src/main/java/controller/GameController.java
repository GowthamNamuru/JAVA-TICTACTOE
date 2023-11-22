package main.java.controller;

import main.java.exceptions.InvalidGameException;
import main.java.models.Board;
import main.java.models.Game;
import main.java.models.GameStatus;
import main.java.models.Player;

import java.util.List;

public class GameController {
    // It should have all the methods required to play the game
    public void undo(Game game) {

    }

    public Game createGame(int dimension, List<Player> players) throws InvalidGameException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players).build();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void executeNextMove(Game game) {
        game.makeNextMove();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }
}
