package slotmachine.playresult;

import slotmachine.reelrelated.Reel;

import java.util.ArrayList;
import java.util.List;

public class PokerPlayResult implements IPlayResult {
    private boolean win;
    private List<Integer> reelsResults;
    private List<Reel> reels;
    private static String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

    @Override
    public void setReelsResults(List<Integer> reelsResults) {
        this.reelsResults = reelsResults;
    }

    @Override
    public void setReels(List<Reel> reels) {
        this.reels = reels;
    }

   /* public void readReels() {
        for(int i = 0; i < reelsResults.size(); i++) {
            System.out.println("Resultados: " + reelsResults.get(i));
        }

        for(int i = 0; i < reels.size(); i++) {
            System.out.println("Reel"+ i + ": " + reels.get(i).getSymbols());
        }
    }*/

   @Override
    public void getResult() {
        List<String> results = new ArrayList<>();
        int finishedResult = 0;
        int sameCards = 1, sameCards2 = 1;
        boolean straight = false;
        boolean flush = true;

        for(int i = 0; i < reelsResults.size(); i++) {
            results.add(reels.get(i).getSymbols().get(reelsResults.get(i)));
        }

        int[] ranks = new int[13];
        List<Character> symbols = new ArrayList<>();

        for(int x = 0; x < reelsResults.size(); x++) {
            int i;
            symbols.add(results.get(x).charAt(0));
            String number = results.get(x).substring(1);

            for(i = 0; i < 13; i++) {
                if(values[i].equals(number)) {
                    ranks[i]++;
                    break;
                }
            }
        }

        for(int x = 12; x >= 0; x--) {
            if(ranks[x] > sameCards) {
                if(sameCards != 1) {
                    sameCards2 = sameCards;
                }

                sameCards = ranks[x];
            } else if (ranks[x] > sameCards2){
                sameCards2 = ranks[x];
            }
        }

        for(int x = 0; x < symbols.size()-1; x++) {
            if(!symbols.get(x).equals(symbols.get(x+1))) {
                flush = false;
            }
        }

        for (int x = 0; x < 9; x++)
        {
            if (ranks[x]==1 && ranks[x+1]==1 && ranks[x+2]==1 && ranks[x+3]==1 && ranks[x+4]==1) {
                straight = true;
                break;
            }
        }

        if (ranks[10]==1 && ranks[11]==1 && ranks[12]==1 && ranks[13]==1 && ranks[0]==1) {
            straight = true;
        }

        if(sameCards == 1) { //No gano nada
            finishedResult = 1;
        }

        if(sameCards == 2 && sameCards2 == 1) { //pair
            finishedResult = 2;
        }

        if(sameCards == 2 && sameCards2 == 2) { //two pair
            finishedResult = 3;
        }

        if(sameCards == 3 && sameCards2 != 2) { //three of a kind
            finishedResult = 4;
        }

        if(straight) {
            finishedResult = 5;
        }

        if(flush) {
            finishedResult = 6;
        }

        if(sameCards == 3 && sameCards2 == 2) { //full house
            finishedResult = 7;
        }

        if(sameCards == 4) { //four of a kind
            finishedResult = 8;
        }

        if(straight && flush) { //straight flush
            finishedResult = 9;
        }


        for(int x = 0; x < ranks.length; x++) {
            System.out.println(ranks[x]);

        }
        System.out.println(finishedResult);
    }
}
