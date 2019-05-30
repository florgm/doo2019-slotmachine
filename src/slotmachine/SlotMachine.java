package slotmachine;

import slotmachine.coinrelated.CoinSlot;
import slotmachine.coinrelated.DropBox;
import slotmachine.coinrelated.PayoutTray;
import slotmachine.gamemode.GameMode;
import slotmachine.gamemode.GameModeFactory;
import slotmachine.gamemode.random.RandomFactory;
import slotmachine.gamemode.randomize.IRandomize;
import slotmachine.gamemode.randomize.Randomize;
import slotmachine.gamemode.sequence.SequenceFactory;
import slotmachine.reelrelated.ReelManager;
import slotmachine.settings.Settings;

import java.util.List;

public class SlotMachine {
    private static SlotMachine instance;
    private Settings settings;
    private CoinSlot coinSlot;
    private DropBox dropBox;
    private PayoutTray payoutTray;
    private ReelManager reelManager;
    private GameMode gamePlayGenerator;
    private IRandomize randomize;

    private SlotMachine() { }

    public static SlotMachine getInstance() {
        if (instance == null) {
            instance = new SlotMachine();
        }

        return instance;
    }

    public void initComponents() {
        settings = Settings.getInstance();

        coinSlot = new CoinSlot();
        dropBox = new DropBox();
        reelManager = new ReelManager();
        randomize = new Randomize();


        //Aca faltaria iniciar recordManager

    }

    public void loadConfiguration() {
        int coinPool = Integer.valueOf(settings.getProperties().getProperty("coinPool"));
        dropBox.setCoinPool(coinPool);

        int reelQuantity = Integer.valueOf(settings.getProperties().getProperty("reelQuantity"));
        reelManager.setReels(reelQuantity);

        //Primero cargo los reels, paso esa cantidad una funcion y esa funcion va a un list de enteros que se los paso a gamemode
        List<Integer> reelSize;

        String gameMode = settings.getProperties().getProperty("gameMode");

        if (gameMode.equals("Random")) {
            //GameMode random = GameModeFactory.getGameMode(new RandomFactory(reelSize, randomize));
        }
        else {
            //GameMode sequence = GameModeFactory.getGameMode(new SequenceFactory(reelSize,10, randomize));
        }
    }

}
