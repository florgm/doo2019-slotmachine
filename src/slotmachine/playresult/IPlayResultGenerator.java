package slotmachine.playresult;

import slotmachine.reelrelated.Reel;

import java.util.List;

public interface IPlayResultGenerator {
    void setReels(List<Reel> reels);
    void setReelsResults(List<Integer> reelsResults);
    List<String> getSymbolResult();
    int getResult(int bet);
}
