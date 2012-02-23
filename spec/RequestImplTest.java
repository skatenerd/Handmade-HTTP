import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.*;
import java.io.*;
///**
// * Created by IntelliJ IDEA.
// * User: 8thlight
// * Date: 2/21/12
// * Time: 2:07 PM
// * To change this template use File | Settings | File Templates.
// */
public class RequestImplTest {
    String getBasic="GET / HTTP/1.1";
    String getPath="GET /foo/bar HTTP/1.1";
    String getImgPath="GET /foo/bar.img HTTP/1.1";
    String postPath="POST /path/script.cgi HTTP/1.0";
    
    @Test
    public void extractsHeader()
    throws IOException{
        String requestText="foo\nfizz\n\nbooger\npollution\n\nbuddy\nholly";
        byte [] requestBytes=requestText.getBytes();
        InputStream requestStream = new ByteArrayInputStream(requestBytes);

        RequestImpl request=new RequestImpl(requestStream);
        
        List<String> parsedHeader= request.get_header();
        List<String> desiredResult = new ArrayList<String>();
        desiredResult.add("foo");
        desiredResult.add("fizz");

        assertTrue(desiredResult.equals(parsedHeader));

    }


    @Test
    public void getsRequestTypeAndPath()
    throws IOException{
        String requestText=postPath+"\n"+"junk: fizz\n\n";
        byte [] requestBytes=requestText.getBytes();
        InputStream requestStream = new ByteArrayInputStream(requestBytes);

        Request request=new RequestImpl(requestStream);

        String parsedRequestType= request.get_RequestType();
        String parsedPath=request.get_path();

        assertEquals("POST",parsedRequestType);
        assertEquals("/path/script.cgi",parsedPath);
        
        
    }


    @Test
    public void extractsBody()
    throws IOException{
        String bodyString="abcdefg";
        int length=bodyString.getBytes().length;
        String lengthString=new Integer(length).toString();
                
        String requestText=postPath+"\n"+"Content-Length: "+lengthString+"\n\n"+bodyString;
        byte [] requestBytes=requestText.getBytes();
        InputStream requestStream = new ByteArrayInputStream(requestBytes);

        RequestImpl request=new RequestImpl(requestStream);
        assertEquals(length,request.get_ContentLength());
        assertArrayEquals(bodyString.getBytes(), request.get_Body());
    }
    
    @Test
    public void garbageContentLength()
    throws IOException{
        String bodyString="abcdefg";
        String lengthString="overfishing";

        String requestText=postPath+"\n"+"Content-Length: "+lengthString+"\n\n"+bodyString;
        byte [] requestBytes=requestText.getBytes();
        InputStream requestStream = new ByteArrayInputStream(requestBytes);

        RequestImpl request=new RequestImpl(requestStream);
        assert(!request.contentLengthSupplied());
        assertEquals("POST",request.get_RequestType());
        assertEquals("/path/script.cgi",request.get_path());
        
    }
    
    @Test
    public void emptyRequest()
    throws IOException{

        String requestText="";
        byte [] requestBytes=requestText.getBytes();
        InputStream requestStream = new ByteArrayInputStream(requestBytes);

        RequestImpl request=new RequestImpl(requestStream);
        assertEquals(-1,request.get_ContentLength());
        assertArrayEquals(new byte[0], request.get_Body());
        assert(!request.contentLengthSupplied());
        assert(!request.requestTypeSupplied());
        assert(!request.pathSupplied());
    }
}

//}
