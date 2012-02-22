import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/21/12
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Response {
    Request _request;
    FileBrowser _browser;
    String _status;
    Writeable _output;
    static String connection="close";
    static String contentType="text";
    public Response(Request request, FileBrowser browser, Writeable output){
        _request=request;
        _browser=browser;
        _status=responseStatus(request,browser);
        _output=output;
    }
    
    public String [] listFiles(){
        return _browser.ListDirectory(_request.getPath());
    }
    
    private static String responseStatus(Request request,FileBrowser browser){
        String [] files = browser.ListDirectory(request.getPath());
        if (files==null){
            return "404 Not Found";
        }else{
            return "200 OK";
        }
    }
    
    public String statusLine(){
        return "HTTP/1.1 "+_status;
    }
    
    public String connectionTypeHeader(){
        return "Connection: " + connection;
    }
    
    public String contentTypeHeader(){
        return "Content-Type: "+contentType;
    }
    
    public List<String> response(){
        List<String> rtn=new ArrayList<String>();
        rtn.add(statusLine());
        rtn.add(connectionTypeHeader());
        rtn.add(contentTypeHeader());
        rtn.add("");
        for(String file:listFiles()){
            rtn.add(file);
        }
        
        return rtn;
    }

    public void writeResponse(){
        for(String line:response()){
            _output.PrintLn(line);
        }
    }



}
