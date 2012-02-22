import java.util.*;

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
    private List<String> _body;
    //private


    public RequestImpl(Iterator<String> request) {
    
        List [] headerAndBody=getHeaderAndBody(request);
        List<String> header = headerAndBody[0];
        List<String> body=headerAndBody[1];
        _body=body;
        _requestType=requestType(header.get(0));
        _path=path(header.get(0));

        
    }

    public static String requestType(String requestLine) {
        String [] splitted = requestLine.split("[ ]+");
        String firstToken=splitted[0];
                
        if (isValidRequestType(firstToken)) {
            return firstToken;
        }
        return null;
    }

    public static String path(String requestLine) {
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

    public static List<String> getHeader(Iterator<String> request){
        String curString;
        List <String> rtnList=new ArrayList<String>();
        while((curString=request.next().trim())!=""){
            rtnList.add(curString);
        }
        return rtnList;
        
    }
    
    public static List<String> getBody(Iterator<String> partiallyIteratedRequest){
        List <String> rtnList=new ArrayList<String>();
        while(partiallyIteratedRequest.hasNext()){
            rtnList.add(partiallyIteratedRequest.next());
        }
        return rtnList;
        
    }
    
    public static List [] getHeaderAndBody(Iterator<String> request){
        List [] headerAndBody = new List[2];
        headerAndBody[0]=getHeader(request);
        headerAndBody[1]=getBody(request);
        return headerAndBody;
        
    }

    public String getPath(){
        return _path;
    }
    
    public String getRequestType(){
        return _requestType;
    }
    
    public List<String> getBody(){
        return _body;
    }
}
