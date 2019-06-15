package slotmachine.coinrelated;

public class CoinSlot {
    private int coins;

    public CoinSlot( ) {
        resetCoinSlot();
    }

    public void insertCoin(int coin) { coins += coin; }

    public int getCoins() {
        return coins;
    }

    public void resetCoinSlot() {
        coins = 0;
    }

}
