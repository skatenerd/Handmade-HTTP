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
    protected FileBrowser _browser;
    protected OutputStream _output;
    static String _defaultConnectionType = "close";
    protected static byte[] _defaultBody = null;

    public Response(Request request, FileBrowser browser, OutputStream output) {
        _request = request;
        _browser = browser;
        _output = output;
    }


    public void writeResponse()
            throws IOException {
        try{
        writeHeader();
        writeBody();
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

    private void writeHeader()
            throws IOException {
        try{
        for (String line : getHeader()) {
            _output.write(line.getBytes());
            _output.write("\n".getBytes());
        }
        _output.write("\n".getBytes());
        }catch (SocketException e){
            //System.out.println("fizzzaaa  " + getHeader());
            throw e;
        }
    }

    private void writeBody()
            throws IOException {
        _output.write(getBody());
    }

    protected abstract byte[] getBody() throws IOException;

    protected abstract String status();

    protected abstract String contentType();

    protected abstract int contentLength() throws IOException;

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
