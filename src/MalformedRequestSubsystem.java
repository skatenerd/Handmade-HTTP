import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class MalformedRequestSubsystem implements ResponseSubsystem {
    public MalformedRequestSubsystem(){}
    
    public Response buildResponse(Request request){
        if(request.get_timedOut()){
            return new TimeoutResponse();
        }else{
            return new BadRequestResponse();
        }
    }

    public boolean shouldHandle(Request request){
        return !request.isWellFormed();
    }

}
