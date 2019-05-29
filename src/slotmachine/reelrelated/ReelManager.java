package slotmachine.reelrelated;

import java.util.ArrayList;
import java.util.List;

public class ReelManager implements IReel {
    private List<IReel> reels = new ArrayList<>();
    private int indice;

    public ReelManager(int reelQuantity) {
        indice = 0;
        for(int i = 0; i < reelQuantity; i++) {
            reels.add(new Reel());
            indice++;
        }
    }

    public void add( ) {

            this.reels.add(new Reel());
            indice++;

    }

    public void remove( ) {
            reels.remove(indice);
            indice--;

    }


    @Override
    public void spinReel(Object play) {
        List<Integer> results = (List<Integer>) play;

        for(int i = 0; i < reels.size() && i < results.size(); i++) {
            reels.get(i).spinReel(results.get(i));
        }
    }

    @Override
    public void returnPlay() {

    }
}
