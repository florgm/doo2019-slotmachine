package slotmachine.playresult;

import slotmachine.reelrelated.Reel;

import java.util.List;

public interface IPlayResult {
    void setComponents(List<Integer> reelsResults, List<Reel> reels, int bet);
    List<String> getSymbolResult();
    int getResult();
}
