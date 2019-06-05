package slotmachine;

public class Main {
    public static void main(String[] args) {
        SlotMachine slotMachine;

        slotMachine = SlotMachine.getInstance();

        slotMachine.initComponents();
        slotMachine.loadConfiguration();

        slotMachine.getResult();
//        slotMachine.reels();
//
//        slotMachine.prueba();
//
//        slotMachine.play();
    }
}
