package slotmachine.playresult;

import org.junit.jupiter.api.Test;
import slotmachine.reelrelated.IReelListener;
import slotmachine.reelrelated.Reel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokerPlayResultTest {
    @Test
    void testGetResultPair() {
        PokerPlayResult playResult = new PokerPlayResult();

        List<Integer> reelsResults = new ArrayList<>();
        reelsResults.add(1);
        reelsResults.add(0);
        reelsResults.add(1);
        reelsResults.add(2);
        reelsResults.add(0);


        IReelListener reelListener = new IReelListener() {
            @Override
            public void spinFinished(Reel r) {

            }

            @Override
            public void reelUpdate(Reel r) {

            }
        };

        List<String> symbols1 = new ArrayList<>();
        symbols1.add("HA");
        symbols1.add("D2"); //Este

        List<String> symbols2 = new ArrayList<>();
        symbols2.add("C8"); //Este

        List<String> symbols3 = new ArrayList<>();
        symbols3.add("SQ");
        symbols3.add("H2"); //Este

        List<String> symbols4 = new ArrayList<>();
        symbols4.add("HJ");
        symbols4.add("CA");
        symbols4.add("D9"); //Este

        List<String> symbols5 = new ArrayList<>();
        symbols5.add("CK"); //Este

        List<Reel> reels = new ArrayList<>();
        reels.add(new Reel(symbols1, reelListener));
        reels.add(new Reel(symbols2, reelListener));
        reels.add(new Reel(symbols3, reelListener));
        reels.add(new Reel(symbols4, reelListener));
        reels.add(new Reel(symbols5, reelListener));

        int bet = 10;

        playResult.setComponents(reelsResults, reels, bet);

        int result = playResult.getResult();

        int expResult = 20;

        assertEquals(expResult, result);
    }

    @Test
    void testGetResultPairError() {
        PokerPlayResult playResult = new PokerPlayResult();

        List<Integer> reelsResults = new ArrayList<>();
        reelsResults.add(1);
        reelsResults.add(0);
        reelsResults.add(1);
        reelsResults.add(2);
        reelsResults.add(0);


        IReelListener reelListener = new IReelListener() {
            @Override
            public void spinFinished(Reel r) {

            }

            @Override
            public void reelUpdate(Reel r) {

            }
        };

        List<String> symbols1 = new ArrayList<>();
        symbols1.add("HA");
        symbols1.add("D2"); //Este

        List<String> symbols2 = new ArrayList<>();
        symbols2.add("C8"); //Este

        List<String> symbols3 = new ArrayList<>();
        symbols3.add("SQ");
        symbols3.add("H2"); //Este

        List<String> symbols4 = new ArrayList<>();
        symbols4.add("HJ");
        symbols4.add("CA");
        symbols4.add("D9"); //Este

        List<String> symbols5 = new ArrayList<>();
        symbols5.add("CK"); //Este

        List<Reel> reels = new ArrayList<>();
        reels.add(new Reel(symbols1, reelListener));
        reels.add(new Reel(symbols2, reelListener));
        reels.add(new Reel(symbols3, reelListener));
        reels.add(new Reel(symbols4, reelListener));
        reels.add(new Reel(symbols5, reelListener));

        int bet = 10;

        playResult.setComponents(reelsResults, reels, bet);

        int result = playResult.getResult();

        int expResult = 40;

        assertNotEquals(expResult, result);
    }

    @Test
    void testGetResultTwoPair() {
        PokerPlayResult playResult = new PokerPlayResult();

        List<Integer> reelsResults = new ArrayList<>();
        reelsResults.add(1);
        reelsResults.add(0);
        reelsResults.add(1);
        reelsResults.add(2);
        reelsResults.add(0);


        IReelListener reelListener = new IReelListener() {
            @Override
            public void spinFinished(Reel r) {

            }

            @Override
            public void reelUpdate(Reel r) {

            }
        };

        List<String> symbols1 = new ArrayList<>();
        symbols1.add("HA");
        symbols1.add("D2"); //Este

        List<String> symbols2 = new ArrayList<>();
        symbols2.add("C8"); //Este

        List<String> symbols3 = new ArrayList<>();
        symbols3.add("SQ");
        symbols3.add("H2"); //Este

        List<String> symbols4 = new ArrayList<>();
        symbols4.add("HJ");
        symbols4.add("CA");
        symbols4.add("D8"); //Este

        List<String> symbols5 = new ArrayList<>();
        symbols5.add("CK"); //Este

        List<Reel> reels = new ArrayList<>();
        reels.add(new Reel(symbols1, reelListener));
        reels.add(new Reel(symbols2, reelListener));
        reels.add(new Reel(symbols3, reelListener));
        reels.add(new Reel(symbols4, reelListener));
        reels.add(new Reel(symbols5, reelListener));

        int bet = 10;

        playResult.setComponents(reelsResults, reels, bet);

        int result = playResult.getResult();

        int expResult = 30;

        assertEquals(expResult, result);
    }


    @Test
    void testGetResultTwoPairError() {
        PokerPlayResult playResult = new PokerPlayResult();

        List<Integer> reelsResults = new ArrayList<>();
        reelsResults.add(1);
        reelsResults.add(0);
        reelsResults.add(1);
        reelsResults.add(2);
        reelsResults.add(0);


        IReelListener reelListener = new IReelListener() {
            @Override
            public void spinFinished(Reel r) {

            }

            @Override
            public void reelUpdate(Reel r) {

            }
        };

        List<String> symbols1 = new ArrayList<>();
        symbols1.add("HA");
        symbols1.add("D2"); //Este

        List<String> symbols2 = new ArrayList<>();
        symbols2.add("C8"); //Este

        List<String> symbols3 = new ArrayList<>();
        symbols3.add("SQ");
        symbols3.add("H2"); //Este

        List<String> symbols4 = new ArrayList<>();
        symbols4.add("HJ");
        symbols4.add("CA");
        symbols4.add("D8"); //Este

        List<String> symbols5 = new ArrayList<>();
        symbols5.add("CK"); //Este

        List<Reel> reels = new ArrayList<>();
        reels.add(new Reel(symbols1, reelListener));
        reels.add(new Reel(symbols2, reelListener));
        reels.add(new Reel(symbols3, reelListener));
        reels.add(new Reel(symbols4, reelListener));
        reels.add(new Reel(symbols5, reelListener));

        int bet = 10;

        playResult.setComponents(reelsResults, reels, bet);

        int result = playResult.getResult();

        int expResult = 40;

        assertNotEquals(expResult, result);
    }

}