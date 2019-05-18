package slotmachine.gamemode;

public class GameModeFactory {
    public static GameMode getGameMode(GameModeAbstractFactory factory) {
        return factory.createGameMode();
    }
}
