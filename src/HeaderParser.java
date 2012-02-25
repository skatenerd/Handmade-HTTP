import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 10:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class HeaderParser {
    private static String[] _requestTypes = {"GET", "POST"};
    private static int _defaultContentLength=-1;
    private static String _defaultPath=null;
    private static String _defaultRequestType=null;
    private static String _defaultAcceptEncoding="identity";

    public static int contentLength(List<String> header){
        int contentLength=_defaultContentLength;
        try{
            for(String headerLine: header){
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

    public static String requestType(List<String>header) {
        String requestType=_defaultRequestType;
        if(header.size()>0){
            String requestLine = header.get(0);
            String [] splitted = requestLine.split("[ ]+");
            String firstToken=splitted[0];

            if (isValidRequestType(firstToken)) {
                requestType = firstToken;
            }
        }
        return requestType;
    }

    public static String path(List<String> header) {
        String path=_defaultPath;
        if(header.size()>0){
            String requestLine = header.get(0);
            String [] splitted = requestLine.split("[ ]+");
            if(splitted.length>1){
                String secondToken=splitted[1];
                String rawPath=secondToken;
                path=rawPath.replace("%20"," ");
            }
        }
        return path;
    }
    
    public static List<String> acceptEncodings(List<String> header){
        String encoding=_defaultAcceptEncoding;
        List<String> encodings=new ArrayList<String>();
        encodings.add(encoding);
        for(String headerLine:header){
            String [] splitted = headerLine.split(",?[ ]+");
            if (splitted[0].equalsIgnoreCase("Accept-Encoding:")){
                encodings.add(splitted[1]);
            }
        }
        return encodings;
    }


    private static boolean isValidRequestType(String requestType) {
        for (String current : _requestTypes) {
            if (current.equalsIgnoreCase(requestType)) {
                return true;
            }
        }
        return false;
    }
    
    

    public static boolean validContentLength(int contentLength){
        return contentLength!=_defaultContentLength;
    }

    public static boolean validRequestType(String requestType){
        return requestType!=_defaultRequestType;
    }

    public static boolean validPath(String path){
        return path!=_defaultPath;
    }

}
