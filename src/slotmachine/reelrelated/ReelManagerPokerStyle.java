package slotmachine.reelrelated;

import slotmachine.ui.handler.IReelHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReelManagerPokerStyle implements IReelManager, IReelListener {
    private List<Reel> reels = new ArrayList<>();
    private List<Reel> spinningReels;
    private List<IReelHandler> reelHandlers;
    private IReelManagerListener reelManagerListener;

    public ReelManagerPokerStyle() {
        spinningReels = new ArrayList<>();
    }

    @Override
    public void setListener(IReelManagerListener reelManagerListener) {
        this.reelManagerListener = reelManagerListener;
    }

    @Override
    public List<Integer> setReels(int reelQuantity, String stringSymbols) {
        List<String> reelSymbols = Arrays.asList(stringSymbols.split(","));
        Collections.shuffle(reelSymbols);
        List<Integer> reelSize = new ArrayList<>();

        int remainder = 52 % reelQuantity;
        int symbolsQuantity = (52 - remainder) / reelQuantity;

        for(int j = 0; j < reelQuantity; j++) {
            List<String> symbols = new ArrayList<>();
            int start = symbolsQuantity*j;
            int end;

            if(j == reelQuantity-1) {
                end = 52;
            }
            else {
                end = start + symbolsQuantity;
            }

            for(int k = start; k < end; k++) {
                symbols.add(reelSymbols.get(k));
            }

            reelSize.add(end-start);
            reels.add(new Reel(symbols, this));
        }

        return reelSize;
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
        spinningReels.remove(r);

        if(spinningReels.size() == 0) {
            reelManagerListener.onReelsFinished();
        }
    }

    @Override
    public void reelUpdate(Reel r) {
        reelHandlers.get(reels.indexOf(r)).setSymbol(r.getSymbols().get(r.getCurrentValue()));
    }

    public void setReelHandlers(List<IReelHandler> reelHandlers) {

        this.reelHandlers = reelHandlers;
    }
}
