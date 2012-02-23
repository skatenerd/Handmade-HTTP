import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
    static String _defaultConnectionType="close";
    protected static byte [] _defaultBody=null;
    public Response(Request request, FileBrowser browser, OutputStream output){
        _request=request;
        _browser=browser;
        _output=output;
    }


    public void writeResponse()
    throws IOException{
        writeHeader();
        writeBody();
    }
    private void writeHeader()
    throws FileNotFoundException{
        PrintWriter writer=new PrintWriter(_output,true);
        for(String line:getHeader()){
            writer.println(line);
        }
        writer.println("");
    }
    private void writeBody()
    throws IOException{
        _output.write(getBody());
    }

    protected abstract byte[] getBody() throws FileNotFoundException;
    protected abstract String status();
    protected abstract String contentType();
    protected abstract int contentLength() throws FileNotFoundException;

    private String statusLine(){
        return "HTTP/1.1 "+status();
    }

    private String connectionTypeHeader(){
        return "Connection: " + _defaultConnectionType;
    }

    private String contentTypeHeader(){
        return "Content-Type: "+contentType();
    }
    
    private String contentLengthHeader()
    throws FileNotFoundException{
        String lengthString=new Integer(contentLength()).toString();
        return "Content-Length: "+lengthString;
    }


    
    protected List<String> getHeader()
    throws FileNotFoundException{
        List<String> rtn=new ArrayList<String>();
        rtn.add(statusLine());
        rtn.add(connectionTypeHeader());
        rtn.add(contentTypeHeader());
        rtn.add(contentLengthHeader());
        return rtn;
    }




}
