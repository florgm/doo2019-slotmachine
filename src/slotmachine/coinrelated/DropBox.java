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
        //System.out.println("CoinPool despues del add " + coinPool);
    }

    public int withdrawCoins(int prize) {
        //System.out.println("Prize de withdraw " + prize);
        this.coinPool -= prize;
        return prize;
    }

}
