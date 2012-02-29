import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestHandler extends Thread {
    Socket _socket;
    List<ResponseSubsystem> _subsystems;

    public RequestHandler(Socket socket, List<ResponseSubsystem> subsystems) {
        _socket = socket;
        _subsystems = subsystems;
    }

    public void run() {
        try {
            handleRequest();
        } catch (IOException e) {
            System.out.println("Exception in response handler");
        }
    }

    public void handleRequest()
            throws IOException {
        try{
            _socket.setSoTimeout(200);
            Request request = new RequestImpl(_socket.getInputStream());
            ResponseFactory factory = new ResponseFactory();
            OutputStream stream = _socket.getOutputStream();
            Response response = factory.buildResponse(request,_subsystems);
            response.writeResponse(stream);
        }catch (IOException e){
            throw e;
        }finally{
            _socket.close();
        }
    }

}
