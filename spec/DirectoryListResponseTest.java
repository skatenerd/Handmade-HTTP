import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/21/12
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class DirectoryListResponseTest {
    @Test
    public void responseBody()
    throws FileNotFoundException{
        String toList="/bin/fizz/to/list";
        String [] files = {"PPP.pdf","PPP.txt","C#.txt","pirated_cartoons"};
        FileBrowser mockBrowser=new MockFileBrowser(toList,files);

        Request mockRequest = new MockRequest("UBERPOST",toList,new byte[0],false,true);

        OutputStream stream=new ByteArrayOutputStream();
        MockMarkupGenerator mockMarkupGenerator=new MockMarkupGenerator();
        DirectoryListReponse response = new DirectoryListReponse(mockRequest,mockBrowser, mockMarkupGenerator);
        List<String> urls=response.listFileURLS();
        response.getBody();

        assertTrue(urls.get(0).equals("http://localhost:"+ConfigConstants.port+"/bin/fizz/to/list/PPP.pdf"));
        assertEquals("pageWithLinks", mockMarkupGenerator.calls.get(0));
        assertEquals(urls,mockMarkupGenerator.args.get(0));

        
    }

    @Test
    public void statusLineAndContentType(){
        String toList = "/fizz/buzz";
        String [] files = {};
        FileBrowser mockBrowser=new MockFileBrowser(toList, files);

        Request mockRequest = new MockRequest("GET","/fizz/buzz",new byte[0],false,true);
        OutputStream stream=new ByteArrayOutputStream();
        Response response = new DirectoryListReponse(mockRequest,mockBrowser, new MockMarkupGenerator());

        assertEquals("200 OK", response.status());
        assertEquals("text/html",response.contentType());
    }

    @Test
    public void throwsExceptionForInvalidPath() throws IOException{
        String toList = "";
        String [] files = {};
        boolean thrown=false;
        FileBrowser mockBrowser=new MockFileBrowser(toList, files);

        Request mockRequest = new MockRequest("UBERGET","/fizz/buzz",new byte[0],false,true);
        OutputStream stream=new ByteArrayOutputStream();
        Response response = new DirectoryListReponse(mockRequest,mockBrowser, new MockMarkupGenerator());
        try{
        response.getBody();}
        catch(FileNotFoundException e){
            thrown=true;
        }finally{
            assertTrue(thrown);
        }
        

    }

}




