import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/26/12
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommandLineParserTest {
    String [] files={};
    FileBrowser mockBrowser=new MockFileBrowser("/Users/8thlight",files);
    
    @Test
    public void parsesCommandLineInput(){
        String [] args={"-p","8022","-d","/Users/8thlight"};
        CommandLineParser parser=new CommandLineParser(args,mockBrowser);
        assertEquals(8022,parser.port());
        assertEquals("/Users/8thlight",parser.path());
    }

    @Test
    public void parsesJunkPort(){
        String [] args={"-p","fizz","-d","/Users/8thlight"};
        CommandLineParser parser=new CommandLineParser(args,mockBrowser);
        assertFalse(parser.isValidInput());
    }

    @Test
    public void parsesJunkPath(){
        String [] args={"-p","8022","-d","nuclear war is terrible"};
        CommandLineParser parser=new CommandLineParser(args,mockBrowser);
        assertFalse(parser.isValidInput());
    }
    
    
}
