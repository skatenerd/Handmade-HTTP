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
public class RequestHandler extends Thread{
    Socket _socket;
    public RequestHandler(Socket socket){
        _socket=socket;
    }
    
    public void run(){
        try{
            handleResponse();
        }catch (IOException e){
            System.out.println("Exception in response handler");
            e.printStackTrace();
        }
            
    }
    
    public void handleResponse()
    throws IOException{
        _socket.setSoTimeout(200);
        Request request=new RequestImpl(_socket.getInputStream());
        ResponseFactory factory=new ResponseFactoryImpl();
        OutputStream stream=_socket.getOutputStream();
        Response response= factory.buildResponse(request, stream, new FileBrowserImpl(ConfigConstants.root));
        response.writeResponse();
        _socket.close();
    }

}
