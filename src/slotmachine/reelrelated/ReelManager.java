package slotmachine.reelrelated;

import slotmachine.settings.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReelManager implements IReelListener {
    private List<Reel> reels = new ArrayList<>();
    private List<Reel> spinningReels;
    private Settings settings;
    private IReelManagerListener reelManagerListener;

    public ReelManager(IReelManagerListener reelManagerListener) {
        settings = Settings.getInstance();
        this.reelManagerListener = reelManagerListener;
        spinningReels = new ArrayList<>();
    }

    public List<Integer> setReels(int reelQuantity) {
        List<String> reelSymbols = Arrays.asList(settings.getProperties().getProperty("symbols").split(","));
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

    public List<Reel> getReels() {

        return reels;
    }


    public void spinReels(){
        spinningReels.addAll(reels);
        reels.forEach(reel -> reel.spinReel());
    }


    //TODO ver como le comunica ReelManager a SlotMachine que los reels dejaron de girar

    public void spinFinished() {

    }

    @Override
    public void spinFinished(Reel r) {
        spinningReels.remove(r);

        if(spinningReels.size() == 0) {
            reelManagerListener.onReelsFinished();
        }
    }
}
