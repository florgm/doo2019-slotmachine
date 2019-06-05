package slotmachine.settings;

import java.io.*;
import java.util.Properties;

public class Settings {
    private static Settings instance;
    private Properties properties;

    private Settings () { }

    public static Settings getInstance() {
        if(instance == null) {
            Properties properties = new Properties();
            instance = new Settings();

            try {
                properties = instance.loadSettings();
            } catch (ConfigExceptions fileNotFound) {

                //Setear por default
                properties.setProperty("coinPool","300");
                properties.setProperty("gameMode","random");
                properties.setProperty("reelQuantity","5");
                properties.setProperty("sequenceQ", "10");

                //clubs es trebol y spades es pica
                properties.setProperty("symbols","DA,HA,CA,SA,"+
                                                 "D2,H2,C2,S2," +
                                                 "D3,H3,C3,S3," +
                                                 "D4,H4,C4,S4," +
                                                 "D5,H5,C5,S5," +
                                                 "D6,H6,C6,S6," +
                                                 "D7,H7,C7,S7," +
                                                 "D8,H8,C8,S8," +
                                                 "D9,H9,C9,S9," +
                                                 "D10,H10,C10,S10," +
                                                 "DJ,HJ,CJ,SJ," +
                                                 "DQ,HQ,CQ,SQ," +
                                                 "DK,HK,CK,SK");

            } finally {
                instance.setProperties(properties);
                instance.saveSettings();
            }
        }
        return instance;
    }

    public void setProperties(Properties properties) { this.properties = properties; }

    public Properties getProperties() { return properties; }

    public Properties loadSettings() throws ConfigExceptions {
        try {
            InputStream input = new FileInputStream(System.getProperty("user.dir") + File.separator + "settings.properties");
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (IOException exception){
            throw new ConfigExceptions();
        }
    }

    public void saveSettings() {
        try {
            OutputStream output = new FileOutputStream(System.getProperty("user.dir") + File.separator + "settings.properties");
            properties.store(output,null);
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (IOException io){
            io.printStackTrace();
        }
    }
}
