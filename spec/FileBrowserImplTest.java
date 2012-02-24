import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileBrowserImplTest {
    @Test
    public void returnsNullForInvalidDirectories(){
        assertArrayEquals(null,new FileBrowserImpl("/woiejf/efiowfw").ListDirectory("ej/ef.foiw"));
    }

    @Test
    public void encodesFiles()
    throws IOException{
        FileBrowser browser=new FileBrowserImpl("/Users/8thlight/Programs");
        byte [] uncompressed=browser.getFileBytes("/foo.txt");
        byte [] compressed = browser.getZippedFileBytes("/foo.txt");
        assertTrue(uncompressed.length>compressed.length);


    }
    
}
