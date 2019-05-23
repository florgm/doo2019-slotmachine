package slotmachine.gamemode.random;

import org.junit.jupiter.api.Test;
import slotmachine.gamemode.GameMode;
import slotmachine.gamemode.GameModeFactory;
import slotmachine.gamemode.randomize.IRandomize;
import slotmachine.gamemode.randomize.RandomizeMock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomTest {
    @Test
    void testGetNextValues3Reels() {
        List<Integer> reelSize = new ArrayList<>();
        reelSize.add(5);
        reelSize.add(3);
        reelSize.add(8);

        IRandomize randomize = new RandomizeMock();

        GameMode random = GameModeFactory.getGameMode(new RandomFactory(reelSize,randomize));

        List<Integer> result = random.getNextValues();

        List<Integer> expResult = new ArrayList<>();
        expResult.add(0);
        expResult.add(2);
        expResult.add(5);

        assertEquals(expResult,result);
    }

    @Test
    void testGetNextValuesError3Reels() {
        List<Integer> reelSize = new ArrayList<>();
        reelSize.add(5);
        reelSize.add(3);
        reelSize.add(8);

        IRandomize randomize = new RandomizeMock();

        GameMode random = GameModeFactory.getGameMode(new RandomFactory(reelSize,randomize));

        List<Integer> result = random.getNextValues();

        List<Integer> expResult = new ArrayList<>();
        expResult.add(3);
        expResult.add(8);
        expResult.add(2);

        assertNotEquals(expResult,result);
    }

    @Test
    void testGetNextValues5Reels() {
        List<Integer> reelSize = new ArrayList<>();
        reelSize.add(5);
        reelSize.add(3);
        reelSize.add(2);
        reelSize.add(4);
        reelSize.add(9);

        IRandomize randomize = new RandomizeMock();

        GameMode random = GameModeFactory.getGameMode(new RandomFactory(reelSize,randomize));

        List<Integer> result = random.getNextValues();

        List<Integer> expResult = new ArrayList<>();
        expResult.add(0);
        expResult.add(2);
        expResult.add(1);
        expResult.add(1);
        expResult.add(5);

        assertEquals(expResult,result);
    }

    @Test
    void testGetNextValuesError5Reels() {
        List<Integer> reelSize = new ArrayList<>();
        reelSize.add(5);
        reelSize.add(3);
        reelSize.add(2);
        reelSize.add(4);
        reelSize.add(9);

        IRandomize randomize = new RandomizeMock();

        GameMode random = GameModeFactory.getGameMode(new RandomFactory(reelSize,randomize));

        List<Integer> result = random.getNextValues();

        List<Integer> expResult = new ArrayList<>();
        expResult.add(5);
        expResult.add(3);
        expResult.add(7);
        expResult.add(0);
        expResult.add(1);

        assertNotEquals(expResult,result);
    }
}