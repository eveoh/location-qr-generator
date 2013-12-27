package nl.eveoh.qrgenerator.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import nl.eveoh.qrgenerator.exception.QrEncodingException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Erik van Paassen
 */
@Component
public class QrServiceImpl implements QrService {

    private static final String IMAGE_FORMAT_PNG = "png";

    @Override
    public void generateQrCode(int width, int height, OutputStream stream, String content)
            throws QrEncodingException, IOException {
        BitMatrix bitMatrix;

        try {
            bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException e) {
            throw new QrEncodingException(e);
        }

        MatrixToImageWriter.writeToStream(bitMatrix, IMAGE_FORMAT_PNG, stream);
    }
}