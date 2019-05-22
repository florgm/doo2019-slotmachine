package slotmachine.gamemode.random;

import slotmachine.gamemode.GameMode;
import slotmachine.gamemode.randomize.IRandomize;

import java.util.List;

public class Random extends GameMode {
    private List<Integer> reelSize;

    public Random (List<Integer> reelSize, IRandomize rand) {
        super(rand);
        this.reelSize = reelSize;
    }

    @Override
    public List<Integer> getNextValues() {
        return getRandoms(reelSize);
    }

}
