import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/24/12
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class HeaderParserTest {
    @Test
    public void parsesPathAndRequstType(){
        List<String>header=new ArrayList<String>();
        header.add("GET /a/b%20c%20d/e HTTP/1.1");
        header.add("Content-Type: joke");
        assertEquals("/a/b c d/e", HeaderParser.path(header));
        assertFalse(HeaderParser.contentLengthSupplied(HeaderParser.contentLength(header)));
        assertTrue(HeaderParser.validRequestType(HeaderParser.requestType(header)));
        assertTrue(HeaderParser.pathSupplied(HeaderParser.path(header)));
    }
    
    @Test
    public void parsesContentLength(){
        List<String>header=new ArrayList<String>();
        header.add("GET /a/b%20c%20d/e HTTP/1.1");
        header.add("Content-Type: joke");
        header.add("Content-Length: 74");
        assertEquals(74, HeaderParser.contentLength(header));
        assertTrue(HeaderParser.contentLengthSupplied(HeaderParser.contentLength(header)));
    }
    
    @Test
    public void parsesAcceptEncoding(){
        List<String>header=new ArrayList<String>();
        header.add("GET /a/b%20c%20d/e HTTP/1.1");
        header.add("Content-Type: joke");
        header.add("Accept-Encoding: gzip, overfishing");
    }
    
    
}
