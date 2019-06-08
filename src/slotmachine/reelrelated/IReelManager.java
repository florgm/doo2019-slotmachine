package slotmachine.reelrelated;

import slotmachine.ui.handler.IReelHandler;

import java.util.List;

public interface IReelManager {
    void setListener(IReelManagerListener reelManagerListener);
    List<Integer> setReels(int reelQuantity, String stringSymbols);
    void setReelHandlers(List<IReelHandler> reelHandlers);
    List<Reel> getReels();
    void spinReels(int spins, List<Integer> results);
}
