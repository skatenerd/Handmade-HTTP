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
        String [] bodyArray={"foo", "bar", "soymilk","free-range eggs"};
        List<String> body = Arrays.asList(bodyArray);
        MockRequest mock=new MockRequest("SUPERGET","/path/to/your/password.jpg",body);
        assertEquals("SUPERGET",mock.getRequestType());
        assertEquals("/path/to/your/password.jpg",mock.getPath());
        assertEquals(body,mock.getBody());
    }
}
