package slotmachine;

import slotmachine.coinrelated.CoinException;
import slotmachine.coinrelated.CoinManager;
import slotmachine.gamemode.GameContext;
import slotmachine.playresult.IPlayResult;
import slotmachine.recordrelated.RecordManager;
import slotmachine.reelrelated.IReelManager;
import slotmachine.reelrelated.IReelManagerListener;
import slotmachine.settings.Settings;
import slotmachine.ui.data.ICredit;
import slotmachine.ui.handler.*;

import java.util.List;

public class SlotMachine implements IReelManagerListener, IPlayHandler, ICreditHandler, IGameModeHandler {
    private static SlotMachine instance;
    private Settings settings;

    private CoinManager coinManager;
    private IReelManager reelManager;
    private GameContext gameContext;
    private IPlayResult playResult;

    private IDisplayHandler iDisplayHandler;
    private IPrizeHandler iPrizeHandler;
    private IReelsHandler iReelsHandler;

    private int reelQuantity;
    private String symbols;

    private SlotMachine() { }

    public static SlotMachine getInstance() {
        if (instance == null) {
            instance = new SlotMachine();
        }

        return instance;
    }

    public void initComponents(IPlayResult playResult, IReelManager reelManager, int reelQuantity, String symbols) {
        settings = Settings.getInstance();

        this.reelManager = reelManager;
        this.playResult = playResult;
        this.reelQuantity = reelQuantity;
        this.symbols = symbols;

        gameContext = new GameContext();
        coinManager = new CoinManager();
    }

    public void loadConfiguration() {
        reelManager.setListener(this);

        int coinPool = Integer.valueOf(settings.getProperties().getProperty("coinPool"));
        coinManager.loadCoins(coinPool);

        List<Integer> reelSize = reelManager.setReels(reelQuantity, symbols);

        gameContext.setReelSizes(reelSize);

        String gameMode = settings.getProperties().getProperty("gameMode");

        if (gameMode.equals("random")) {
            gameContext.setMode("random");
        }
        else {
            gameContext.setMode("sequence");
        }

    }

    public void setiDisplayHandler(IDisplayHandler iDisplayHandler) {
        this.iDisplayHandler = iDisplayHandler;
    }

    public void setiPrizeHandler (IPrizeHandler iPrizeHandler) {
        this.iPrizeHandler = iPrizeHandler;
    }

    public void setiReelsHandler (IReelsHandler iReelsHandler) {
        this.iReelsHandler = iReelsHandler;
        reelManager.setReelHandlers(iReelsHandler.getReelsHandler());
    }

    public void showMessage(String msg) {
        if(iDisplayHandler != null) {
            iDisplayHandler.setText(msg);
        }
    }

    public void addCredit(ICredit coin) {
        coinManager.insertCredit(coin.getValue());
        showMessage("Coins: " + coinManager.getBet());
    }


    public void play() {
        if(coinManager.playAllowed()) {
            List<Integer> result = gameContext.getNextValues();

            showMessage("Spinning");

            coinManager.addCoinsInDropBox();

            playResult.setComponents(result, reelManager.getReels(), coinManager.getBet());

            coinManager.setPrize(playResult.getResult());

            reelManager.spinReels(2, result);

            RecordManager.getInstance().setRecord(coinManager.getBet(), playResult.getSymbolResult(), coinManager.getPrize());
        } else {
            showMessage("Insufficient coins to play");
        }
    }

    public void onReelsFinished() {
        int prize = coinManager.getPrize();

        try {
            iPrizeHandler.retrieve(coinManager.deliverPrize(prize));
        } catch (CoinException exc) {
            exc.printStackTrace();
            showMessage("Error...");
            return;
        }

        if(prize > 0) {
            showMessage("You won " + prize + " coins");
        } else {
            showMessage("Thank you for playing");
        }

        coinManager.resetBet();
        coinManager.resetPrize();
    }

    public void reset() {
        loadConfiguration();
    }

    @Override
    public String change() {
        return gameContext.changeMode();
    }
}
