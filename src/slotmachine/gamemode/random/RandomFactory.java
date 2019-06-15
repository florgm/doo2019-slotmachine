package slotmachine.gamemode.random;

import slotmachine.gamemode.GameMode;
import slotmachine.gamemode.GameModeAbstractFactory;
import slotmachine.gamemode.randomize.IRandomize;

import java.util.List;

public class RandomFactory implements GameModeAbstractFactory {
    private List<Integer> reelSize;
    private IRandomize rand;

    public RandomFactory (List<Integer> reelSize, IRandomize rand) {
        this.reelSize = reelSize;
        this.rand = rand;
    }

    @Override
    public GameMode createGameMode() {
        return new Random(reelSize, rand);
    }
}
