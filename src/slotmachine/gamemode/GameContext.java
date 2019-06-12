package slotmachine.gamemode;

import slotmachine.gamemode.random.RandomFactory;
import slotmachine.gamemode.randomize.IRandomize;
import slotmachine.gamemode.randomize.Randomize;
import slotmachine.gamemode.sequence.SequenceFactory;

import java.util.List;

public class GameContext implements Mode {
    private Mode mode;
    private String gameModeKey;
    private List<Integer> reelSizes;

    //TODO ver de hacer un enum
    public enum GameMode {
        RANDOM,
        SEQUENCE
    }

    public void setReelSizes(List<Integer> reelSizes) {
        this.reelSizes = reelSizes;
    }

    public void setMode(String gameMode) {
        IRandomize randomize = new Randomize();

        this.gameModeKey = gameMode;

        if(gameMode.equals("Random")) {
            this.mode = GameModeFactory.getGameMode(new RandomFactory(reelSizes,randomize));
        } else {
            this.mode = GameModeFactory.getGameMode(new SequenceFactory(reelSizes,20,randomize));
        }
    }

    public String changeMode() {
        if(gameModeKey.equals("Random")) {
            this.setMode("Sequence");
            return "Random";
        } else {
            this.setMode("Random");
            return "Sequence";
        }
    }

    public String getGameModeKey() {
        return gameModeKey;
    }

    @Override
    public List<Integer> getNextValues() {
        return this.mode.getNextValues();
    }
}
