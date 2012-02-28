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
    String postPath="POST /path/form HTTP/1.0";
    String noRequestType="/path/img.jpg HTTP/1.0\n\n";
    String badRequestType="FIZZ /path/frog.txt HTTP/1.0\n\n";
    String noContentLength="POST /path/form HTTP/1.0\nJunk: Freak\n\nbodayyy";
    String postWithContentLength="POST /path/form HTTP/1.0\nContent-Length: 33\n\nbodayyy";
    
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
        assertFalse(request.isWellFormed());

    }


    @Test
    public void getsRequestTypeAndPath()
    throws IOException{
        String requestText=postPath+"\n"+"Content-Length: 772\nAccept-Encoding: gzip, fizz\n\nfnorb";
        byte [] requestBytes=requestText.getBytes();
        InputStream requestStream = new ByteArrayInputStream(requestBytes);

        Request request=new RequestImpl(requestStream);

        String parsedRequestType= request.get_requestType();
        String parsedPath=request.get_path();

        assertEquals("POST",parsedRequestType);
        assertEquals("/path/form",parsedPath);
        assertTrue(request.isWellFormed());
        
    }


    @Test
    public void extractsBody()
    throws IOException{
        String bodyString="abcdefg";
        int length=bodyString.getBytes().length;
        String lengthString=Integer.toString(length);
                
        String requestText=postPath+"\n"+"Content-Length: "+lengthString+"\n\n"+bodyString;
        byte [] requestBytes=requestText.getBytes();
        InputStream requestStream = new ByteArrayInputStream(requestBytes);

        RequestImpl request=new RequestImpl(requestStream);
        assertEquals(length,request.get_ContentLength());
        assertArrayEquals(bodyString.getBytes(), request.get_Body());
        assertTrue(request.isWellFormed());
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
        assertEquals("POST",request.get_requestType());
        assertEquals("/path/form",request.get_path());
        assertFalse(request.isWellFormed());
        
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
        assertFalse(request.contentLengthSupplied());
        assertFalse(request.requestTypeSupplied());
        assertFalse(request.pathSupplied());
        assertFalse(request.isWellFormed());
    }
    
    @Test
    public void missingConentLength()
    throws IOException{
        InputStream stream=new ByteArrayInputStream(noContentLength.getBytes());
        Request request=new RequestImpl(stream);
        assertFalse(request.isWellFormed());
    }

    @Test
    public void validPostWithContentLength()
    throws IOException{
        InputStream stream=new ByteArrayInputStream(postWithContentLength.getBytes());
        Request request=new RequestImpl(stream);
        assertTrue(request.isWellFormed());
    }

    @Test
    public void basicInvalidation()
    throws IOException{
        InputStream noRequestTypeStream=new ByteArrayInputStream(noRequestType.getBytes());
        Request noRequestTypeRequest=new RequestImpl(noRequestTypeStream);
        assertFalse(noRequestTypeRequest.isWellFormed());

        InputStream badRequestTypeStream=new ByteArrayInputStream(badRequestType.getBytes());
        Request badRequestTypeRequest=new RequestImpl(noRequestTypeStream);
        assertFalse(badRequestTypeRequest.isWellFormed());
    }
}

//}
