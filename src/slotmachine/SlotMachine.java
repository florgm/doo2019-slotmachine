package slotmachine;

import slotmachine.coinrelated.CoinSlot;
import slotmachine.coinrelated.DropBox;
import slotmachine.coinrelated.PayoutTray;
import slotmachine.gamemode.GameContext;
import slotmachine.gamemode.GameModeFactory;
import slotmachine.gamemode.Mode;
import slotmachine.gamemode.random.RandomFactory;
import slotmachine.gamemode.randomize.IRandomize;
import slotmachine.gamemode.randomize.Randomize;
import slotmachine.gamemode.sequence.SequenceFactory;
import slotmachine.playresult.IPlayResult;
import slotmachine.recordrelated.RecordManager;
import slotmachine.reelrelated.IReelManager;
import slotmachine.reelrelated.IReelManagerListener;
import slotmachine.settings.Settings;

import java.util.List;

public class SlotMachine implements IReelManagerListener {
    private static SlotMachine instance;
    private Settings settings;
    private CoinSlot coinSlot;
    private DropBox dropBox;
    private PayoutTray payoutTray;
    private IReelManager reelManager;
    private IRandomize randomize;
    private GameContext gameContext;
    private IPlayResult playResult;
    private RecordManager recordManager;

    public List<Integer> reelSize;
    public List<Integer> results;
    public int reelQuantity;
    public String symbols;

    private SlotMachine() { }

    public static SlotMachine getInstance() {
        if (instance == null) {
            instance = new SlotMachine();
        }

        return instance;
    }

    public void initComponents(IPlayResult playResult, IReelManager reelManager, int reelQuantity, String symbols) {
        settings = Settings.getInstance();

        gameContext = new GameContext();
        coinSlot = new CoinSlot();
        dropBox = new DropBox();
        randomize = new Randomize();
        this.reelManager = reelManager;
        this.playResult = playResult;
        this.reelQuantity = reelQuantity;
        this.symbols = symbols;
        recordManager = new RecordManager();
    }

    public void loadConfiguration() {
        reelManager.setListener(this);

        int coinPool = Integer.valueOf(settings.getProperties().getProperty("coinPool"));
        dropBox.setCoinPool(coinPool);

        reelSize = reelManager.setReels(reelQuantity, symbols);

        String gameMode = settings.getProperties().getProperty("gameMode");
        int sequenceQuantity = Integer.valueOf(settings.getProperties().getProperty("sequenceQ"));

        //TODO ver enum
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
        results = gameContext.getNextValues();
        System.out.println(results);
    }

    public void reels() {
        for(int i = 0; i < reelSize.size(); i++) {
            System.out.println("Reel " + i + ": " + reelSize.get(i));
        }

    }

    public void guardarRecord() {
        recordManager.setRecord(5);
        recordManager.setRecord(6);
        recordManager.getRecords();
    }

    public void prueba() {
        playResult.setReelsResults(results);
        playResult.setReels(reelManager.getReels());
        //playResult.readReels();
        playResult.getResult();
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
