import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class MalformedRequestResponder {
    public MalformedRequestResponder(){}
    
    public Response buildResponse(Request request,OutputStream stream){
        if(request.get_timedOut()){
            return new TimeoutResponse(stream);
        }else{
            return new BadRequestResponse(stream);
        }
    }

    public boolean shouldHandle(Request request){
        return !request.isWellFormed();
    }

}
