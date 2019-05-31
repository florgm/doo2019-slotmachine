package slotmachine.reelrelated;

import java.util.List;

public class Reel implements IReel {
    private List<String> symbols;

    public Reel(List<String> symbols) {
        this.symbols = symbols;
    }

    @Override
    public void spinReel(Object play) {

    }

    @Override
    public void returnPlay() {

    }
}
