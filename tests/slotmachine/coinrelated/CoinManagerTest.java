package slotmachine.coinrelated;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinManagerTest {

    @Test
    void testLoadCoins() {
        CoinManager coinManager = new CoinManager();

        coinManager.loadCoins(20);

        assertEquals(20, coinManager.getCoinPool());
    }

    @Test
    void testLoadCoinsError() {
        CoinManager coinManager = new CoinManager();

        coinManager.loadCoins(20);

        assertNotEquals(30, coinManager.getCoinPool());
    }

    @Test
    void testInsertCredit() {
        CoinManager coinManager = new CoinManager();

        coinManager.insertCredit(15);

        assertEquals(15, coinManager.getBet());
    }

    @Test
    void testInsertCreditError() {
        CoinManager coinManager = new CoinManager();

        coinManager.insertCredit(15);

        assertNotEquals(25, coinManager.getBet());
    }

    @Test
    void testPlayAllowed() {
        CoinManager coinManager = new CoinManager();

        coinManager.insertCredit(10);

        assertTrue(coinManager.playAllowed());
    }

    @Test
    void testPlayAllowedError() {
        CoinManager coinManager = new CoinManager();

        assertFalse(coinManager.playAllowed());
    }

    @Test
    void testAddCoinsInDropBox() {
        CoinManager coinManager = new CoinManager();

        coinManager.insertCredit(10);
        coinManager.addCoinsInDropBox();

        assertEquals(10, coinManager.getBet());
    }

    @Test
    void testAddCoinsInDropBoxError() {
        CoinManager coinManager = new CoinManager();

        coinManager.insertCredit(10);
        coinManager.addCoinsInDropBox();

        assertNotEquals(25, coinManager.getBet());
    }

    @Test
    void testSetPrize() {
        CoinManager coinManager = new CoinManager();

        coinManager.setPrize(10);

        assertEquals(10, coinManager.getPrize());
    }

    @Test
    void testSetPrizeError() {
        CoinManager coinManager = new CoinManager();

        coinManager.setPrize(10);

        assertNotEquals(25, coinManager.getPrize());
    }

    @Test
    void testGetPrize() {
        CoinManager coinManager = new CoinManager();

        coinManager.setPrize(25);

        assertEquals(25, coinManager.getPrize());
    }

    @Test
    void testGetPrizeError() {
        CoinManager coinManager = new CoinManager();

        coinManager.setPrize(25);

        assertNotEquals(45, coinManager.getPrize());
    }

    @Test
    void testGetBet() {
        CoinManager coinManager = new CoinManager();

        coinManager.insertCredit(10);

        assertEquals(10, coinManager.getBet());
    }

    @Test
    void testGetBetError() {
        CoinManager coinManager = new CoinManager();

        coinManager.insertCredit(10);

        assertNotEquals(15, coinManager.getBet());
    }

    @Test
    void testResetBet() {
        CoinManager coinManager = new CoinManager();

        coinManager.insertCredit(15);
        coinManager.resetBet();

        assertEquals(0, coinManager.getBet());
    }

    @Test
    void testResetBetError() {
        CoinManager coinManager = new CoinManager();

        coinManager.insertCredit(15);
        coinManager.resetBet();

        assertNotEquals(15, coinManager.getBet());
    }

    @Test
    void testResetPrize() {
        CoinManager coinManager = new CoinManager();

        coinManager.setPrize(20);
        coinManager.resetPrize();

        assertEquals(0, coinManager.getPrize());
    }

    @Test
    void testResetPrizeError() {
        CoinManager coinManager = new CoinManager();

        coinManager.setPrize(20);
        coinManager.resetPrize();

        assertNotEquals(20, coinManager.getPrize());
    }

    @Test
    void testGetCoinPool() {
        CoinManager coinManager = new CoinManager();

        coinManager.loadCoins(100);

        assertEquals(100, coinManager.getCoinPool());
    }

    @Test
    void testGetCoinPoolError() {
        CoinManager coinManager = new CoinManager();

        coinManager.loadCoins(100);

        assertNotEquals(200, coinManager.getCoinPool());
    }

    @Test
    void testDeliverPrize() {
        CoinManager coinManager = new CoinManager();

        assertThrows(CoinException.class, () -> {
           coinManager.deliverPrize(50);
        });

        coinManager.insertCredit(60);
        coinManager.addCoinsInDropBox();

        try {
            coinManager.deliverPrize(50);
            assertEquals(10, coinManager.getCoinPool());
        } catch(CoinException exc) {
            exc.printStackTrace();
        }
    }
}