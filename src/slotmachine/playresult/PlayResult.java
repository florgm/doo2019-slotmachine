package slotmachine.playresult;

import slotmachine.reelrelated.Reel;

import java.util.List;

public class PlayResult {
    //Esta clase va a tener un metodo que devuelve si el jugador gano o perdio y cuanto es lo que gano
    private boolean win;
    private List<Integer> reelsResults;
    private List<Reel> reels;

    public void setReelsResults(List<Integer> reelsResults) {
        this.reelsResults = reelsResults;
    }

    public void setReels(List<Reel> reels) {
        this.reels = reels;
    }


    //TODO ver como funciona esto con el composite, no puedo tener una funcion en reel queno este en Ireel
    public void readReels() {
        for(int i = 0; i < reels.size(); i++) {
            System.out.println("Resultados: " + reels.get(i).getSymbols());
        }
    }
}
