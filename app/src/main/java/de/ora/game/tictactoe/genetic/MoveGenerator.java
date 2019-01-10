package de.ora.game.tictactoe.genetic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ora.game.tictactoe.game.Board;
import de.ora.game.tictactoe.game.Coordinate;
import de.ora.game.tictactoe.game.Player;

public class MoveGenerator {
    private Board board;
    private Map<Board, List<Move>> possibleMovesPlayer1;
    private Map<Board, List<Move>> possibleMovesPlayer2;

    public MoveGenerator(Board board) {
        this.board = board;
        possibleMovesPlayer1 = new HashMap<>();
        possibleMovesPlayer2 = new HashMap<>();
    }

    public void generate() {
        Player player = this.board.activePlayer();

        List<Coordinate> freeCells = this.board.freeCells();
        while (!(freeCells = board.freeCells()).isEmpty()) {

        }
    }
}
