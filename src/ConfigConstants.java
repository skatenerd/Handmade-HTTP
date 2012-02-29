import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/25/12
 * Time: 5:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConfigConstants {
    public static String formLocation = "/form";
    public static String pingLocation= "/ping";
    public static String[] inputs = {"fizz", "buzz", "foo"};
    public static String root = "";
    public static int port;

    public static List<ResponseSubsystem> getDefaultSubsystems(){
        List<ResponseSubsystem> subsystems=new ArrayList<ResponseSubsystem>();
        subsystems.add(new MalformedRequestSubsystem());
        subsystems.add(new FormRequestSubsystem());
        subsystems.add(new PingSubsystem());
        subsystems.add(new FileServerSubsystem(new FileBrowserImpl(ConfigConstants.root)));
        return subsystems;
    }
    
}
