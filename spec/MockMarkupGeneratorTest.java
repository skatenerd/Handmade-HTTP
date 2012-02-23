import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockMarkupGeneratorTest {
    @Test
    public void recordsCalls(){
        MockMarkupGenerator mock=new MockMarkupGenerator();
        List<String> args=new ArrayList<String>();
        args.add("foo");
        args.add("bar");
        mock.pageWithLinks(args);
        
        assertEquals("pageWithLinks", mock.calls.get(0));
        assertEquals(args, mock.args.get(0));

    }
}
