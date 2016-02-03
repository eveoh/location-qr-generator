package nl.eveoh.qrgenerator.model;

/**
 * Location model class.
 *
 * @author Erik van Paassen
 */
public class Location {

    private String hostKey;
	private String locationName;



    public String getHostKey() {
        return hostKey;
    }

	public String getLocationName()
	{
		return locationName;
	}

    public void setHostKey(String hostKey) {
        this.hostKey = hostKey;
    }

	public void setLocationName(String locationName)
	{
		this.locationName = locationName;
	}
}