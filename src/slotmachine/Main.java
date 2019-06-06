package slotmachine;

import slotmachine.playresult.PokerPlayResult;
import slotmachine.reelrelated.ReelManagerPokerStyle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        SlotMachine slotMachine;
        Properties properties = new Properties();

        slotMachine = SlotMachine.getInstance();

        try {
            InputStream input = new FileInputStream(System.getProperty("user.dir") +  "/resources/pokerSettings.properties");
            properties.load(input);

        } catch (IOException exception){
            System.out.println("Error al abrir el archivo");;
        }


        slotMachine.initComponents(new PokerPlayResult(), new ReelManagerPokerStyle(), 5, properties.getProperty("symbols"));
        slotMachine.loadConfiguration();

        slotMachine.getResult();
        //slotMachine.guardarRecord();
//        slotMachine.reels();
//
        slotMachine.prueba();

//
//        slotMachine.play();
    }
}
