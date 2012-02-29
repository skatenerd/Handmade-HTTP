import org.junit.Test;

import java.io.ByteArrayOutputStream;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileServerSubsystemTest {
    int _port=8080;
    @Test
    public void buildsDirectoryResponses(){
        String path="/";
        String [] files={"foo","bar"};
        FileBrowser mockBrowser=new MockFileBrowser(path,files);
        FileServerSubsystem factory=new FileServerSubsystem(mockBrowser,_port);
        Request mockRequest=new MockRequest("GET",path,"".getBytes(),false,true);
        Response response=factory.buildResponse(mockRequest);
        assertEquals(DirectoryListReponse.class, response.getClass());
    }

    @Test
    public void buildsNotFoundResponses(){
        String path="/Users";
        String [] files={"foo","bar"};
        String wrongPath="/foo/bizz/shabang.sdfljwef";
        FileBrowser mockBrowser=new MockFileBrowser(path,files);
        FileServerSubsystem factory=new FileServerSubsystem(mockBrowser,_port);
        Request mockRequest=new MockRequest("GET",wrongPath,"".getBytes(),false,true);
        Response response=factory.buildResponse(mockRequest);
        assertEquals(response.getClass(), NotFoundResponse.class);
    }

    @Test
    public void buildsFileResponses(){
        String rootPath="/";
        String [] files={"foo.pdf","bar.jpg"};
        String filePath= rootPath+files[0];
        FileBrowser mockBrowser=new MockFileBrowser(rootPath,files);

        FileServerSubsystem factory=new FileServerSubsystem(mockBrowser,_port);

        Request mockRequest=new MockRequest("GET",filePath,"".getBytes(),false,true);
        Response response=factory.buildResponse(mockRequest);

        assertEquals(FileResponse.class, response.getClass());
    }

}
