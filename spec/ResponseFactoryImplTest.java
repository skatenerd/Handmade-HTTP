import com.sun.imageio.spi.InputStreamImageInputStreamSpi;
import org.junit.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseFactoryImplTest {
    @Test
    public void buildsDirectoryResponses(){
        String path="/";
        String [] files={"foo","bar"};
        FileBrowser mockBrowser=new MockFileBrowser(path,files);
        ResponseFactory factory=new ResponseFactoryImpl();
        List<String> links=new ArrayList<String>();
        links.add("fizz");
        Request mockRequest=new MockRequest("GET",path,"".getBytes());
        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream(), mockBrowser);
        assertEquals(DirectoryListReponse.class, response.getClass());
    }

    @Test
    public void buildsNotFoundResponses(){
        String path="/Users";
        String [] files={"foo","bar"};
        String wrongPath="/foo/bizz/shabang.sdfljwef";
        FileBrowser mockBrowser=new MockFileBrowser("/",files);
        ResponseFactory factory=new ResponseFactoryImpl();
        List<String> links=new ArrayList<String>();
        links.add("fizz");
        Request mockRequest=new MockRequest("GET",wrongPath,"fnorb".getBytes());
        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream(), mockBrowser);
        assertEquals(response.getClass(), NotFoundResponse.class);
    }

    @Test
    public void buildsFileResponses(){
        String rootPath="/";
        String [] files={"foo.pdf","bar.jpg"};
        String filePath= rootPath+files[0];
        FileBrowser mockBrowser=new MockFileBrowser(rootPath,files);

        ResponseFactory factory=new ResponseFactoryImpl();

        List<String> links=new ArrayList<String>();
        links.add("fizz");

        Request mockRequest=new MockRequest("GET",filePath,"".getBytes());

        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream(), mockBrowser);

        assertEquals(FileResponse.class, response.getClass());
    }

    @Test
    public void buildsBadRequestResponses(){
        String [] files={};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        ResponseFactory factory=new ResponseFactoryImpl();
        Request mockRequest=new MockRequest(null,"/some/crazy/bad/form/path","".getBytes());
        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream(), mockBrowser);
        assertEquals(BadRequestResponse.class, response.getClass());
    }

    @Test
    public void buildsFormPostResponses(){
        String [] files={};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        ResponseFactory factory=new ResponseFactoryImpl();
        Request mockRequest=new MockRequest("POST",ConfigConstants.formLocation,"".getBytes());
        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream(), mockBrowser);
        assertEquals(FormPostResponse.class, response.getClass());
    }

    @Test
    public void buildsFormGetResponses(){
        String [] files={};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        ResponseFactory factory=new ResponseFactoryImpl();
        Request mockRequest=new MockRequest("GET",ConfigConstants.formLocation,"".getBytes());
        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream(), mockBrowser);
        assertEquals(FormGetResponse.class, response.getClass());
    }

    @Test
    public void buildsNotAllowedResponses(){
        String [] files={};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        ResponseFactory factory=new ResponseFactoryImpl();
        Request randomPost=new MockRequest("POST","/fizz/buzz","".getBytes());
        Response postResponse=factory.buildResponse(randomPost, new ByteArrayOutputStream(), mockBrowser);
        assertEquals(NotAllowedResponse.class, postResponse.getClass());

        Request randomPut=new MockRequest("POST","/fizz/buzz","".getBytes());
        Response putResponse=factory.buildResponse(randomPut, new ByteArrayOutputStream(), mockBrowser);
        assertEquals(NotAllowedResponse.class, putResponse.getClass());
    }

    @Test
    public void buildsTimeoutResponses(){
        String [] files={};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        ResponseFactory factory=new ResponseFactoryImpl();
        Request timeoutRequest=new MockRequest("POST","/bbb","".getBytes(),true);
        
        Response timeoutResponse=factory.buildResponse(timeoutRequest,new ByteArrayOutputStream(),mockBrowser);
        assertEquals(TimeoutResponse.class,timeoutResponse.getClass());
    }

    @Test
    public void buildsPingResponses(){
        String [] files={};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        ResponseFactory factory=new ResponseFactoryImpl();
        Request pingRequest=new MockRequest("GET", ConfigConstants.pingLocation, "".getBytes());
        Response pingResponse=factory.buildResponse(pingRequest,new ByteArrayOutputStream(), mockBrowser);
        assertEquals(PingResponse.class,pingResponse.getClass());

    }


    
}
