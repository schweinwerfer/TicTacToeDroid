package de.ora.game.tictactoe.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.ora.game.tictactoe.genetic.PlayingAgent;

public class GameEngine {
    private final Random random = new Random();
    private Board board = new ThreeXThreeBoard();
    private Player computerPlayer;
    private PlayingAgent agent;
    private List<PlayingAgent> agentList = new ArrayList<>();

    public Player checkWinner() {
        Player winner = board.findWinner();
        return winner;
    }

    public int possibleMoves() {
        return board.freeCells().size();
    }

    public boolean play(int x, int y) {
        return board.set(x, y);
    }

    public boolean computer() {
        if (agent != null) {
            final Coordinate move = agent.play(board.activePlayer(), board);
            return play(move.row, move.column);
        }
        return false;
    }

    public int elements(int x, int y) {
        return board.get(x, y);
    }

    public void newGame() {
        board = new ThreeXThreeBoard();
        this.agent = chooseAgent();
        this.computerPlayer = Player.PLAYER1 == this.computerPlayer ? Player.PLAYER2 : Player.PLAYER1;
        if (computerHasToPlay()) {
            computer();
        }
    }

    private PlayingAgent chooseAgent() {
        if (agentList.isEmpty()) {
            return null;
        }

        return agentList.get(random.nextInt(agentList.size()));
    }

    public void setAgents(List<PlayingAgent> agentList) {
        if (agentList == null || agentList.isEmpty()) {
            return;
        }
        this.agentList = agentList;
        agent = chooseAgent();
    }

    public PlayingAgent getAgent() {
        return agent;
    }

    public Player getComputerPlayer() {
        return computerPlayer;
    }

    public Player getActivePlayer() {
        return this.board.activePlayer();
    }

    public boolean computerHasToPlay() {
        return getComputerPlayer() == getActivePlayer();
    }
}
