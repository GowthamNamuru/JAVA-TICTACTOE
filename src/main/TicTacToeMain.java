package main;

import main.java.controller.GameController;
import main.java.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TicTacToeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give the dimension of the board");
        int dimension = scanner.nextInt();

        System.out.println("Is there any bot player? y/n");

        String isBotPlayer = scanner.next();

        List<Player> players = new ArrayList<>();
        int numberOfPlayers = dimension - 1;

        if (isBotPlayer.equals("y")) {
            numberOfPlayers = numberOfPlayers - 1;
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Enter details of the player number: " + i);

            String playerName = scanner.next();

            System.out.println("Enter symbol for player number: " + i);
            char playerSymbol = scanner.next().charAt(0);

            Player player = new Player(playerSymbol, playerName);
            players.add(player);
        }

        if (isBotPlayer.equals("y")) {
            System.out.println("Enter details of bot player: ");

            String playerName = scanner.next();

            System.out.println("Enter symbol for bot player: ");
            char playerSymbol = scanner.next().charAt(0);

            Bot player = new Bot(playerSymbol, playerName, BotDifficultyLevel.MEDIUM);
            players.add(player);
        }

        GameController gameController = new GameController();
        try {
            Game game = gameController.createGame(dimension, players);
            while (gameController.getGameStatus(game).equals(GameStatus.INPROGRESS)) {
                System.out.println("This is the current board");
                game.displayBoard();

                System.out.println("Do you want to undo? y/n");

                String input = scanner.next();

                if (input.equals("y")) {
                    gameController.undo(game);
                } else {
                    gameController.executeNextMove(game);
                }
            }

            System.out.println("Game has ended. Result is: ");
            if (!gameController.getGameStatus(game).equals(GameStatus.DRAW)) {
                System.out.println(gameController.getWinner(game).getName() + " is the winner of the game");
            }

        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
    }
}