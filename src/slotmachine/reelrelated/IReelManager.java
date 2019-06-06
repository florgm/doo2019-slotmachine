package slotmachine.reelrelated;

import java.util.List;

public interface IReelManager {
    void setListener(IReelManagerListener reelManagerListener);
    List<Integer> setReels(int reelQuantity, String stringSymbols);
    List<Reel> getReels();
    void spinReels();
}
