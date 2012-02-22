import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/21/12
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockRequest implements Request{
    String _requestType;
    String _path;
    List<String> _body;
            
    public MockRequest(String requestType, String path, List<String> body){
        _requestType = requestType;
        _path=path;
        _body=new ArrayList<String>();
        _body.addAll(body);
    }


    public String getPath(){
        return _path;
    }

    public String getRequestType(){
        return _requestType;
    }

    public List<String> getBody(){
        return _body;
    }
}
