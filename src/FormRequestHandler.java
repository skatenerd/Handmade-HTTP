import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormRequestHandler implements ResponseSubsystem {
    public FormRequestHandler(){}
    public Response buildResponse(Request request, OutputStream stream){
        if(request.get_requestType().equalsIgnoreCase("GET")){
            return new FormGetResponse(stream,new MarkupGeneratorImpl());
        }else if(request.get_requestType().equalsIgnoreCase("POST")){
            return new FormPostResponse(stream, request, new MarkupGeneratorImpl());
        }else{
            throw new NoSuchMethodError();
        }
    }
    public boolean shouldHandle(Request request){
        return false;
    }

}
