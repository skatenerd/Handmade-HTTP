import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestHandler {
    Socket _socket;
    public RequestHandler(Socket socket){
        _socket=socket;
    }
    
    public void handleResponse()
    throws IOException{
        FileBrowser browser=new FileBrowserImpl("/Users");
        Request request=RequestFactory.BuildRequest(_socket.getInputStream());
        Response response=new DirectoryListReponse(request,
                                                   browser,
                                                   getServerSocketOutputStream(_socket),
                                                   new MarkupGeneratorImpl());
        response.writeResponse();
        _socket.close();
    }

    private OutputStream getServerSocketOutputStream(Socket socket)
            throws IOException {
        return socket.getOutputStream();
    }
}