package slotmachine.gamemodestate;
import slotmachine.gamemode.GameMode;

public class RandomState implements State {

    @Override
    public void doAction(){
        System.out.println("Random Mode");
    }
}
