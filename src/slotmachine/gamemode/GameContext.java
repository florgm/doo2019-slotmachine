package slotmachine.gamemode;

import java.util.List;

public class GameContext implements Mode{
    private Mode mode;

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return this.mode;
    }

    @Override
    public List<Integer> getNextValues() {
        return this.mode.getNextValues();
    }
}
