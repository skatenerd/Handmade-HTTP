import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormRequestSubsystem implements ResponseSubsystem {
    public FormRequestSubsystem(){}
    public Response buildResponse(Request request){
        if(request.get_requestType().equalsIgnoreCase("GET")){
            return new FormGetResponse(new MarkupGeneratorImpl());
        }else if(request.get_requestType().equalsIgnoreCase("POST")){
            return new FormPostResponse(request, new MarkupGeneratorImpl());
        }else{
            throw new NoSuchMethodError();
        }
    }
    public boolean shouldHandle(Request request){
        return request.pathSupplied() && request.get_path().equals(ConfigConstants.formLocation);
    }

}
