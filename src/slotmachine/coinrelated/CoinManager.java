package slotmachine.coinrelated;



public class CoinManager {
    private DropBox dropBox;
    private CoinSlot coinSlot;

    private int bet;
    private int prize;

    public CoinManager() {
        coinSlot = new CoinSlot();
        dropBox = new DropBox();
    }

    public boolean playAllowed() {
        if(coinSlot.getCoins() > 0) {
            return true;
        }
        return false;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public int getPrize() {
        return prize;
    }

    public int getBet() {
        return bet;
    }

    public void addCoinsInDropBox() {
        bet = coinSlot.getCoins();
        dropBox.addToCoinPool(bet);
        coinSlot.resetCoinSlot();
    }

    public void insertCredit(int coin) {
        coinSlot.insertCoin(coin);
        bet += coin;
    }

    public void loadCoins(int coins) {
        dropBox.setCoinPool(coins);
    }

    public void resetBet() {
        bet = 0;
    }

    public void resetPrize() {
        prize = 0;
    }

    public int deliverPrize(int coins) throws CoinException {
        if(coins > dropBox.getCoinPool()) {
            throw new CoinException("Can't deliver prize, insufficient coins");
        }
        return dropBox.withdrawCoins(coins);
    }
}
