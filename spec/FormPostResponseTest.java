import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/26/12
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormPostResponseTest {
    @Test
    public void responseBody()
    throws FileNotFoundException {
        Request mockRequest = new MockRequest("post","/form","fizz=74".getBytes(),false,true);

        MockMarkupGenerator mockMarkupGenerator=new MockMarkupGenerator();

        FormPostResponse response = new FormPostResponse(mockRequest, mockMarkupGenerator);

        response.getBody();
        assertEquals("displayForm", mockMarkupGenerator.calls.get(0));
        assertEquals("74",mockMarkupGenerator.lastFormData.get("fizz"));
        assertEquals("200 OK",response.status());
        assertEquals("text/html",response.contentType());
    }
}
