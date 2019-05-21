package slotmachine;

import slotmachine.coinrelated.CoinSlot;
import slotmachine.coinrelated.DropBox;
import slotmachine.coinrelated.PayoutTray;
import slotmachine.gamemode.GameMode;
import slotmachine.gamemode.GameModeFactory;
import slotmachine.gamemode.random.RandomFactory;
import slotmachine.gamemode.sequence.SequenceFactory;

public class SlotMachine {
    private static SlotMachine instance;
    private GameMode gamePlayGenerator;
    private CoinSlot coinSlot;
    private DropBox dropBox;
    private PayoutTray payoutTray;

    private SlotMachine() { }

    public static SlotMachine getInstance() {
        if (instance == null) {
            instance = new SlotMachine();
        }

        return instance;
    }


}
