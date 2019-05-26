package slotmachine.reelrelated;

import java.util.ArrayList;
import java.util.List;

public class ReelManager implements IReel {
    private List<IReel> Reels = new ArrayList<IReel>();
    private List<Integer> results;

    public ReelManager(int reelQuantity) {
        for(int i = 0; i < reelQuantity; i++) {
            Reels.add(new Reel());
        }
    }

    public void add(int addQuantity) {
        for(int i = 0; i < addQuantity; i++) {
            this.Reels.add(new Reel());
        }
    }

    public void remove(int removeQuantity) {
        for(int i = 0; i < removeQuantity; i++) {
            Reels.remove(i);
        }
    }

    public void simulation(List<Integer> results) {
        this.results = results;
    }

    @Override
    public void spinReel(int play) {
        for(IReel ireel : Reels) {
            ireel.spinReel(results.get());
        }
    }

    @Override
    public void returnPlay() {

    }
}
