package slotmachine.reelrelated;

import slotmachine.settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class ReelManager implements IReel {
    private List<IReel> reels = new ArrayList<>();
    private Settings settings;

    public ReelManager( ) {
        settings = Settings.getInstance();
    }

    public List<Integer> setReels(int reelQuantity) {
        String[] reelSymbols = settings.getProperties().getProperty("symbols").split(",");
        List<Integer> reelSize = new ArrayList<>();

        int remainder = 52 % reelQuantity;
        int symbolsQuantity = (52 - remainder) / reelQuantity;

        for(int j = 0; j < reelQuantity; j++) {
            List<String> symbols = new ArrayList<>();
            int start = symbolsQuantity*j;
            int end;

            if(j == reelQuantity-1) {
                end = 52;
            }
            else {
                end = start + symbolsQuantity;
            }

            for(int k = start; k < end; k++) {
                symbols.add(reelSymbols[k]);
            }

            reelSize.add(end-start);
            reels.add(new Reel(symbols));
        }

        return reelSize;
    }

    public List<IReel> getReels() {
        return reels;
    }


    //TODO ver la excepcion porque la funcion generica no puede hacer throws
    @Override
    public void spinReel(Object play){
        try {
            List<Integer> results = (List<Integer>) play;

            for(int i = 0; i < reels.size() && i < results.size(); i++) {
                reels.get(i).spinReel(results.get(i));
            }
        } catch (ClassCastException exc) {
            System.out.println("El elemento debe ser una lista de enteros");
        }

    }


    //TODO ver como le comunica ReelManager a SlotMachine que los reels dejaron de girar
    @Override
    public void spinFinished() {

    }
}
