package nl.eveoh.qrgenerator.model;

/**
 * Location model class.
 *
 * @author Erik van Paassen
 */
public class Location {

    private String hostKey;
    private String fileName;


    public String getHostKey() {
        return hostKey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setHostKey(String hostKey) {
        this.hostKey = hostKey;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}