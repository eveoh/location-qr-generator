package nl.eveoh.qrgenerator.exception;

/**
 * @author Erik van Paassen
 */
public class QrEncodingException extends Exception {

    public QrEncodingException() {
    }

    public QrEncodingException(String message) {
        super(message);
    }

    public QrEncodingException(String message, Throwable cause) {
        super(message, cause);
    }

    public QrEncodingException(Throwable cause) {
        super(cause);
    }

    public QrEncodingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}