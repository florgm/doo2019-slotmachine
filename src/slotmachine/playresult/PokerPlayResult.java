package slotmachine.playresult;

import slotmachine.reelrelated.Reel;

import java.util.ArrayList;
import java.util.List;

public class PokerPlayResult implements IPlayResult {
    private List<Integer> reelsResults;
    private List<Reel> reels;
    private int bet;
    private static String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

    public void setComponents(List<Integer> reelsResults, List<Reel> reels, int bet) {
        this.reelsResults = reelsResults;
        this.reels = reels;
        this.bet = bet;
    }

    public List<String> getSymbolResult() {
        List<String> symbolsResult = new ArrayList<>();

        for(int i = 0; i < reelsResults.size(); i++) {
            symbolsResult.add(reels.get(i).getSymbols().get(reelsResults.get(i)));
        }

        return symbolsResult;
    }

   @Override
    public int getResult() {
        List<String> results = new ArrayList<>();
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

        if(sameCards == 1) { //Nothing
            bet = 0;
        }

        if(sameCards == 2 && sameCards2 == 1) { //pair
            bet = bet*2;
        }

        if(sameCards == 2 && sameCards2 == 2) { //two pair
            bet = bet*3;
        }

        if(sameCards == 3 && sameCards2 != 2) { //three of a kind
            bet = bet*4;
        }

        if(straight) {
            bet = bet*5;
        }

        if(flush) {
            bet = bet*6;
        }

        if(sameCards == 3 && sameCards2 == 2) { //full house
            bet = bet*7;
        }

        if(sameCards == 4) { //four of a kind
            bet = bet*8;
        }

        if(straight && flush) { //straight flush
            bet = bet*10;
        }

        return bet;
    }
}
