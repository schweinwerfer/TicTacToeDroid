package de.ora.game.tictactoe.genetic;

import de.ora.game.tictactoe.game.Board;
import de.ora.game.tictactoe.game.Coordinate;

public class Move {
    private Board board;
    private Coordinate move;


    public Move(final Board board, final Coordinate move) {
        this.board = board.copy();
        this.move = move;
    }
}
