import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class PingSubsystem implements ResponseSubsystem {

    public PingSubsystem(){}
    
    public Response buildResponse(Request request, OutputStream stream){
        return new PingResponse(stream);
    }
    
    public boolean shouldHandle(Request request){
        return request.pathSupplied() && request.get_path().equals(ConfigConstants.pingLocation);
    }
    
    
}
