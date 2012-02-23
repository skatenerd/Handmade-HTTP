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
    private static int _defaultContentLength=-1;
    private static String _defaultPath=null;
    private static String _defaultRequestType=null;
    private static byte [] _default_body = new byte[0];

    
    private String _requestType;
    private String _path;
    private List<String> _header;
    private byte [] _body;
    private int _contentLength;


    public RequestImpl(InputStream request)
    throws IOException{
        _header= extractHeader(request);
        _requestType=requestType();
        _path=path();
        _contentLength=contentLength();
        _body=extractBody(request, _contentLength);

    }
    
    private int contentLength(){
        //should look for exceptions here
        int contentLength=_defaultContentLength;
        try{
            for(String headerLine:_header){
                String [] splitted = headerLine.split("[ ]+");
                if (splitted[0].equalsIgnoreCase("Content-length:")){
                    contentLength = Integer.parseInt(splitted[1]);
                }
            }
        }catch(NumberFormatException e){
            //redundant
            contentLength = _defaultContentLength;
        }finally{
            return contentLength;
        }
    }

    public String requestType() {
        String requestType=_defaultRequestType;
        if(_header.size()>0){
            String requestLine = _header.get(0);
            String [] splitted = requestLine.split("[ ]+");
            String firstToken=splitted[0];

            if (isValidRequestType(firstToken)) {
                requestType = firstToken;
            }
        }
        return requestType;
    }

    public String path() {
        String path=_defaultPath;
        if(_header.size()>0){
            String requestLine = _header.get(0);
            String [] splitted = requestLine.split("[ ]+");
            if(splitted.length>1){
            String secondToken=splitted[1];
                path=secondToken;
            }
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

        LineReader reader= new LineReader(stream);
        List<String> rtnList = new ArrayList<String>();
        String curString;

        while((curString=reader.readLine().trim()).length()>0){
            rtnList.add(curString);
        }
        return rtnList;

    }

    public byte [] extractBody(InputStream stream, int contentLength)
    throws IOException{
        byte [] body=_default_body;
        if(contentLengthSupplied()){
            body= new byte[contentLength];
            stream.read(body);
        }

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

    public int get_ContentLength(){
        return _contentLength;
    }

    public boolean contentLengthSupplied(){
        return _contentLength!=_defaultContentLength;
    }

    public boolean requestTypeSupplied(){
        return _requestType!=_defaultRequestType;
    }

    public boolean pathSupplied(){
        return _path!=_defaultPath;
    }
}
