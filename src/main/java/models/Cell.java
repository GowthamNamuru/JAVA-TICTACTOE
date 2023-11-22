package main.java.models;

public class Cell {

    public Cell(int i, int j) {
        this.row = i;
        this.column = j;
        this.cellState = CellState.EMPTY;
    }

    private Player player;
    private int row;
    private int column;

    private CellState cellState;

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
