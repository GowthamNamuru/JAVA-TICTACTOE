package main.java.factories;

import main.java.models.BotDifficultyLevel;
import main.java.strategies.botPlayingStrategy.BotPlayingStrategy;
import main.java.strategies.botPlayingStrategy.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategyForEnum(BotDifficultyLevel difficultyLevel) {
        return new RandomBotPlayingStrategy();
    }

}
