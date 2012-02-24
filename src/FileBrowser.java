import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 9:08 AM
 * To change this template use File | Settings | File Templates.
 */
public interface FileBrowser {
    String [] ListDirectory(String path);
    boolean isValidPath(String path);
    boolean isDirectory(String path);
    boolean isFile(String path);
    byte [] getFileBytes(String path) throws FileNotFoundException, IOException;

}
