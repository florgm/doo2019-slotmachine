package slotmachine.settings;

import java.io.*;
import java.util.Properties;

public class Settings {
    private static Settings instance;
    private Properties properties;
    private int coinPool;
    private String gameMode;

    private Settings () {
        coinPool = 1000;
        gameMode = "Random";
    }

    public static Settings getInstance() {
        if(instance == null) {
            Properties properties = new Properties();
            instance = new Settings();

            try {
                properties = instance.loadSettings();
            } catch (ConfigExceptions fileNotFound) {

                //Setear por default
                properties.setProperty("coinPool","1000");
                properties.setProperty("gameMode","Random");

            } finally {
                instance.setProperties(properties);
                instance.saveSettings();
            }
        }
        return instance;
    }

    public void setCoinPool(int coinPool) {
        this.coinPool = coinPool;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
        System.out.println(gameMode);
    }

    public void setProperties(Properties properties) { this.properties = properties; }

    public Properties getProperties() { return properties; }

    public Properties loadSettings() throws ConfigExceptions {
        try {
            InputStream input = new FileInputStream(System.getProperty("user.dir") +  "/resources/settings.properties");
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (IOException exception){
            throw new ConfigExceptions();
        }
    }

    public void saveSettings() {
        try {
            OutputStream output = new FileOutputStream(System.getProperty("user.dir") + "/resources/settings.properties");
            properties.setProperty("gameMode", gameMode);
            properties.setProperty("coinPool", Integer.toString(coinPool));
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
