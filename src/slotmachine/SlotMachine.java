package slotmachine;

import slotmachine.coinrelated.CoinSlot;
import slotmachine.coinrelated.DropBox;
import slotmachine.coinrelated.PayoutTray;
import slotmachine.gamemode.GameContext;
import slotmachine.gamemode.GameMode;
import slotmachine.gamemode.GameModeFactory;
import slotmachine.gamemode.Mode;
import slotmachine.gamemode.random.RandomFactory;
import slotmachine.gamemode.randomize.IRandomize;
import slotmachine.gamemode.randomize.Randomize;
import slotmachine.gamemode.sequence.SequenceFactory;
import slotmachine.playresult.PlayResult;
import slotmachine.reelrelated.IReelManagerListener;
import slotmachine.reelrelated.ReelManager;
import slotmachine.settings.Settings;

import java.util.List;

public class SlotMachine implements IReelManagerListener {
    private static SlotMachine instance;
    private Settings settings;
    private CoinSlot coinSlot;
    private DropBox dropBox;
    private PayoutTray payoutTray;
    private ReelManager reelManager;
    private IRandomize randomize;
    private GameContext gameContext;
    private PlayResult playResult;

    public List<Integer> reelSize;

    private SlotMachine() { }

    public static SlotMachine getInstance() {
        if (instance == null) {
            instance = new SlotMachine();
        }

        return instance;
    }

    public void initComponents() {
        settings = Settings.getInstance();

        gameContext = new GameContext();

        coinSlot = new CoinSlot();
        dropBox = new DropBox();
        reelManager = new ReelManager(this);
        randomize = new Randomize();
        playResult = new PlayResult();


        //Aca faltaria iniciar recordManager

    }

    public void loadConfiguration() {
        int coinPool = Integer.valueOf(settings.getProperties().getProperty("coinPool"));
        dropBox.setCoinPool(coinPool);

        int reelQuantity = Integer.valueOf(settings.getProperties().getProperty("reelQuantity"));
        reelSize = reelManager.setReels(reelQuantity);

        String gameMode = settings.getProperties().getProperty("gameMode");
        int sequenceQuantity = Integer.valueOf(settings.getProperties().getProperty("sequenceQ"));

        //TODO preguntar si esto esta bien
        if (gameMode.equals("random")) {
            Mode random = GameModeFactory.getGameMode(new RandomFactory(reelSize,randomize));
            gameContext.setMode(random);
        }
        else {
            Mode sequence = GameModeFactory.getGameMode(new SequenceFactory(reelSize,sequenceQuantity,randomize));
            gameContext.setMode(sequence);
        }

    }

    public void onReelsFinished() {
        //TODO este metodo se llama cuando todos los reels terminan de girar
        System.out.println("Todos los reels terminaron");
    }

    public void getResult() {
        System.out.println(gameContext.getNextValues());
    }

    public void reels() {
        for(int i = 0; i < reelSize.size(); i++) {
            System.out.println("Reel " + i + ": " + reelSize.get(i));
        }

    }

    public void prueba() {
        playResult.setReelsResults(gameContext.getNextValues());
        playResult.setReels(reelManager.getReels());
        playResult.readReels();
    }

    public void play() {
//        int coins = coinSlot.getCoins();
//
//        if(coins > 0) {
//            dropBox.setBet(coins);
//
//
//
//        } else {
//          System.out.println("No hay monedas ingresadas para jugar");
//        }
        reelManager.spinReels();
    }

}
