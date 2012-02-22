/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 9:08 AM
 * To change this template use File | Settings | File Templates.
 */
public interface FileBrowser {
    String [] ListDirectory(String path);
    boolean ValidPath(String path);
}
