package slotmachine.reelrelated;

import java.util.ArrayList;
import java.util.List;

public class ReelManagerPokerStyle implements IReelManager, IReelListener {
    private List<Reel> reels = new ArrayList<>();
    private List<Reel> spinningReels;
    private IReelManagerListener reelManagerListener;
    private int updatedReels = reels.size();

    public ReelManagerPokerStyle() {
        spinningReels = new ArrayList<>();
    }

    @Override
    public void setListener(IReelManagerListener reelManagerListener) {
        this.reelManagerListener = reelManagerListener;
    }

    @Override
    public void setReels(List<Reel> reels) {
        this.reels = reels;

        for(Reel reel : this.reels) {
            reel.setReelListener(this);
        }
    }

    @Override
    public List<Integer> getReelsSizes() {
        List<Integer> reelsSizes = new ArrayList<>();

        for(Reel reel : this.reels) {
            reelsSizes.add(reel.getSymbols().size());
        }

        return reelsSizes;
    }

    @Override
    public List<Reel> getReels() {
        return reels;
    }

    @Override
    public void spinReels(int spins, List<Integer> results){
        spinningReels.addAll(reels);

        for(int j = 0; j < results.size(); j++) {
            reels.get(j).spinReel(spins, results.get(j));
        }
    }

    @Override
    public void spinFinished(Reel r) {
        synchronized (this) {
            spinningReels.remove(r);

            if(spinningReels.size() == 0) {
                reelManagerListener.onReelsFinished();
            }
        }
    }

    @Override
    public void reelUpdate(Reel r) {
        synchronized (this) {
            updatedReels = ++updatedReels % reels.size();

            if(updatedReels == 0)
                reelManagerListener.notifyReelHandler();
        }
    }
}
