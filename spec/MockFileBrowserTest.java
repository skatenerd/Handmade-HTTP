import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockFileBrowserTest {
    @Test
    public void ListsSpecifiedDirectories(){
        String path="/biz/foo/potato/";
        String [] files={"a.txt","boo.jar","fizzdir","bin"};
        FileBrowser mockBrowser = new MockFileBrowser(path,files);
        assertArrayEquals(mockBrowser.ListDirectory(path),files);
        assertArrayEquals(null,mockBrowser.ListDirectory("/not/valid"));
        assertTrue(mockBrowser.isFile("/biz/foo/potato/a.txt"));

    }
}
