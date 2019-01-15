package de.ora.game.tictactoe.genetic;

import de.ora.game.tictactoe.game.Board;
import de.ora.game.tictactoe.game.Coordinate;

public interface AgentPlayFilter {
    Coordinate filter(final Board board);
}
