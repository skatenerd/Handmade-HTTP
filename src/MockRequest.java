import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/21/12
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockRequest implements Request {
    String _requestType;
    String _path;
    byte[] _body;
    List<String> _header;
    boolean _timedOut;

    public MockRequest(String requestType, String path, byte[] body) {
        _requestType = requestType;
        _path = path;
        _body = body;
        _timedOut = false;
    }

    public MockRequest(String requestType, String path, byte[] body, boolean timedOut) {
        new MockRequest(requestType, path, body);
        _timedOut = timedOut;
    }


    public String get_path() {
        return _path;
    }

    public String get_RequestType() {
        return _requestType;
    }

    public byte[] get_Body() {
        return _body;
    }

    public boolean requestTypeSupplied() {
        return _requestType != null;
    }

    public boolean pathSupplied() {
        return _path != null;
    }

    public boolean timedOut() {
        return _timedOut;
    }


}
