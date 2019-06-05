package slotmachine.reelrelated;

import java.util.List;

public class Reel {
    private List<String> symbols;
    private IReelListener reelListener;

    public Reel(List<String> symbols, IReelListener rl) {
        this.symbols = symbols;
        this.reelListener = rl;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public void spinReel() {
        //TODO antes tiene que girar y despues llamar al metodo
        reelListener.spinFinished(this);
    }
}
