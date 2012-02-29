import javax.sound.midi.SysexMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.*;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/21/12
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Response {
    protected Request _request;
    static String _defaultConnectionType = "close";
    protected static byte[] _defaultBody = null;

    public Response(Request request) {
        _request = request;
    }


    public void writeResponse(OutputStream output)
            throws IOException {
        try{
        writeHeader(output);
        writeBody(output);
        }catch (SocketException e){
            if(e.getMessage().equals("Broken pipe") || e.getMessage().equals("Connection reset")){
                //pass, the client closed the connection
            }else{
                throw(e);
            }
        }catch (IOException e){
            throw e;
        }
    }

    private void writeHeader(OutputStream output)
            throws IOException {
        try{
        for (String line : getHeader()) {
            output.write(line.getBytes());
            output.write("\n".getBytes());
        }
        output.write("\n".getBytes());
        }catch (SocketException e){
            throw e;
        }
    }

    private void writeBody(OutputStream output)
            throws IOException {
        output.write(getBody());
    }

    protected abstract byte[] getBody() throws IOException;

    protected abstract String status();

    protected abstract String contentType();

    protected int contentLength()
    throws IOException{
        return getBody().length;
    }

    private String statusLine() {
        return "HTTP/1.1 " + status();
    }

    private String connectionTypeHeader() {
        return "Connection: " + _defaultConnectionType;
    }

    private String contentTypeHeader() {
        return "Content-Type: " + contentType();
    }

    private String contentLengthHeader()
            throws IOException {
        String lengthString = new Integer(contentLength()).toString();
        return "Content-Length: " + lengthString;
    }


    public List<String> getHeader()
            throws IOException {
        List<String> rtn = new ArrayList<String>();
        rtn.add(statusLine());
        rtn.add(connectionTypeHeader());
        rtn.add(contentTypeHeader());
        rtn.add(contentLengthHeader());
        return rtn;
    }


}
