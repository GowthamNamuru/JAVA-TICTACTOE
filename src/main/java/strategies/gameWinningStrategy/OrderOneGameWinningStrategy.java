package main.java.strategies.gameWinningStrategy;

import main.java.models.Board;
import main.java.models.Cell;
import main.java.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy {
    // 3 * 3 board -> 3 maps for rows, 3 maps for columns & 2 maps for diagonals.


    List<HashMap<Character, Integer>> rowSymbolCounts = new ArrayList<>();
    List<HashMap<Character, Integer>> columnSymbolCounts = new ArrayList<>();

    HashMap<Character, Integer> topRightSymbolCounts = new HashMap<>();
    HashMap<Character, Integer> topLeftSymbolCounts = new HashMap<>();

    public OrderOneGameWinningStrategy(int dimension) {
        for (int i = 0; i < dimension; i++) {
            rowSymbolCounts.add(new HashMap<>());
            columnSymbolCounts.add(new HashMap<>());
        }
    }

    @Override
    public boolean checkWinner(Board board, Player lastMovedPlayer, Cell cell) {
        char symbol = cell.getPlayer().getSymbol();
        int row = cell.getRow();
        int column = cell.getColumn();
        int dimension = board.getBoard().size();

        // Update rows in List
        if (!rowSymbolCounts.get(row).containsKey(symbol)) {
            rowSymbolCounts.get(row).put(symbol, 0);
        }
        rowSymbolCounts.get(row).put(symbol,
                rowSymbolCounts.get(row).get(symbol) + 1);

        // Update columns in List
        if (!columnSymbolCounts.get(column).containsKey(symbol)) {
            columnSymbolCounts.get(column).put(symbol, 0);
        }
        columnSymbolCounts.get(column).put(symbol,
                columnSymbolCounts.get(column).get(symbol) + 1);

        if (isCellOnTopLeftDiagonal(row, column)) {
            if (!topLeftSymbolCounts.containsKey(symbol)) {
                topLeftSymbolCounts.put(symbol, 0);
            }

            topLeftSymbolCounts.put(symbol, topLeftSymbolCounts.get(symbol) + 1);
        }

        if (isCellOnTopRightDiagonal(row, column, dimension)) {
            if (!topRightSymbolCounts.containsKey(symbol)) {
                topRightSymbolCounts.put(symbol, 0);
            }

            topRightSymbolCounts.put(symbol, topRightSymbolCounts.get(symbol) + 1);
        }

        // If we are winning the game in the row or column
        if (rowSymbolCounts.get(row).get(symbol) == dimension ||
                columnSymbolCounts.get(row).get(symbol) == dimension) {
            return true;
        }

        if (isCellOnTopLeftDiagonal(row,column) &&
                topLeftSymbolCounts.get(symbol) == dimension) {
            return true;
        }

        if (isCellOnTopRightDiagonal(row,column, dimension) &&
                topRightSymbolCounts.get(symbol) == dimension) {
            return true;
        }


        return false;
    }

    private boolean isCellOnTopLeftDiagonal(int row, int column) {
        return row == column;
    }

    private boolean isCellOnTopRightDiagonal(int row, int column, int dimension) {
        return row + column == dimension - 1;
    }
}
