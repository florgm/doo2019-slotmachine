package slotmachine.playresult;

import slotmachine.reelrelated.Reel;

import java.util.List;

public interface IPlayResult {
    void setReelsResults(List<Integer> reelsResults);
    void setReels(List<Reel> reels);
    void getResult();
}
