import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/24/12
 * Time: 10:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args)
            throws IOException {
        CommandLineParser parser = new CommandLineParser(args, new FileBrowserImpl());
        if (parser.isValidInput()) {
            setGlobals(parser.path(), parser.port());
            Server server = new Server(parser.port());
            server.start();
            System.out.println("Press any key to kill");
            System.in.read();
            server.kill();
        } else {
            System.out.println("Invalid inputs pleas try again");
        }
    }

    public static void setGlobals(String path, int port) {
        ConfigConstants.root = path;
        ConfigConstants.port = port;
    }
}
