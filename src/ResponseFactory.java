import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseFactory {
    public ResponseFactory() {

    }

    public Response buildResponse(Request request, OutputStream stream) {
        ResponseSubsystem malformed=new MalformedRequestSubsystem();
        ResponseSubsystem form=new FormRequestSubsystem();
        ResponseSubsystem ping=new PingSubsystem();
        ResponseSubsystem file=new FileServerSubsystem(new FileBrowserImpl(ConfigConstants.root));

        if(malformed.shouldHandle(request)){
            return malformed.buildResponse(request, stream);
        } else if(form.shouldHandle(request)){
            return form.buildResponse(request, stream);
        } else if(ping.shouldHandle(request)){
            return ping.buildResponse(request,stream);
        } else if (file.shouldHandle(request)){
            return file.buildResponse(request,stream);
        } else {
            return new NotAllowedResponse(stream);
        }
    }



}
