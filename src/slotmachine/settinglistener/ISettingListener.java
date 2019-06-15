package slotmachine.settinglistener;

public interface ISettingListener {
    void updateCoinPool(int coinPool);
    void updateGameMode(String gameMode);
    void resetSettings();
}
