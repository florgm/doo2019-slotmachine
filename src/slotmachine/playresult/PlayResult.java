package slotmachine.playresult;

import slotmachine.reelrelated.Reel;

import java.util.ArrayList;
import java.util.List;

public class PlayResult {
    //Esta clase va a tener un metodo que devuelve si el jugador gano o perdio y cuanto es lo que gano
    private boolean win;
    private List<Integer> reelsResults;
    private List<Reel> reels;
    private static String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

    public void setReelsResults(List<Integer> reelsResults) {
        this.reelsResults = reelsResults;
    }

    public void setReels(List<Reel> reels) {
        this.reels = reels;
    }

    public void readReels() {
        for(int i = 0; i < reelsResults.size(); i++) {
            System.out.println("Resultados: " + reelsResults.get(i));
        }

        for(int i = 0; i < reels.size(); i++) {
            System.out.println("Reel"+ i + ": " + reels.get(i).getSymbols());
        }

        //System.out.println(reels.get(0).getSymbols().get(reelsResults.get(0)));
        //System.out.println(reels.get(0).getSymbols().get(reelsResults.get(0)).charAt(0));
    }

    public void getResult() {
        List<String> results = new ArrayList<>();

        for(int i = 0; i < reelsResults.size(); i++) {
            results.add(reels.get(i).getSymbols().get(reelsResults.get(i)));
        }

        int[] ranks = new int[13];

        for(int x = 0; x < reelsResults.size(); x++) {
            int i;
            String number = results.get(x).substring(1);
            System.out.println(number);
            for(i = 0; i < 13; i++) {
                if(values[i].equals(number)) {
                    ranks[i]++;
                    break;
                }
            }
        }

        for(int x = 0; x < ranks.length; x++) {
            System.out.println(ranks[x]);
        }
    }
}
