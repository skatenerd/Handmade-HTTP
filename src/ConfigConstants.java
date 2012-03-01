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
    public static int concurrentRequests=1000;

    public static List<ResponseSubsystem> getDefaultSubsystems(String root, int port){
        List<ResponseSubsystem> subsystems=new ArrayList<ResponseSubsystem>();
        subsystems.add(new FormRequestSubsystem());
        subsystems.add(new PingSubsystem());
        subsystems.add(new FileServerSubsystem(new FileBrowserImpl(root),port));
        return subsystems;
    }

    
}
