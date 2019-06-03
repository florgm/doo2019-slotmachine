package slotmachine.reelrelated;

import java.util.List;

public class Reel implements IReel {
    private List<String> symbols;

    public Reel(List<String> symbols) {
        this.symbols = symbols;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    @Override
    public void spinReel(Object play) {
        //TODO ver metodo de spin
    }

    @Override
    public void spinFinished() {

    }
}
