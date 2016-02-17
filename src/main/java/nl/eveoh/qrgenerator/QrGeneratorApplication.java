package nl.eveoh.qrgenerator;

import nl.eveoh.qrgenerator.exception.QrEncodingException;
import nl.eveoh.qrgenerator.model.Location;
import nl.eveoh.qrgenerator.service.LocationService;
import nl.eveoh.qrgenerator.service.QrService;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * @author Erik van Paassen
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class QrGeneratorApplication implements CommandLineRunner {

    @Autowired
    private LocationService locationService;

    @Autowired
    private QrService qrService;

    @Value("${Qr.Width}")
    private int qrCodeWidth;

    @Value("${Qr.Height}")
    private int qrCodeHeight;

    @Value("${Url.Scheme}")
    private String urlScheme;

    @Value("${Url.Host}")
    private String urlHost;

    @Value("${Url.TimetableType}")
    private String urlTimetableType;



    public void generateQrCodes(String inputFile, String outputDirPath) {
        boolean allSucceeded = true;

        ArrayList<Location> locations = locationService.getLocations(inputFile);

        for (Location location : locations) {
            if (StringUtils.isEmpty(location.getHostKey())) {
                continue;
            }

            URIBuilder uriBuilder = new URIBuilder()
                    .setScheme(urlScheme)
                    .setHost(urlHost)
                    .setPath("/m/")
                    .addParameter("type", urlTimetableType)
                    .addParameter("hostKey", location.getHostKey());

            String uri;
            try {
                uri = uriBuilder.build().toASCIIString();
            } catch (URISyntaxException e) {
                throw new RuntimeException("Error while generating URL.", e);
            }

            // Create output directory if it doesn't exist.
            File outputDir = new File(outputDirPath);
            if (!outputDir.exists()) {
                if (!outputDir.mkdir()) {
                    throw new RuntimeException("Could not create output directory.");
                }
            }

            FileOutputStream out = null;
            try {
                out = new FileOutputStream(new File(outputDirPath + File.separator + location.getFileName() + ".png"));

                try {
                    qrService.generateQrCode(qrCodeWidth, qrCodeHeight, out, uri);
                    System.out.println("Generate SUCCESS: " + location.getHostKey());
                } catch (QrEncodingException | IOException e) {
                    allSucceeded = false;
                    System.out.println("Generate FAILED : " + location.getHostKey());
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Error while opening output file.", e);
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // Whatever...
                    }
                }
            }
        }

        if (allSucceeded) {
            System.out.println("FINISHED: All QR codes have been generated successfully.");
        } else {
            System.out.println("FINISHED: There were errors!");
        }
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length != 2)  {
            System.err.println("Requires two arguments: <input xls(x)> <output dir>");
        }

        generateQrCodes(args[0], args[1]);
    }

    public static void main(String[] args) {
        SpringApplication.run(QrGeneratorApplication.class, args);
    }
}