package main.java.strategies.botPlayingStrategy;

import main.java.models.Board;
import main.java.models.Move;
import main.java.models.Player;

public interface BotPlayingStrategy {
    Move decideMove(Player player, Board board);
}
