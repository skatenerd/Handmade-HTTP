import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseFactory {   

    public Response buildResponse(Request request,List<ResponseSubsystem> subsystems) {
        for(ResponseSubsystem subsystem:subsystems){
            if(subsystem.shouldHandle(request)){
                return subsystem.buildResponse(request);
            }
        }
        return new NotAllowedResponse();

    }



}
