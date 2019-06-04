package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    Properties properties = new Properties();

    public void loadPropertyFile() throws FileNotFoundException, IOException {
        String filePath = System.getProperty("user.dir") + "/src/main/java/config/config.properties";
        File file = new File(filePath);
        properties.load(new FileInputStream(file));
    }

    public String getAusPostURI() {
        return properties.getProperty("ausPostURI");
    }

    public String getSourceArea() {
        return properties.getProperty("sourceArea");
    }

    public String getSourceState() {
        return properties.getProperty("sourceState");
    }

    public String getDestinationArea() {
        return properties.getProperty("destinationArea");
    }

    public String getDestinationState() {
        return properties.getProperty("destinationState");
    }
}
