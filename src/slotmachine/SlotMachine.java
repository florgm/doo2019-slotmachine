package slotmachine;

import slotmachine.coinrelated.CoinException;
import slotmachine.coinrelated.CoinManager;
import slotmachine.gamemode.GameContext;
import slotmachine.playresult.IPlayResultGenerator;
import slotmachine.recordrelated.RecordManager;
import slotmachine.reelrelated.IReelManager;
import slotmachine.reelrelated.IReelManagerListener;
import slotmachine.reelrelated.Reel;
import slotmachine.settinglistener.ISettingListener;
import slotmachine.ui.handler.*;
import slotmachine.ui.view.SlotMachineViewFacade;

import java.util.List;

public class SlotMachine implements IReelManagerListener {
    private static SlotMachine instance;

    private CoinManager coinManager;
    private IReelManager reelManager;
    private GameContext gameContext;
    private IPlayResultGenerator playResult;
    private ISettingListener settingListener;

    private IDisplayHandler iDisplayHandler;
    private IPrizeHandler iPrizeHandler;
    private IReelHandler reelHandler;

    private int coinPool;
    private int prize;
    private String gameMode;

    private SlotMachine() { }

    public static SlotMachine getInstance() {
        if (instance == null) {
            instance = new SlotMachine();
        }

        return instance;
    }

    public void initComponents(IPlayResultGenerator playResult, IReelManager reelManager, ISettingListener settingListener, int coinPool, String gameMode) {
        this.settingListener = settingListener;
        this.reelManager = reelManager;
        this.playResult = playResult;
        this.coinPool = coinPool;
        this.gameMode = gameMode;

        gameContext = new GameContext();
        coinManager = new CoinManager();

    }

    public void loadConfiguration(List<Reel> reels) {
        reelManager.setListener(this);

        coinManager.loadCoins(coinPool);

        reelManager.setReels(reels);
        List<Integer> reelSize = reelManager.getReelsSizes();

        gameContext.setReelSizes(reelSize);
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

    public void addCoins(int coin) {
        coinManager.insertCredit(coin);
        showMessage("Coins " + coinManager.getBet());

    }

    public void play() {
        if(coinManager.playAllowed()) {
            SlotMachineViewFacade.setInputEnabled(false);
            SlotMachineViewFacade.setCreditInputEnabled(false);

            iPrizeHandler.reset();

            List<Integer> result = gameContext.getNextValues();

            showMessage("Spinning");

            coinManager.addCoinsInDropBox();

            playResult.setReelsResults(result);

            coinManager.setPrize(playResult.getResult(coinManager.getBet()));

            reelManager.spinReels(1, result);

            RecordManager.getInstance().setRecord(coinManager.getBet(), playResult.getSymbolResult(), coinManager.getPrize());
        } else {
            showMessage("Insufficient coins to play");
        }
    }

    public void onReelsFinished() {
        prize = coinManager.getPrize();
        //System.out.println("Prize en reels finished " + prize);

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

        //int newCoinPool = coinManager.updateCoinPool(prize);
        settingListener.updateCoinPool();
        //settings.setCoinPool(newCoinPool);
        //settings.saveSettings();
        coinManager.resetBet();
        coinManager.resetPrize();
        SlotMachineViewFacade.setInputEnabled(true);
        SlotMachineViewFacade.setCreditInputEnabled(true);
    }

    public void notifyReelHandler() {
        reelHandler.update();
    }

    public int getNewCoinPool() {
        int newCoinPool = coinManager.updateCoinPool(prize);

        return newCoinPool;
    }

    public void setGameMode(String gameMode) {
        gameContext.setMode(gameMode);
        settingListener.updateGameMode();
        //settings.setGameMode(gameMode);
        //settings.saveSettings();
    }

    public String getGameMode() {
        return gameContext.getGameModeKey();
    }

    public void reset() {
        iPrizeHandler.reset();
        coinManager.loadCoins(coinPool);
        gameContext.setMode("RANDOM");
        settingListener.updateGameMode();
        //settings.setGameMode("RANDOM");
        //settings.saveSettings();
        showMessage("Insert Coins");
    }

    public void printRecords() {
        RecordManager.getInstance().getRecords();
    }
}
