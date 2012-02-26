import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/26/12
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileResponseTest {
    @Test
    public void deliversFile()
    throws IOException{
        String [] files = {};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        OutputStream stream=new ByteArrayOutputStream();

        Request jpgRequest = new MockRequest("UBERGET","fzzzz.jpg",new byte[0]);
        FileResponse jpgResponse=new FileResponse(jpgRequest,mockBrowser,stream);


        assertArrayEquals(mockBrowser.getFileBytes("fzzzz.jpg"),jpgResponse.getBody());
        assertEquals(mockBrowser.getFileBytes("fzzzz.jpg").length,jpgResponse.contentLength());
    }
    
    
    @Test
    public void findsContentType(){
        String [] files = {};
        FileBrowser mockBrowser=new MockFileBrowser("",files);
        OutputStream stream=new ByteArrayOutputStream();

        Request jpgRequest = new MockRequest("UBERPOST","fzzzz.jpg",new byte[0]);
        FileResponse jpgResponse=new FileResponse(jpgRequest,mockBrowser,stream);
        assertEquals(jpgResponse.contentType(),"image/jpeg");

        Request bmpRequest = new MockRequest("UBERPOST","fzzzz.bmp",new byte[0]);
        FileResponse bmpResponse=new FileResponse(bmpRequest,mockBrowser,stream);
        assertEquals(bmpResponse.contentType(),"image/bmp");

        Request pdfRequest = new MockRequest("UBERPOST","fzzzz.pdf",new byte[0]);
        FileResponse pdfResponse=new FileResponse(pdfRequest,mockBrowser,stream);
        assertEquals(pdfResponse.contentType(),"application/pdf");

        Request gifRequest = new MockRequest("UBERPOST","fzzzz.gif",new byte[0]);
        FileResponse gifResponse=new FileResponse(gifRequest,mockBrowser,stream);
        assertEquals(gifResponse.contentType(),"image/gif");

        Request htmlRequest = new MockRequest("UBERPOST","fzzzz.html",new byte[0]);
        FileResponse htmlResponse=new FileResponse(htmlRequest,mockBrowser,stream);
        assertEquals(htmlResponse.contentType(),"text/html");

        Request badRequest = new MockRequest("UBERPOST","fnorbbbb",new byte[0]);
        FileResponse badResponse=new FileResponse(badRequest,mockBrowser,stream);
        assertEquals(badResponse.contentType(),"text/plain");
    }


}
