package de.ora.game.tictactoe.genetic;

import java.util.LinkedList;
import java.util.List;

import de.ora.game.tictactoe.game.Board;
import de.ora.game.tictactoe.game.Coordinate;
import de.ora.game.tictactoe.game.GameResult;
import de.ora.game.tictactoe.game.Player;

public class Trainer extends PlayingAgent {
    private boolean initDone = false;

    public Trainer() {
    }

    public Trainer(Board board) {
        super("trainer");
        this.initDone = init(board, board.freeCells());
    }

    public Trainer(PlayingAgent father, PlayingAgent mother) {
        throw new UnsupportedOperationException("Trainer cannot be combined!");
    }

    private boolean init(final Board board, List<Coordinate> freeCellsCurr) {
        if (this.initDone) {
            return true;
        }

        List<Coordinate> freeCells = freeCellsCurr;

        while (!(freeCells = board.freeCells()).isEmpty()) {
            String key = board.asKey();
            if (this.moves.containsKey(key)) {
                return true;
            }

            final List<Coordinate> currentFreeCells = new LinkedList<>(freeCells);
            List<Coordinate> coordinates = this.moves.computeIfAbsent(key, k ->
                    new LinkedList<>(currentFreeCells)
            );

            for (Coordinate move : coordinates) {
                Board copy = board.copy();
                boolean valid = copy.set(move);
                List<Coordinate> freeCellsAfterMove = copy.freeCells();
                boolean freeCellsLeft = !freeCellsAfterMove.isEmpty();
                boolean seekFurther = valid && freeCellsLeft && copy.findWinner() == Player.NONE;

                if (seekFurther) {
                    init(copy, freeCellsAfterMove);
                }

            }
        }

        return true;
    }

    @Override
    public Coordinate play(Player player, Board board) {
        if (!initDone) {
            throw new IllegalStateException("Not initialized!");
        }

        List<Coordinate> possibleMoves = this.moves.get(board.asKey());
        return possibleMoves.get(rnd.nextInt(possibleMoves.size()));
    }

    public boolean isInitDone() {
        return initDone;
    }

    @Override
    public void feedback(GameResult result) {
    }
}
