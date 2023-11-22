package main.java.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int dimension;

    private List<List<Cell>> board;

    Board(int dimension) {
        this.board = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            this.board.add(new ArrayList<>());

            for (int j = 0; j < dimension; j++) {
                this.board.get(i).add(new Cell(i, j));
            }
        }
    }


    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public void display() {
        int dimension = this.dimension;

        for (int i = 0; i < this.board.size(); i++) {
            for (int j = 0; j < this.board.size(); j++) {
                if (this.board.get(i).get(j).getCellState() == CellState.FILLED) {
                    System.out.print("| " + board.get(i).get(j).getPlayer().getSymbol() + " | ");
                } else {
                    System.out.print("|  |");
                }
            }
            System.out.println();
        }
    }

}
