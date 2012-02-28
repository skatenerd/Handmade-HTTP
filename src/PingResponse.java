import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class PingResponse extends Response{
    public PingResponse(OutputStream stream){
        super(null,null,stream);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            //oh well.
        }
    }

    protected byte [] getBody(){
        return "PONG!".getBytes();
    }
    
    protected int contentLength(){
        return getBody().length;
    }
    
    protected String contentType(){
        return "text/html";
    }
    
    protected String status(){
        return "200 OK";
    }


}
