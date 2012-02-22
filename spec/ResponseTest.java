import org.junit.*;
import java.io.File;
import static org.junit.Assert.*;
import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/21/12
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseTest {
//    @Test
//    public void listsDirectory(){
//        File dir = new File("/Users/8thlight/Programs");
//        String [] foo = dir.list();
//        for (String f:foo){
//            System.out.println(f);
//        }
//    }
    @Test
    public void canListFilesInDirectory(){
        String toList="/bin/fizz/to/list";
        String [] files = {"PPP.pdf","PPP.txt","C#.txt","pirated_cartoons"};
        List<String> body = new ArrayList<String>();
        FileBrowser mockBrowser=new MockFileBrowser(toList,files);
        Request mockRequest = new MockRequest("UBERPOST",toList,body);
        Response response = new Response(mockRequest,mockBrowser);
        assertArrayEquals(files,response.listFiles());
    }
    
    @Test
    public void statusLineForValidFolderPath(){
        String toList = "/fizz/buzz";
        String [] files = {};
        List<String> body=new ArrayList<String>();
        FileBrowser mockBrowser=new MockFileBrowser(toList, files);
        Request mockRequest = new MockRequest("UBERGET","/fizz/buzz",body);
        Response response=new Response(mockRequest,mockBrowser);
        
        assertEquals("HTTP/1.1 200 OK", response.statusLine());
        
        
        
    }
    @Test
    public void statusCodeForInvalidFolderPath(){
        String toList = "";
        String [] files = {};
        List<String> body=new ArrayList<String>();
        FileBrowser mockBrowser=new MockFileBrowser(toList, files);
        Request mockRequest = new MockRequest("UBERGET","/fizz/buzz",body);
        Response response=new Response(mockRequest,mockBrowser);

        assertEquals("HTTP/1.1 404 Not Found", response.statusLine());
    }

    @Test
    public void responseBodyForValidFolderPath(){
        String toList="/bin/fizz/to/list";
        String [] files = {"PPP.pdf","PPP.txt","C#.txt","pirated_cartoons"};
        List<String> body = new ArrayList<String>();
        FileBrowser mockBrowser=new MockFileBrowser(toList,files);
        Request mockRequest = new MockRequest("UBERPOST",toList,body);
        Response response = new Response(mockRequest,mockBrowser);
                 
        List<String> desiredResponse = new ArrayList<String>();
        desiredResponse.add("HTTP/1.1 200 OK");
        desiredResponse.add("Connection: close");
        desiredResponse.add("Content-Type: text");

        desiredResponse.add("");
        for (String file:files){
            desiredResponse.add(file);
        }
        assertArrayEquals(desiredResponse.toArray(), response.response().toArray());
        
                
    }
}
