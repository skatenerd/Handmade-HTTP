import org.junit.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.ByteArrayOutputStream;
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

        Request mockRequest=new MockRequest("GET",path,"".getBytes(),"*");
        
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

        Request mockRequest=new MockRequest("GET",wrongPath,"fnorb".getBytes(), "*");

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

        Request mockRequest=new MockRequest("GET",filePath,"".getBytes(), "*");

        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream(), mockBrowser);

        assertEquals(FileResponse.class, response.getClass());
    }
    
}
