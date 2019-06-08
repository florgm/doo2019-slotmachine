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
