import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
        Response response=new Response(request, browser, getServerSocketOutputStream(_socket));
        response.writeResponse();
        _socket.close();
    }

    private PrintStreamWriteable getServerSocketOutputStream(Socket socket)
            throws IOException {
        return new PrintStreamWriteable(new PrintStream(socket.getOutputStream()));
    }
}
