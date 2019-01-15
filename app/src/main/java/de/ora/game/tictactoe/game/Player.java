package de.ora.game.tictactoe.game;

import java.util.HashMap;
import java.util.Map;

public enum Player {
    NONE(0),
    PLAYER1(1),
    PLAYER2(2);
    private static final Map<Integer, Player> lookup;

    static {
        lookup = new HashMap<>();

        for (Player player : Player.values()) {
            lookup.put(player.getCode(), player);
        }
    }

    private int code;


    Player(int code) {
        this.code = code;
    }

    public static Player from(int code) {
        return lookup.get(code);
    }

    public int getCode() {
        return code;
    }
}
