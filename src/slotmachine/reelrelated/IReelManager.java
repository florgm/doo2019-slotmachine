package slotmachine.reelrelated;

import java.util.List;

public interface IReelManager {
    void setListener(IReelManagerListener reelManagerListener);
    void setReels(List<Reel> reels);
    List<Integer> getReelsSizes();
    List<Reel> getReels();
    void spinReels(int spins, List<Integer> results);
}
