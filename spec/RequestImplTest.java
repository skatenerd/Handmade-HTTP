import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/21/12
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestImplTest {
    String getBasic="GET / HTTP/1.1";
    String getPath="GET /foo/bar HTTP/1.1";
    String getImgPath="GET /foo/bar.img HTTP/1.1";
    String postPath="POST /path/script.cgi HTTP/1.0";


    @Test
    public void getsRequestTypeFromString(){
        assertEquals("GET", RequestImpl.requestType(getBasic));
        assertEquals("GET", RequestImpl.requestType(getPath));
        assertEquals("GET", RequestImpl.requestType(getImgPath));
        assertEquals("POST", RequestImpl.requestType(postPath));
        assertEquals(null, RequestImpl.requestType("fizz bazoo bingo frogs"));
    }
    
    @Test
    public void getsContentPathFromString(){
        assertEquals("/", RequestImpl.path(getBasic));
        assertEquals("/foo/bar.img", RequestImpl.path(getImgPath));
        assertEquals("/path/script.cgi", RequestImpl.path(postPath));
        assertEquals(null, RequestImpl.path("foo fizz quiche salad"));
    }

    @Test
    public void extractsHeader(){
        String [] requestArray={"foo",
                                "fizz",
                                "booger",
                                "pollution",
                                "",
                                "buddy",
                                "holly"};
        String [] requestHeaderArray = Arrays.copyOfRange(requestArray,0,4);

        List<String> request = Arrays.asList(requestArray);
        List<String> requestHeader = Arrays.asList(requestHeaderArray);
        
        List<String> parsedHeader= RequestImpl.getHeader(request.iterator());
        
        assertTrue(requestHeader.equals(parsedHeader));

    }

    @Test
    public void extractsHeaderAndBody(){
        String [] requestArray={"foo",
                "fizz",
                "booger",
                "pollution",
                "",
                "buddy",
                "holly"};
        String [] requestHeaderArray = Arrays.copyOfRange(requestArray,0,4);
        String [] requestBodyArray = Arrays.copyOfRange(requestArray,5,7);



        List<String> request = Arrays.asList(requestArray);
        List<String> requestHeader = Arrays.asList(requestHeaderArray);
        List<String> requestBody = Arrays.asList(requestBodyArray);

        List [] parsedBody = RequestImpl.getHeaderAndBody(request.iterator());

        assertTrue(requestBody.equals(parsedBody[1]));

    }
}
