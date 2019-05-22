package slotmachine.gamemode.sequence;

import slotmachine.gamemode.GameMode;
import slotmachine.gamemode.GameModeAbstractFactory;

import java.util.List;

public class SequenceFactory implements GameModeAbstractFactory {
    private List<Integer> reelSize;
    private int quantity;

    public SequenceFactory (List<Integer> reelSize, int quantity) {
        this.reelSize = reelSize;
        this.quantity = quantity;
    }

    @Override
    public GameMode createGameMode() {
        return new Sequence(reelSize, quantity);
    }
}
