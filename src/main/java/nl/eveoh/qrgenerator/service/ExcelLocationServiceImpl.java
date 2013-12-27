package nl.eveoh.qrgenerator.service;

import nl.eveoh.qrgenerator.model.Location;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Erik van Paassen
 */
@Component
public class ExcelLocationServiceImpl implements LocationService {

    @Value("${Excel.HostKeyColumnHeader}")
    private String hostKeyColumnHeader;



    @Override
    public ArrayList<Location> getLocations(String inputFilePath) {
        ArrayList<Location> locations = new ArrayList<>();

        Workbook wb;
        try {
            wb = WorkbookFactory.create(new File(inputFilePath));
        } catch (InvalidFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        Sheet sheet = wb.getSheetAt(0);
        int rowIndex = 0;
        int hostKeyColumn = 0;
        for (Row row : sheet) {
            if (rowIndex == 0) {
                hostKeyColumn = getHostKeyCell(row);
            } else {
                Location location = parseRow(row, hostKeyColumn);

                if (location != null) {
                    locations.add(location);
                }
            }

            rowIndex++;
        }

        return locations;
    }

    private int getHostKeyCell(Row row) {
        int cellIndex = 0;
        for (Cell cell : row) {
            if (hostKeyColumnHeader.equalsIgnoreCase(cell.getStringCellValue())) {
                return cellIndex;
            }

            cellIndex++;
        }

        return -1;
    }

    private Location parseRow(Row row, int hostKeyColumn) {
        Location location = new Location();

        Cell cell = row.getCell(hostKeyColumn);
        if (cell == null) {
            return null;
        }

        location.setHostKey(cell.getStringCellValue());

        return location;
    }
}