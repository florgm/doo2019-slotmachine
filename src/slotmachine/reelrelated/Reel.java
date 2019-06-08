package slotmachine.reelrelated;

import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Reel {
    private List<String> symbols;
    private IReelListener reelListener;
    int currentValue;

    public Reel(List<String> symbols, IReelListener rl) {
        this.symbols = symbols;
        this.reelListener = rl;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public void spinReel(int spins, int result) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(() -> {
            try {
                boolean lastSpin = false;

                for(int i = 0; i <= spins; i++) {
                    if (i == spins) {
                        lastSpin = true;
                    }

                    for (int k = currentValue; k < symbols.size(); k++) {
                        currentValue = ++currentValue % symbols.size();
                        //System.out.println(String.valueOf(this.hashCode()) + symbols.get(currentValue));
                        Thread.sleep(200);
                        reelListener.reelUpdate(this);

                        if (lastSpin && currentValue == result)
                            break;
                    }
                }
                reelListener.spinFinished(this);
            } catch(InterruptedException exc) {
                exc.printStackTrace();
            }
        });
    }

    public int getCurrentValue() {
        return currentValue;
    }
}
