package nl.eveoh.qrgenerator.service;

import nl.eveoh.qrgenerator.model.Location;

import java.util.ArrayList;

/**
 * @author Erik van Paassen
 */
public interface LocationService {

    public ArrayList<Location> getLocations(String inputFilePath);
}