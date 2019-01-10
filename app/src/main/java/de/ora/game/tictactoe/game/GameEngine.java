package de.ora.game.tictactoe.game;

public class GameEngine {
    private Board board = new ThreeXThreeBoard();

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

    public char computer() {
        return 0;
    }

    public double elements(int x, int y) {
        return board.get(x, y);
    }

    public void newGame() {
        board = new ThreeXThreeBoard();
    }
}
