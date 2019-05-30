package slotmachine.coinrelated;

public class CoinSlot {
    private int coins;

    public CoinSlot( ) {
        resetCoinSlot();
    }

    //Esto va a ser el doAction de algun boton de la interfaz
    public void insertCoin() {
        coins++;
    }

    //Este metodo lo pide slot machine cuando aprietan play
    public int getCoins() {
        return coins;
    }


    //Este metodo se llama desde slotmachine
    public void resetCoinSlot() {
        coins = 0;
    }

}
