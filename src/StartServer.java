import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/24/12
 * Time: 10:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class StartServer {
    public static void main(String[] args)
            throws IOException {
        CommandLineParser parser = new CommandLineParser(args, new FileBrowserImpl());
        if (parser.isValidInput()) {
            startServer(parser.port(), parser.path(), ConfigConstants.getDefaultSubsystems(parser.path(), parser.port()));
        } else {
            System.out.println("Invalid inputs please try again");
        }
    }
    public static void startServer(int port, String path,List<ResponseSubsystem> subsystems)
    throws IOException {
        Server server = new Server(path,port,subsystems);
        server.start();
        System.out.println("Press any key to kill");
        System.in.read();
        server.kill();
    }

}
