import java.io.IOException;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/24/12
 * Time: 10:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String [] args)
    throws IOException{
        Server server=new Server(8080);
        server.start();
        System.out.println("Press any key to kill");
        System.in.read();
        server.kill();
        

    }
}
