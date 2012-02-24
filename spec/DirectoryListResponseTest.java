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

        Request mockRequest = new MockRequest("UBERPOST",toList,new byte[0]);

        OutputStream stream=new ByteArrayOutputStream();
        MockMarkupGenerator mockMarkupGenerator=new MockMarkupGenerator();
        DirectoryListReponse response = new DirectoryListReponse(mockRequest,mockBrowser, stream, mockMarkupGenerator);
        List<String> urls=response.listFileURLS();
        response.getBody();


        assertEquals("pageWithLinks", mockMarkupGenerator.calls.get(0));
        assertEquals(urls,mockMarkupGenerator.args.get(0));

        
    }

    @Test
    public void generatesURLs(){
        //implement me!
    }

    @Test
    public void statusLineForValidFolderPath(){
        String toList = "/fizz/buzz";
        String [] files = {};
        FileBrowser mockBrowser=new MockFileBrowser(toList, files);

        Request mockRequest = new MockRequest("UBERGET","/fizz/buzz",new byte[0]);
        OutputStream stream=new ByteArrayOutputStream();
        Response response = new DirectoryListReponse(mockRequest,mockBrowser, stream, new MockMarkupGenerator());

        assertEquals("200 OK", response.status());
    }

    @Test
    public void statusCodeForInvalidFolderPath() throws IOException{
        String toList = "";
        String [] files = {};
        boolean thrown=false;
        FileBrowser mockBrowser=new MockFileBrowser(toList, files);

        Request mockRequest = new MockRequest("UBERGET","/fizz/buzz",new byte[0]);
        OutputStream stream=new ByteArrayOutputStream();
        Response response = new DirectoryListReponse(mockRequest,mockBrowser, stream, new MockMarkupGenerator());
        try{
        response.getBody();}
        catch(FileNotFoundException e){
            thrown=true;
        }finally{
            assertTrue(thrown);
        }
        

    }
    
    @Test
    public void totalResponse()
    throws IOException{
        String toList="/bin/fizz/to/list";
        String [] files = {"PPP.pdf","PPP.txt","C#.txt","pirated_cartoons"};
        FileBrowser mockBrowser=new MockFileBrowser(toList,files);
        Request mockRequest = new MockRequest("UBERPOST",toList,new byte[0]);
        OutputStream stream=new ByteArrayOutputStream();
        Response response = new DirectoryListReponse(mockRequest,mockBrowser, stream, new MockMarkupGenerator());
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
    public void responseHeaderForValidFolderPath()
    throws IOException{
        String toList="/bin/fizz/to/list";
        String [] files = {"PPP.pdf","PPP.txt","C#.txt","pirated_cartoons"};
        FileBrowser mockBrowser=new MockFileBrowser(toList,files);
        Request mockRequest = new MockRequest("UBERPOST",toList,new byte[0]);
        Response response = new DirectoryListReponse(mockRequest,mockBrowser, new ByteArrayOutputStream(), new MockMarkupGenerator());
        String lengthString= new Integer(response.contentLength()).toString();
        

        List<String> header=new ArrayList<String>();
        header.add("HTTP/1.1 200 OK");
        header.add("Connection: close");
        header.add("Content-Type: text");
        header.add("Content-Length: "+lengthString);
               
        List<String> actualHeader=response.getHeader();

        assertEquals(header,actualHeader);


    }
}




