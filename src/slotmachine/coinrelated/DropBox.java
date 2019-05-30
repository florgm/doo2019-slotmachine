package slotmachine.coinrelated;

public class DropBox {
    private int coinPool;
    private int bet;
    private int multiplier;

    public void setCoinPool(int coinPool) {
        this.coinPool = coinPool;
    }

    //A esta funcion la llama slotmachine y le pasa las monedas ingresadas en coinslot
    public void setBet(int bet) {
        this.bet = bet;
        coinPool += bet;
    }

    public void withdrawCoins(int prize) {
        coinPool = coinPool - prize;
    }

}
