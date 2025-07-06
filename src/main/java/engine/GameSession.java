package engine;

import living.entity.Player;

public class GameSession {

    private static final GameSession instance = new GameSession();

    private Player player;

    public static GameSession getInstance() {
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
