package slotmachine.gamemode;

import slotmachine.gamemode.randomize.IRandomize;

import java.util.ArrayList;
import java.util.List;

public abstract class GameMode {
    private IRandomize randomize;

    public GameMode(IRandomize randomize) {
        this.randomize = randomize;
    }

    public abstract List<Integer> getNextValues();

    public void setRandomize(IRandomize rand) {
        this.randomize = rand;
    }

    protected List<Integer> getRandoms(List<Integer> reelSize) {
        List<Integer> result = new ArrayList<>();


        for(int i : reelSize) {
            result.add(Math.abs(randomize.nextInt()%i));
        }


        return result;
    }

}
