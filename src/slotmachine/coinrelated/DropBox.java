package slotmachine.coinrelated;

public class DropBox {
    private int coinPool;

    public void setCoinPool(int coinPool) {
        this.coinPool = coinPool;
    }

    public int getCoinPool() {
        return coinPool;
    }

    public void addToCoinPool(int bet) {
        this.coinPool += bet;
    }

    public int withdrawCoins(int prize) {
        this.coinPool -= prize;
        return prize;
    }

}
