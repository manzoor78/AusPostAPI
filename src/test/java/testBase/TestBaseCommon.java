package testBase;

import utilities.ReadProperties;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestBaseCommon {
    public ReadProperties propertyFile = new ReadProperties();
    public static String ausPostURL;
    public static String apiKey = "84a49642-83fc-4de4-bfd3-effe6838783e";
    public String sourceArea;
    public String sourceState;
    public String destinationArea;
    public String destinationState;

    public void initialise() throws FileNotFoundException, IOException, InterruptedException {
        propertyFile.loadPropertyFile();
        ausPostURL = propertyFile.getAusPostURI();
        sourceArea = propertyFile.getSourceArea();
        sourceState = propertyFile.getSourceState();
        destinationArea = propertyFile.getDestinationArea();
        destinationState = propertyFile.getDestinationState();
    }
}
