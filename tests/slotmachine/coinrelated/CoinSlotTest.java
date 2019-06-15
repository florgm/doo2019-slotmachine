package slotmachine.coinrelated;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinSlotTest {

    @Test
    void testInsertCoin() {
        CoinSlot coinSlot = new CoinSlot();

        coinSlot.insertCoin(10);
        coinSlot.insertCoin(5);

        assertEquals(15, coinSlot.getCoins());
    }

    @Test
    void testGetCoins() {
        CoinSlot coinSlot = new CoinSlot();

        coinSlot.insertCoin(20);
        coinSlot.insertCoin(25);

        assertEquals(45, coinSlot.getCoins());
    }

    @Test
    void testResetCoinSlot() {
        CoinSlot coinSlot = new CoinSlot();

        coinSlot.insertCoin(10);
        coinSlot.resetCoinSlot();

        assertEquals(0, coinSlot.getCoins());
    }
}