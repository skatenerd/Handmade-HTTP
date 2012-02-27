import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/25/12
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class MostBasicResponseTest {
    @Test
    public void writesHeaderAndBodyToStream()
    throws IOException {

        OutputStream stream=new ByteArrayOutputStream();
        Response response = new MostBasicResponse(stream);
        response.writeResponse();

        StringBuilder headerStringBuilder=new StringBuilder();
        for(String line:response.getHeader()){
            headerStringBuilder.append(line+"\n");
        }
        String headerString = headerStringBuilder.toString();

        String desiredResponse=(headerString +"\n"+ new String(response.getBody()));

        assertEquals(desiredResponse, stream.toString());
    }

    @Test
    public void createsValidHeader()
    throws IOException{
        String [] files = {};
        Response response = new MostBasicResponse(new ByteArrayOutputStream());
        String lengthString= Integer.toString(response.contentLength());


        List<String> header=new ArrayList<String>();
        header.add("HTTP/1.1 200 OK");
        header.add("Connection: close");
        header.add("Content-Type: text/plain");
        header.add("Content-Length: "+lengthString);

        List<String> actualHeader=response.getHeader();

        assertEquals(header,actualHeader);


    }
}
