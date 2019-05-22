package slotmachine.gamemode;

import slotmachine.gamemode.random.RandomFactory;
import slotmachine.gamemode.randomize.IRandomize;
import slotmachine.gamemode.randomize.Randomize;
import slotmachine.gamemode.sequence.SequenceFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> reelSize = new ArrayList<>();
        reelSize.add(5);
        reelSize.add(3);
        reelSize.add(8);

        IRandomize randomize = new Randomize();


        GameMode random = GameModeFactory.getGameMode(new RandomFactory(reelSize, randomize));
        GameMode sequence = GameModeFactory.getGameMode(new SequenceFactory(reelSize,10, randomize));

        for(int i = 0; i < 10; i++)
            System.out.println(i+" "+random.getNextValues());

        for(int i = 0; i < 20; i++)
            System.out.println(i+" "+sequence.getNextValues());
    }
}
