package slotmachine.reelrelated;

import slotmachine.settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class ReelManager implements IReel {
    private List<IReel> reels = new ArrayList<>();
    private Settings settings;

    public ReelManager( ) {
        settings = Settings.getInstance();
        /*index = 0;
        for(int i = 0; i < reelQuantity; i++) {
            reels.add(new Reel());
            index++;
        }*/
    }

    public void setReels(int reelQuantity) {
        String[] reelSymbols = settings.getProperties().getProperty("symbols").split(",");

        //Aca va un metodo que asigna a cada reel su lista de simbolos segun la cantidad de reels que sean
        //Y tiene que haber una funcion que me devuelva cuantos simbolos tiene cada reel
        int remainder = 52 % reelQuantity;
        int symbolsQuantity = (52 - remainder) / reelQuantity;



    }


    @Override
    public void spinReel(Object play) {
        List<Integer> results = (List<Integer>) play;

        for(int i = 0; i < reels.size() && i < results.size(); i++) {
            reels.get(i).spinReel(results.get(i));
        }
    }

    @Override
    public void returnPlay() {

    }
}
