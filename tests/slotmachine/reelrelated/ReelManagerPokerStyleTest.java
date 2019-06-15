package slotmachine.reelrelated;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReelManagerPokerStyleTest {
    /*@Test
    void testSetReels() {
        ReelManagerPokerStyle reelManager = new ReelManagerPokerStyle();

        reelManager.setListener(new IReelManagerListener() {
            @Override
            public void onReelsFinished() {

            }

            @Override
            public void notifyReelHandler() {

            }
        });

        List<String> symbols = new ArrayList<>();
        symbols.add("HA");
        symbols.add("D2");
        symbols.add("C8");
        symbols.add("H2");
        symbols.add("D9");
        symbols.add("SK");

        List<Reel> reels = new ArrayList<>();
        reels.add(new Reel(symbols));
        reels.add(new Reel(symbols));
        reels.add(new Reel(symbols));
        reels.add(new Reel(symbols));
        reels.add(new Reel(symbols));

        reelManager.setReels(reels);

        assertEquals(reels, reelManager.getReels());
    }*/

    @Test
    void testGetReelsSizes() {
        ReelManagerPokerStyle reelManager = new ReelManagerPokerStyle();

        reelManager.setListener(new IReelManagerListener() {
            @Override
            public void onReelsFinished() {

            }

            @Override
            public void notifyReelHandler() {

            }
        });

        List<String> symbols1 = new ArrayList<>();
        symbols1.add("HA");
        symbols1.add("D2");
        symbols1.add("C8");
        symbols1.add("H2");
        symbols1.add("D9");
        symbols1.add("SK");

        List<String> symbols2 = new ArrayList<>();
        symbols2.add("H5");
        symbols2.add("D7");
        symbols2.add("CJ");

        List<Reel> reels = new ArrayList<>();
        reels.add(new Reel(symbols1));
        reels.add(new Reel(symbols2));
        reels.add(new Reel(symbols1));
        reels.add(new Reel(symbols2));
        reels.add(new Reel(symbols1));

        reelManager.setReels(reels);

        List<Integer> expValues = new ArrayList<>();
        expValues.add(6);
        expValues.add(3);
        expValues.add(6);
        expValues.add(3);
        expValues.add(6);

        assertEquals(expValues, reelManager.getReelsSizes());

    }

    /*@Test
    void testGetReels() {
    }*/
}