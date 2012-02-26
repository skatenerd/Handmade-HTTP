import org.junit.Test;

import java.io.*;

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
    public void listsRealDirectories(){
        String path="/Users/8thlight/Documents";
        File file=new File(path);
        if (file!=null){
            FileBrowser fileBrowser=new FileBrowserImpl("/");
            assertTrue(fileBrowser.isDirectory(path));
            assertTrue(fileBrowser.ListDirectory(path).length>0);
        }else{
            System.out.println("WARNING FILEBROWSERIMPL NOT TESTED");
        }
    }

    @Test
    public void listsRealFiles()
    throws IOException{
        String path="/Users/8thlight/Documents";
        String filename="foo.txt";
        String relativePath="/"+filename;
        File file=new File(path+relativePath);
        if (file!=null){
            FileBrowser fileBrowser=new FileBrowserImpl(path);
            assertTrue(fileBrowser.isFile(relativePath));
            assertTrue(fileBrowser.getFileBytes(relativePath)!=null);
        }else{
            System.out.println("WARNING FILEBROWSERIMPL NOT TESTED");
        }
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
