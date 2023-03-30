package nl.ordina.distribution.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;

public class FactoryError extends HttpClientErrorException {

    public FactoryError(HttpStatus statusCode) {
        super(statusCode);
    }

    public FactoryError(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public FactoryError(HttpStatus statusCode, String statusText, byte[] body, Charset responseCharset) {
        super(statusCode, statusText, body, responseCharset);
    }

    public FactoryError(HttpStatus statusCode, String statusText, HttpHeaders headers, byte[] body, Charset responseCharset) {
        super(statusCode, statusText, headers, body, responseCharset);
    }

    public FactoryError(String message, HttpStatus statusCode, String statusText, HttpHeaders headers, byte[] body, Charset responseCharset) {
        super(message, statusCode, statusText, headers, body, responseCharset);
    }
}