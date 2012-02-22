import java.util.*;
import java.io.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/21/12
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestImpl implements Request{

    private static String[] _requestTypes = {"GET", "POST"};
    private String _requestType;
    private String _path;
    private List<String> _header;
    private byte [] _body;


    public RequestImpl(InputStream request)
    throws IOException{
        assignHeaderAndBody(request);
        _requestType=requestType();
        _path=path();


    }

    private void assignHeaderAndBody(InputStream request)
    throws IOException{
        _header= extractHeader(request);
        _body=extractBody(request, 0);

    }

    private LineReader getLineReader(InputStream stream)
            throws IOException {
        return new LineReader(new InputStreamReader(stream));
    }

    public String requestType() {
        String requestLine = _header.get(0);
        String [] splitted = requestLine.split("[ ]+");
        String firstToken=splitted[0];

        if (isValidRequestType(firstToken)) {
            return firstToken;
        }
        return null;
    }

    public String path() {
        String requestLine = _header.get(0);
        String [] splitted = requestLine.split("[ ]+");
        String path=null;
        if(splitted.length>1){
        String secondToken=splitted[1];
            path=secondToken;
        }
        return path;
    }

    private static boolean isValidRequestType(String requestType) {
        for (String current : _requestTypes) {
            if (current.equalsIgnoreCase(requestType)) {
                return true;
            }
        }
        return false;
    }

    public List<String> extractHeader(InputStream stream)
            throws IOException {

        LineReader reader= getLineReader(stream);
        List<String> rtnList = new ArrayList<String>();
        String curString;

        while((curString=reader.readLine().trim()).length()>0){
            rtnList.add(curString);
        }
        return rtnList;

    }

    public byte [] extractBody(InputStream stream, int contentLength)
    throws IOException{
        byte [] body= new byte[contentLength];
        stream.read(body);
        return body;
    }



    public String get_path(){
        return _path;
    }

    public String get_RequestType(){
        return _requestType;
    }

    public byte [] get_Body(){
        return _body;
    }
    
    public List<String> get_header(){
        List<String> rtn=new ArrayList<String>();
        rtn.addAll(_header);
        return rtn;
    }
}
