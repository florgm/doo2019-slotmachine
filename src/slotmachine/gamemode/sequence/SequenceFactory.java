package slotmachine.gamemode.sequence;

import slotmachine.gamemode.GameMode;
import slotmachine.gamemode.GameModeAbstractFactory;
import slotmachine.gamemode.randomize.IRandomize;

import java.util.List;

public class SequenceFactory implements GameModeAbstractFactory {
    private List<Integer> reelSize;
    private int quantity;
    private IRandomize rand;

    public SequenceFactory (List<Integer> reelSize, int quantity, IRandomize rand) {
        this.reelSize = reelSize;
        this.quantity = quantity;
        this.rand = rand;
    }

    @Override
    public GameMode createGameMode() {
        return new Sequence(reelSize, quantity, rand);
    }
}
