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
        if(!request.requestTypeSupplied()){
            return new NotFoundResponse(stream, new MarkupGeneratorImpl());
        }else if (request.get_RequestType().equals("GET")){
            return handleGetResponse(request, stream, browser);
        }else if (request.get_RequestType().equals("POST")){
            return handlePostResponse(request,stream,browser);
        }else{
            return handleGetResponse(request, stream, browser);
        }
    }
    
    private Response handlePostResponse(Request request, OutputStream stream, FileBrowser browser){
        if(request.get_path().equals(ConfigConstants.formLocation)){
            return new FormPostResponse(stream,request,new MarkupGeneratorImpl());
        }else{
            return new BadRequestResponse(stream);
        }
    }
    
    private Response handleGetResponse(Request request, OutputStream stream, FileBrowser browser){
        MarkupGenerator generator=new MarkupGeneratorImpl();
        if(browser.isDirectory(request.get_path())){
            return new DirectoryListReponse(request,
                    browser,
                    stream,
                    generator);
        }else if(browser.isFile(request.get_path())){
            return new FileResponse(request,
                    browser,
                    stream);

        }else if(request.get_path()!=null && request.get_path().equals(ConfigConstants.formLocation)){
            return new FormGetResponse(stream,generator);
        }
        else{
            System.out.println(request.get_path());
            return new NotFoundResponse(stream,generator);
        }
    }
    
    
}
