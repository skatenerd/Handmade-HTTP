import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/26/12
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class BadRequestResponse extends Response {
    public BadRequestResponse(OutputStream output) {
        super(null, output);
    }

    public byte[] getBody() {
        return "<html><head>Bad Request</head><body/></html>".getBytes();
    }

    public String contentType() {
        return "text/html";
    }

    public String status() {
        return "400 Bad Request";
    }
}
