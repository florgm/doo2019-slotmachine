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
import slotmachine.ui.view.SlotMachineViewFacade;

import java.util.List;

public class SlotMachine implements IReelManagerListener, IPlayHandler, IResetHandler,ICreditHandler, IGameModeHandler {
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
    private int coinPool;
    private String gameMode;

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

        coinPool = Integer.valueOf(settings.getProperties().getProperty("coinPool"));
        coinManager.loadCoins(coinPool);

        List<Integer> reelSize = reelManager.setReels(reelQuantity, symbols);

        gameContext.setReelSizes(reelSize);

        gameMode = settings.getProperties().getProperty("gameMode");

        gameContext.setMode(gameMode);
    }

    public void setIDisplayHandler(IDisplayHandler iDisplayHandler) {
        this.iDisplayHandler = iDisplayHandler;
    }

    public void setIPrizeHandler (IPrizeHandler iPrizeHandler) {
        this.iPrizeHandler = iPrizeHandler;
    }

    public void setIReelsHandler (IReelsHandler iReelsHandler) {
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
            SlotMachineViewFacade.setCreditInputEnabled(false);
            SlotMachineViewFacade.setInputEnabled(false);
            iPrizeHandler.reset();

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
        SlotMachineViewFacade.setInputEnabled(true);
        SlotMachineViewFacade.setCreditInputEnabled(true);
    }

    public void reset() {
        iPrizeHandler.reset();
        coinManager.loadCoins(coinPool);
        gameContext.setMode(gameMode);
        showMessage("Insert Coins");
    }

    public String change() {
        return gameContext.changeMode();
    }
}
