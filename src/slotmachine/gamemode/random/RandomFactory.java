package slotmachine.gamemode.random;

import slotmachine.gamemode.GameMode;
import slotmachine.gamemode.GameModeAbstractFactory;

import java.util.List;

public class RandomFactory implements GameModeAbstractFactory {
    private List<Integer> reelSize;

    public RandomFactory (List<Integer> reelSize) {
        this.reelSize = reelSize;
    }

    @Override
    public GameMode createGameMode() {
        return new Random(reelSize);
    }
}
