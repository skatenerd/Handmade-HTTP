import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseFactory {
    public static List<ResponseSubsystem> _defaultSubsystems=ConfigConstants.getDefaultSubsystems();
    private List<ResponseSubsystem> _subsystems;

    public ResponseFactory(List<ResponseSubsystem> subsystemList) {
        _subsystems=subsystemList;
        _subsystems.addAll(_defaultSubsystems);
    }

    public ResponseFactory(){
        this(new ArrayList<ResponseSubsystem>());
    }
   

    public Response buildResponse(Request request) {
        for(ResponseSubsystem subsystem:_subsystems){
            if(subsystem.shouldHandle(request)){
                return subsystem.buildResponse(request);
            }
        }
        return new NotAllowedResponse();

    }



}
