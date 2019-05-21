package slotmachine.coinrelated;

import java.util.Collection;
import java.util.List;

public class DropBox {
    private List<Coin> coinPool;
    private Integer bet;
    private Integer multiplier;

    public DropBox(List<Coin> coinPool) {
        this.coinPool = coinPool;
        this.bet = 0;
        this.multiplier = 5;
    }

    //A esta funcion la llama slotmachine y le pasa las monedas ingresadas en coinslot
    public void setBet(List<Coin> bet) {
        this.bet = bet.size();
        coinPool.addAll(bet);
    }

    public List<Coin> withdrawCoins() {
        return coinPool.subList(0,bet*multiplier-1);
    }

}
