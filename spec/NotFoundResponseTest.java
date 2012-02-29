import org.junit.Test;
import java.io.*;
import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotFoundResponseTest {
    @Test
    public void contentLength()
    throws IOException{
        Response notFound=new NotFoundResponse();
        byte [] body=notFound.getBody();
        assertEquals(body.length, notFound.contentLength());
        assertEquals("404 Not Found",notFound.status());
        assertEquals("text/html",notFound.contentType());
    }
}
