import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileServerSubsystem implements ResponseSubsystem{
    FileBrowser _browser;

    public FileServerSubsystem(FileBrowser browser){
        _browser = browser;
    }

    public Response buildResponse(Request request, OutputStream stream){
        if(_browser.isDirectory(request.get_path())){
            return new DirectoryListReponse(request, _browser,stream, new MarkupGeneratorImpl());
        }else if(_browser.isFile(request.get_path())){
            return new FileResponse(request, _browser, stream);
        }else{
            return new NotFoundResponse(stream);
        }
    }
    
    public boolean shouldHandle(Request request){
        return request.requestTypeSupplied() && request.get_requestType().equalsIgnoreCase("GET");
    }
}