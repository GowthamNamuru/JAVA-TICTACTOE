package main.java.strategies.gameWinningStrategy;

import main.java.models.Board;
import main.java.models.Cell;
import main.java.models.Player;

public interface GameWinningStrategy {

    boolean checkWinner(Board board, Player lastMovedPlayer, Cell cell);
}
