import java.io.IOException;
import java.io.*;
import java.net.*;


/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/20/12
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server
extends Thread{
    private boolean keepRunning=true;
    private int _port;
    public String lastRequestType;
    private ServerSocket _serverSocket;

    public static void main(String [] args)
    throws InterruptedException{
        Server foo = new Server(8080);
        foo.start();
        Thread.sleep(15000);
        foo.kill();
    }


    public Server (int port){
        _port=port;
    }

    public void run()
    {
        try{
            _serverSocket = new ServerSocket(_port);
            while (keepRunning){
                Socket connection=_serverSocket.accept();
                RequestHandler handler=new RequestHandler(connection);
                handler.handleResponse();
                connection=null;
            }
        }
        catch (SocketException e){
            System.out.println("swallowing expected shutdown exception");
        }
        catch (IOException e){
            System.out.println("exception in main server loop");
            e.printStackTrace();
        }
        finally{
            closeServerSocket();
        }

        }


    public void kill(){
        System.out.println(getState());
        keepRunning=false;
        closeServerSocket();
    }
    

    private void closeServerSocket(){
        try{
            if(_serverSocket != null && !_serverSocket.isClosed()){
                _serverSocket.close();
            }
        }
        catch (IOException e){
            System.out.println("IOException closing server socket");
            e.printStackTrace();
        }

    }
    



}
