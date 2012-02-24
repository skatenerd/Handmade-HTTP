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
    String _acceptEncoding;
    byte [] _body;
    List<String> _header;
            
    public MockRequest(String requestType, String path, byte [] body, String acceptEncoding){
        _requestType = requestType;
        _path=path;
        _body=body;
        _acceptEncoding=acceptEncoding;
    }
    
    public String get_acceptEncoding(){
        return _acceptEncoding;
    }

    public String get_path(){
        return _path;
    }

    public String get_RequestType(){
        return _requestType;
    }

    public byte [] get_Body(){
        return _body;
    }

}
