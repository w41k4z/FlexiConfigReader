package proj.w41k4z;

import java.io.FileNotFoundException;
import java.io.IOException;

import proj.w41k4z.fcr.PropertiesFile;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        PropertiesFile propertiesFile = new PropertiesFile();
        propertiesFile.load("src/test.properties");
        System.out.println(propertiesFile.getConfig());
    }
}