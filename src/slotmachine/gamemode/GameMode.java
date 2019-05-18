package slotmachine.gamemode;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public abstract class GameMode {
    public abstract List<Integer> getNextValues();
    protected List<Integer> getRandoms(List<Integer> reelSize) {
        List<Integer> result = new ArrayList<>();
        Random random = new Random();


        for(int i : reelSize) {
            result.add(random.nextInt()%i);
        }


        return result;
    }

}
