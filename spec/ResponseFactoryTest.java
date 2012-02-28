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
    public void buildsFormPostResponses(){
        String [] files={};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        ResponseFactory factory=new ResponseFactory();
        Request mockRequest=new MockRequest("POST",ConfigConstants.formLocation,"".getBytes());
        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream(), mockBrowser);
        assertEquals(FormPostResponse.class, response.getClass());
    }

    @Test
    public void buildsFormGetResponses(){
        String [] files={};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        ResponseFactory factory=new ResponseFactory();
        Request mockRequest=new MockRequest("GET",ConfigConstants.formLocation,"".getBytes());
        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream(), mockBrowser);
        assertEquals(FormGetResponse.class, response.getClass());
    }

    @Test
    public void buildsNotAllowedResponses(){
        String [] files={};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        ResponseFactory factory=new ResponseFactory();
        Request randomPost=new MockRequest("POST","/fizz/buzz","".getBytes());
        Response postResponse=factory.buildResponse(randomPost, new ByteArrayOutputStream(), mockBrowser);
        assertEquals(NotAllowedResponse.class, postResponse.getClass());

        Request randomPut=new MockRequest("POST","/fizz/buzz","".getBytes());
        Response putResponse=factory.buildResponse(randomPut, new ByteArrayOutputStream(), mockBrowser);
        assertEquals(NotAllowedResponse.class, putResponse.getClass());
    }


    @Test
    public void buildsPingResponses(){
        String [] files={};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        ResponseFactory factory=new ResponseFactory();
        Request pingRequest=new MockRequest("GET", ConfigConstants.pingLocation, "".getBytes());
        Response pingResponse=factory.buildResponse(pingRequest,new ByteArrayOutputStream(), mockBrowser);
        assertEquals(PingResponse.class,pingResponse.getClass());

    }


    
}
