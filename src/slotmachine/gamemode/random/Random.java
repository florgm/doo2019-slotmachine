package slotmachine.gamemode.random;

import slotmachine.gamemode.GameMode;

import java.util.ArrayList;
import java.util.List;

public class Random extends GameMode {
    private List<Integer> reelSize;

    public Random (List<Integer> reelSize) {
        this.reelSize = reelSize;
    }

    @Override
    public List<Integer> getNextValues() {
        return getRandoms(reelSize);
    }

}
