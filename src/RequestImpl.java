import java.net.SocketTimeoutException;
import java.util.*;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/21/12
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestImpl implements Request {


    private static byte[] _default_body = new byte[0];


    private String _requestType;
    private String _path;
    private List<String> _header;
    private byte[] _body;
    private int _contentLength;
    private boolean _timeout;


    public RequestImpl(InputStream request)
            throws IOException {
        _header = extractHeaderAndSetTimeoutBool(request);
        _requestType = HeaderParser.requestType(_header);
        _path = HeaderParser.path(_header);
        _contentLength = HeaderParser.contentLength(_header);
        _body = extractBody(request, _contentLength);

    }

    public List<String> extractHeaderAndSetTimeoutBool(InputStream stream)
            throws IOException {
        _timeout = false;
        LineReader reader = new LineReader(stream);
        List<String> rtnList = new ArrayList<String>();
        String curString;
        try {
            while ((curString = reader.readLine().trim()).length() > 0) {
                rtnList.add(curString);
            }
        } catch (SocketTimeoutException e) {
            _timeout = true;
        }
        return rtnList;

    }

    public byte[] extractBody(InputStream stream, int contentLength)
            throws IOException {
        byte[] body = _default_body;
        if (contentLengthSupplied()) {
            body = new byte[contentLength];
            stream.read(body);
        }

        return body;
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

    public List<String> get_header() {
        List<String> rtn = new ArrayList<String>();
        rtn.addAll(_header);
        return rtn;
    }

    public int get_ContentLength() {
        return _contentLength;
    }

    public boolean contentLengthSupplied() {
        return HeaderParser.contentLengthSupplied(_contentLength);
    }

    public boolean requestTypeSupplied() {
        return HeaderParser.validRequestType(_requestType);
    }

    public boolean pathSupplied() {
        return HeaderParser.pathSupplied(_path);
    }

    public boolean timedOut() {
        return _timeout;
    }

}
