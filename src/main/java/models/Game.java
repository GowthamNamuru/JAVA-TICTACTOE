package main.java.models;

import main.java.exceptions.InvalidGameException;
import main.java.strategies.gameWinningStrategy.GameWinningStrategy;
import main.java.strategies.gameWinningStrategy.OrderOneGameWinningStrategy;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;

    private GameStatus gameStatus;

    private int nextPlayerIndex;

    private GameWinningStrategy gameWinningStrategy;
    private Player winner;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }


    public void displayBoard() {
        this.board.display();
    }

    public void makeNextMove() {
        Player playerToMove = players.get(nextPlayerIndex);

        System.out.println("Player " + playerToMove.getName() + " is making a move with Symbol :" + playerToMove.getSymbol());

        Move move = playerToMove.decideMove(this.board);

        int row = move.getCell().getRow();
        int column = move.getCell().getColumn();
        Cell cell = move.getCell();

        // Validate this move
        // Check if the cell is the empty cell
        if (cell.getCellState().equals(CellState.EMPTY)) {
            board.getBoard().get(row).get(column).setCellState(CellState.FILLED);
            board.getBoard().get(row).get(column).setPlayer(playerToMove);
            moves.add(move);
        }

        if (gameWinningStrategy.checkWinner(board, playerToMove, board.getBoard().get(row).get(column))) {
            gameStatus = GameStatus.ENDED;
            winner = playerToMove;
        }
        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();

        // Need to check for winning, draw
    }

    public Player getWinner() {
        return winner;
    }

    public static class GameBuilder {
        private int dimension;
        private List<Player> players;


        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean isValid() throws InvalidGameException {
            if (dimension < 3) {
                throw new InvalidGameException("Dimension of the game cannot be less than 3");
            }

            if (players.size() != dimension - 1) {
                throw new InvalidGameException("Number of players should be equal to dimenion - 1");
            }
            return true;
        }

        public Game build() throws InvalidGameException {
            try {
                isValid();
            } catch (Exception e) {
                throw new InvalidGameException("Invalid game");
            }

            Game game = new Game();
            game.setGameStatus(GameStatus.INPROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setBoard(new Board(dimension));
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));
            return game;
        }
    }
}
