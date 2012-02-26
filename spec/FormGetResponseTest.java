import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/26/12
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormGetResponseTest {
    @Test
    public void responseBody()
            throws FileNotFoundException {
        String [] files = {};
        OutputStream stream=new ByteArrayOutputStream();
        MockMarkupGenerator mockMarkupGenerator=new MockMarkupGenerator();
        FormGetResponse response = new FormGetResponse(stream,mockMarkupGenerator);

        response.getBody();
        assertEquals("submitForm", mockMarkupGenerator.calls.get(0));
        assertEquals("200 OK",response.status());
        assertEquals("text/html",response.contentType());

    }
}

