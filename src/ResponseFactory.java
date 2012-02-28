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

    public Response buildResponse(Request request, OutputStream stream, FileBrowser browser) {
        ResponseSubsystem malformed=new MalformedRequestResponder();
        ResponseSubsystem form=new FormRequestHandler();
        if(malformed.shouldHandle(request)){
            return malformed.buildResponse(request, stream);
        } else if(form.shouldHandle(request)){
            return form.buildResponse(request, stream);
        } else if (request.get_requestType().equalsIgnoreCase("GET")) {
            return handleGetResponse(request, stream, browser);
        } else if (request.get_requestType().equalsIgnoreCase("POST")) {
            return handlePostResponse(request, stream, browser);
        } else {
            return new NotAllowedResponse(stream);
        }
    }

    private Response handlePostResponse(Request request, OutputStream stream, FileBrowser browser) {
        if (request.get_path().equals(ConfigConstants.formLocation)) {
            return new FormPostResponse(stream, request, new MarkupGeneratorImpl());
        } else {
            return new NotAllowedResponse(stream);
        }
    }

    private Response handleGetResponse(Request request, OutputStream stream, FileBrowser browser) {
        MarkupGenerator generator = new MarkupGeneratorImpl();
        if (request.pathSupplied() && request.get_path().equals(ConfigConstants.formLocation)) {
            return new FormGetResponse(stream, generator);
        } else if (request.pathSupplied() && request.get_path().equals(ConfigConstants.pingLocation)){
            return new PingResponse(stream);
        } else {
            FileServerResponder responder=new FileServerResponder(browser);
            return responder.buildResponse(request,stream);
        }
    }


}
