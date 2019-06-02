package slotmachine.gamemodestate;

public class GameModeContext implements State{

    private State gameModeState;

    public void setState(State state){

        this.gameModeState = state;
    }

    public State getState(){

        return this.gameModeState;
    }

    @Override
    public List<Integer> getNextValues(){

        return this.doAction();
    }
}