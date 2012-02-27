import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseFactoryImpl implements ResponseFactory {
    public ResponseFactoryImpl() {

    }

    public Response buildResponse(Request request, OutputStream stream, FileBrowser browser) {
        if (request.timedOut()) {
            return new TimeoutResponse(stream);
        } else if (!request.requestTypeSupplied()) {
            return new BadRequestResponse(stream);
        } else if (request.get_RequestType().equalsIgnoreCase("GET")) {
            return handleGetResponse(request, stream, browser);
        } else if (request.get_RequestType().equalsIgnoreCase("POST")) {
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
        if (browser.isDirectory(request.get_path())) {
            return new DirectoryListReponse(request, browser, stream, generator);
        } else if (browser.isFile(request.get_path())) {
            return new FileResponse(request, browser, stream);
        } else if (request.pathSupplied() && request.get_path().equals(ConfigConstants.formLocation)) {
            return new FormGetResponse(stream, generator);
        } else {
            return new NotFoundResponse(stream, generator);
        }
    }


}
