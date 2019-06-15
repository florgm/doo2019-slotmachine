package slotmachine.coinrelated;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DropBoxTest {

    @Test
    void testSetCoinPool() {
        DropBox dropBox = new DropBox();

        dropBox.setCoinPool(100);

        assertEquals(100, dropBox.getCoinPool());
    }

    @Test
    void testGetCoinPool() {
        DropBox dropBox = new DropBox();

        dropBox.setCoinPool(50);

        assertEquals(50, dropBox.getCoinPool());
    }

    @Test
    void testAddToCoinPool() {
        DropBox dropBox = new DropBox();

        dropBox.setCoinPool(50);
        dropBox.addToCoinPool(15);

        assertEquals(65, dropBox.getCoinPool());
    }

    @Test
    void testWithdrawCoins() {
        DropBox dropBox = new DropBox();

        dropBox.setCoinPool(100);
        dropBox.addToCoinPool(10);
        dropBox.withdrawCoins(20);

        assertEquals(90, dropBox.getCoinPool());
    }
}