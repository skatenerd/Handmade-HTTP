import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/25/12
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestResponseTest {
    @Test
    public void totalResponse()
    throws IOException {

        OutputStream stream=new ByteArrayOutputStream();
        Response response = new TestResponse(stream);
        response.writeResponse();

        StringBuilder headerStringBuilder=new StringBuilder();
        for(String line:response.getHeader()){
            headerStringBuilder.append(line+"\n");
        }
        String headerString = headerStringBuilder.toString();

        String desiredResponse=(headerString +"\n"+ new String(response.getBody()));

        assertEquals(desiredResponse, stream.toString());
    }
}
