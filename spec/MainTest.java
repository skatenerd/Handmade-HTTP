import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/26/12
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainTest {
    @Test
    public void setsGlobalVariables()
    throws IOException{
        Main.setGlobals("/fizz/buzz",8022);
        assertEquals(8022,ConfigConstants.port);
        assertEquals("/fizz/buzz",ConfigConstants.root);
    }
    
}
