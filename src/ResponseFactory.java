import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseFactory {   
    private static MalformedRequestSubsystem malformedRequestSubsystem=new MalformedRequestSubsystem();
    public Response buildResponse(Request request,List<ResponseSubsystem> subsystems) {
        if(malformedRequestSubsystem.shouldHandle(request)){
            return malformedRequestSubsystem.buildResponse(request);
        }else{
            for(ResponseSubsystem subsystem:subsystems){
                if(subsystem.shouldHandle(request)){
                    return subsystem.buildResponse(request);
                }
            }
        }
        return new NotAllowedResponse();
    }



}
