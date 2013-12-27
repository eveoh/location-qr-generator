package nl.eveoh.qrgenerator.service;

import nl.eveoh.qrgenerator.exception.QrEncodingException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Erik van Paassen
 */
public interface QrService {

    public void generateQrCode(int width, int height, OutputStream stream, String content)
            throws QrEncodingException, IOException;
}