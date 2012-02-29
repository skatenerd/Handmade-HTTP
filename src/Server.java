import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/20/12
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server
        extends Thread {

    private ServerSocket _serverSocket;
    private String _path;
    private int _port;
    private List<ResponseSubsystem> _subsystems;

    public Server(String path,int port,List<ResponseSubsystem> subsystems){
        _path = path;
        _port = port;
        _subsystems = subsystems;
    }


    public void run() {
        try {
            _serverSocket = new ServerSocket(_port,ConfigConstants.concurrentRequests);
            while (true) {
                Socket connection = _serverSocket.accept();
                RequestHandler handler = new RequestHandler(connection,_subsystems);
                handler.start();
            }
        } catch (BindException e) {
            System.out.println("Address In Use!");
        } catch (SocketException e) {
            System.out.println("swallowing expected shutdown exception");
        } catch (IOException e) {
            System.out.println("Unknown Exception!");
            e.printStackTrace();
        } finally {
            closeServerSocket();
        }

    }


    public void kill() {
        closeServerSocket();
    }


    private void closeServerSocket() {
        try {
            if (_serverSocket != null && !_serverSocket.isClosed()) {
                _serverSocket.close();
            }
        } catch (IOException e) {
            System.out.println("IOException when closing server socket");
            e.printStackTrace();
        }

    }


}
