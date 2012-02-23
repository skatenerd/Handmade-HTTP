import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/21/12
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockRequestTest {
    @Test
    public void constructor(){
        String bodyString="foo\nbar\nsoymilk\nfree-range eggs";
        byte [] body = bodyString.getBytes();
        MockRequest mock=new MockRequest("SUPERGET","/path/foo.jpg",body);
        assertEquals("SUPERGET",mock.get_RequestType());
        assertEquals("/path/foo.jpg",mock.get_path());
        assertEquals(body,mock.get_Body());
    }
}
