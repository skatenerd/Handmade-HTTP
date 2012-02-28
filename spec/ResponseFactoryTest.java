import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseFactoryTest {

    @Test
    public void buildsPingResponses(){
        String [] files={};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        ResponseFactory factory=new ResponseFactory();
        Request pingRequest=new MockRequest("GET", ConfigConstants.pingLocation, "".getBytes(),false,true);
        Response pingResponse=factory.buildResponse(pingRequest,new ByteArrayOutputStream(), mockBrowser);
        assertEquals(PingResponse.class,pingResponse.getClass());

    }


    
}
