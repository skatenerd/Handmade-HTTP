import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotFoundResponse extends Response {
    public NotFoundResponse (OutputStream output, MarkupGenerator markup){
        super(null,null,output);
    }
    
    public byte [] getBody(){
        return "<html><head>NOT FOUND</head><body/></html>".getBytes();
    }
    
    public String contentType(){
        return "text/html";
    }
    
    public String status(){
        return "404 Not Found";
    }
    
    public int contentLength(){
        return getBody().length;
    }
}
