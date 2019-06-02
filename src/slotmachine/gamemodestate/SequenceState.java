package slotmachine.gamemodestate;
import slotmachine.gamemode.GameMode;

public class SequenceState implements State {

    @Override
    public void doAction(){
        System.out.println("Sequence Mode");
    }
}