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
        
        MarkupGenerator generator=new MarkupGeneratorImpl();
        List<String> links=new ArrayList<String>();
        links.add("fizz");

        Request mockRequest=new MockRequest("GET",path,generator.pageWithLinks(links).getBytes());
        
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

        MarkupGenerator generator=new MarkupGeneratorImpl();
        List<String> links=new ArrayList<String>();
        links.add("fizz");

        Request mockRequest=new MockRequest("GET",wrongPath,generator.pageWithLinks(links).getBytes());

        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream(), mockBrowser);

        assertEquals(response.getClass(), NotFoundResponse.class);
    }
    
}
