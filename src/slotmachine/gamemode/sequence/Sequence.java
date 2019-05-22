package slotmachine.gamemode.sequence;

import slotmachine.gamemode.GameMode;
import slotmachine.gamemode.randomize.IRandomize;

import java.util.ArrayList;
import java.util.List;

public class Sequence extends GameMode {
    private List<Integer> reelSize;
    private List<List<Integer>> sequence;
    private int index;

    public Sequence (List<Integer> reelSize, int quantity, IRandomize rand) {
        super(rand);
        this.reelSize = reelSize;
        sequence = new ArrayList<List<Integer>>();
        index = 0;

        for(int i = 0; i < quantity; i++) {
            sequence.add(getRandoms(reelSize));
        }
    }

    @Override
    public List<Integer> getNextValues() {
        List<Integer> result;

        result = sequence.get(index);
        index = ++index%sequence.size();

        return result;
    }
}
