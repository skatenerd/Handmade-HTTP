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
    List<ResponseSubsystem> _subsystems;
    public ResponseFactory(List<ResponseSubsystem> subsystemList) {
        _subsystems=subsystemList;
    }

    public Response buildResponse(Request request, OutputStream stream){
        return buildResponse(request, new ArrayList<ResponseSubsystem>());
    }
    
    public Response buildResponse(Request request, List<ResponseSubsystem> subsystems) {

        subsystems.add(new MalformedRequestSubsystem());
        subsystems.add(new FormRequestSubsystem());
        subsystems.add(new PingSubsystem());
        subsystems.add(new FileServerSubsystem(new FileBrowserImpl(ConfigConstants.root)));

        for(ResponseSubsystem subsystem:subsystems){
            if(subsystem.shouldHandle(request)){
                return subsystem.buildResponse(request);
            }

        }
        return new NotAllowedResponse();

    }



}
