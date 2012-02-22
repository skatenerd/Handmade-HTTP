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
    public void handleResponse(Socket socket) {

    }

    private PrintStream getServerSocketOutputStream(Socket socket)
            throws IOException {
        return new PrintStream(socket.getOutputStream());
    }
}
