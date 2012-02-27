import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/27/12
 * Time: 7:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class TimeoutResponse extends Response {
    public TimeoutResponse(OutputStream output) {
        super(null, null, output);
    }

    public byte[] getBody() {
        return "<html><head>Timed Out</head><body/></html>".getBytes();
    }

    public String contentType() {
        return "text/html";
    }

    public String status() {
        return "408 Request Timeout";
    }

    public int contentLength() {
        return getBody().length;
    }
}
