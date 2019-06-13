package slotmachine;

import slotmachine.coinrelated.CoinException;
import slotmachine.coinrelated.CoinManager;
import slotmachine.gamemode.GameContext;
import slotmachine.playresult.IPlayResultGenerator;
import slotmachine.recordrelated.RecordManager;
import slotmachine.reelrelated.IReelManager;
import slotmachine.reelrelated.IReelManagerListener;
import slotmachine.reelrelated.Reel;
import slotmachine.settings.Settings;
import slotmachine.ui.data.ICredit;
import slotmachine.ui.handler.*;
import slotmachine.ui.view.SlotMachineViewFacade;

import java.util.List;

public class SlotMachine implements IReelManagerListener, IPlayHandler, IResetHandler,ICreditHandler, IGameModeHandler, IRecordsHandler {
    private static SlotMachine instance;
    private Settings settings;

    private CoinManager coinManager;
    private IReelManager reelManager;
    private GameContext gameContext;
    private IPlayResultGenerator playResult;

    private IDisplayHandler iDisplayHandler;
    private IPrizeHandler iPrizeHandler;
    private IReelHandler reelHandler;

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

    public void initComponents(IPlayResultGenerator playResult, IReelManager reelManager) {
        settings = Settings.getInstance();

        this.reelManager = reelManager;
        this.playResult = playResult;

        gameContext = new GameContext();
        coinManager = new CoinManager();

    }

    public void loadConfiguration(List<Reel> reels) {
        reelManager.setListener(this);

        coinPool = Integer.valueOf(settings.getProperties().getProperty("coinPool"));
        coinManager.loadCoins(coinPool);

        reelManager.setReels(reels);
        List<Integer> reelSize = reelManager.getReelsSizes();

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

    public void setReelHandler(IReelHandler reelHandler) { this.reelHandler = reelHandler; }

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

            playResult.setReelsResults(result);

            coinManager.setPrize(playResult.getResult(coinManager.getBet()));

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

        int newCoinPool = coinManager.updateCoinPool(prize);
        settings.setCoinPool(newCoinPool);
        settings.saveSettings();
        coinManager.resetBet();
        coinManager.resetPrize();
        SlotMachineViewFacade.setInputEnabled(true);
        SlotMachineViewFacade.setCreditInputEnabled(true);
    }

    public void notifyReelHandler() {
        reelHandler.update();
    }

    public void reset() {
        iPrizeHandler.reset();
        coinManager.loadCoins(coinPool);
        gameContext.setMode(gameMode);
        showMessage("Insert Coins");
    }

    public String change() {
        String gameMode = gameContext.changeMode();
        settings.setGameMode(gameContext.getGameModeKey());
        settings.saveSettings();
        return gameMode;
    }

    public void printRecords() {
        RecordManager.getInstance().getRecords();
    }
}
