package slotmachine;

import slotmachine.playresult.PokerPlayResult;
import slotmachine.recordrelated.RecordManager;
import slotmachine.reelrelated.ReelManagerPokerStyle;
import slotmachine.ui.view.SlotMachineViewFacade;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        SlotMachine slotMachine;
        Properties properties = new Properties();

        RecordManager.getInstance().loadRecords();

        slotMachine = SlotMachine.getInstance();

        try {
            InputStream input = new FileInputStream(System.getProperty("user.dir") +  "/resources/pokerSettings.properties");
            properties.load(input);

        } catch (IOException exception){
            System.out.println("Error al abrir el archivo");;
        }

        SlotMachineViewFacade.setPlayHandler(slotMachine);
        SlotMachineViewFacade.setCreditHandler(slotMachine);
        SlotMachineViewFacade.setGameModeHandler(slotMachine);

        slotMachine.initComponents(new PokerPlayResult(), new ReelManagerPokerStyle(), Integer.valueOf(properties.getProperty("reelQuantity")), properties.getProperty("symbols"));
        slotMachine.loadConfiguration();

        slotMachine.setiDisplayHandler(SlotMachineViewFacade.getDisplayHandler());
        slotMachine.setiPrizeHandler(SlotMachineViewFacade.getPrizeHandler());
        slotMachine.setiReelsHandler(SlotMachineViewFacade.getReelsHandler());

        SlotMachineViewFacade.show();

    }
}
