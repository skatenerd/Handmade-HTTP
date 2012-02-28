import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/26/12
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotAllowedResponse extends Response {
    public NotAllowedResponse(OutputStream output) {
        super(null, output);
    }

    public byte[] getBody() {
        return "<html><head>NOT ALLOWED</head><body/></html>".getBytes();
    }

    public String contentType() {
        return "text/html";
    }

    public String status() {
        return "405 Method Not Allowed";
    }

}
