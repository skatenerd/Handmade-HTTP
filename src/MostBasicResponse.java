import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/25/12
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class MostBasicResponse extends Response {
    public MostBasicResponse(OutputStream stream) {
        super(null, null, stream);
    }

    public byte[] getBody() {
        return "workers\nrevolt\nin\nsouth asia".getBytes();
    }

    public String contentType() {
        return "text/plain";
    }

    public String status() {
        return "200 OK";
    }

    public int contentLength() {
        return getBody().length;
    }
}
