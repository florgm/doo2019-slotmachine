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
                properties.setProperty("reelQuantity","3");

                //clubs es trebol y spades es pica

                properties.setProperty("symbols","AD,AH,AC,AS,"+
                                                 "2D,2H,2C,2S" +
                                                 "3D,3H,3C,3S" +
                                                 "4D,4H,4C,4S" +
                                                 "5D,5H,5C,5S" +
                                                 "6D,6H,6C,6S" +
                                                 "7D,7H,7C,7S" +
                                                 "8D,8H,8C,8S" +
                                                 "9D,9H,9C,9S" +
                                                 "10D,10H,10C,10S" +
                                                 "JD,JH,JC,JS" +
                                                 "QD,QH,QC,QS" +
                                                 "KD,KH,KC,KS");

            } finally {
                instance.setProperties(properties);
            }
        }
        return instance;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

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
            OutputStream output = new FileOutputStream(System.getProperty("user.dir") + File.separator + "config.properties");
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
