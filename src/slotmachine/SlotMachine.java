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
    private IRandomize randomize;
    private GameMode random;
    private GameMode sequence;

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
        List<Integer> reelSize = reelManager.setReels(reelQuantity);

        String gameMode = settings.getProperties().getProperty("gameMode");
        int sequenceQuantity = Integer.valueOf(settings.getProperties().getProperty("sequenceQ"));

        System.out.println(gameMode);

        if (gameMode.equals("random")) {
            random = GameModeFactory.getGameMode(new RandomFactory(reelSize, randomize));
        }
        else {
            sequence = GameModeFactory.getGameMode(new SequenceFactory(reelSize,sequenceQuantity, randomize));
        }

    }

    public void getResult() {
        System.out.println(random.getNextValues());
    }

}
