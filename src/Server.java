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
        Thread.sleep(10000);
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
                PrintStream output = getServerSocketOutputStream(connection);
                BufferedReader input = getServerSocketInputStream(connection);
                String readString;
//                while(true){
//                    readString=input.readLine();
//                    if(readString.trim().equals("")){
//                        break;
//                    }
//                    System.out.println(readString);
//                    output.println(readString);
//                }
                output.println("foo");
                System.out.println("closing");
                connection.close();
                connection=null;
            }
        }
        catch (SocketException e){
            System.out.println("swallowing expected exception");
        }
        catch (IOException e){
            System.out.println("exception in main server loop");
            e.printStackTrace();
        }
        finally{
            closeServerSocket();
        }

        }

    
    private PrintStream getServerSocketOutputStream(Socket socket)
    throws IOException{
        return new PrintStream(socket.getOutputStream());
    }
    
    private BufferedReader getServerSocketInputStream(Socket socket)
    throws IOException{
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
