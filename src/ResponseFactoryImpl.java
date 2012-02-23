import java.io.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseFactoryImpl implements ResponseFactory{
    public ResponseFactoryImpl(){

    }
    
    public Response buildResponse(Request request, OutputStream stream, FileBrowser browser){
        Response response=null;
        MarkupGenerator generator=new MarkupGeneratorImpl();
        if(browser.isDirectory(request.get_path())){
         response=new DirectoryListReponse(request,
                                           browser,
                                           stream,
                                           generator);
        }else{
            System.out.println(request.get_path());
            response=new NotFoundResponse(stream,generator);
        }
        
        return response;
    }
    
    
}
