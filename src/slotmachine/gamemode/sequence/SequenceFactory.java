package slotmachine.gamemode.sequence;

import slotmachine.gamemode.GameMode;
import slotmachine.gamemode.GameModeAbstractFactory;

import java.util.ArrayList;
import java.util.List;

public class SequenceFactory implements GameModeAbstractFactory {
    private List<Integer> reelSize;
    private List<List<Integer>> sequence;
    private int index;

    public SequenceFactory (List<Integer> reelSize, int cantidad) {
        this.reelSize = reelSize;
        sequence = new ArrayList<List<Integer>>();
        index = 0;

        for(int i = 0; i  < cantidad; i++) {
            for(int j : reelSize) {
                sequence.add(getRandoms(reelSize));
            }
        }
    }

    @Override
    public GameMode createGameMode() {
        return new Sequence();
    }
}
