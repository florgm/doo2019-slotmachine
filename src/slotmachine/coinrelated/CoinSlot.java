package slotmachine.coinrelated;

import java.util.ArrayList;
import java.util.List;

public class CoinSlot {
    private List<Coin> coins;

    public CoinSlot( ) {
        resetCoinSlot();
    }

    //Esto va a ser el doAction de algun boton de la interfaz
    public void insertCoin() {
        coins.add(new Coin());
    }

    //Este metodo lo pide slot machine cuando aprietan play
    public List<Coin> getCoins() {
        return coins;
    }

    //Este metodo se llama desde slotmachine
    public void resetCoinSlot() {
        coins = new ArrayList<>();
    }

}
