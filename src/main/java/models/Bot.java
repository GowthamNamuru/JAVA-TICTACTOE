package main.java.models;

import main.java.factories.BotPlayingStrategyFactory;
import main.java.strategies.botPlayingStrategy.BotPlayingStrategy;
import main.java.strategies.botPlayingStrategy.RandomBotPlayingStrategy;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(char symbol, String name, BotDifficultyLevel difficultyLevel) {
        super(symbol, name);
        this.botDifficultyLevel = difficultyLevel;
        botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForEnum(BotDifficultyLevel.EASY);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }

    public Move decideMove(Board board) {
        return botPlayingStrategy.decideMove(this, board);
    }

}
