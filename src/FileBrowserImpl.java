import java.io.File;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileBrowserImpl implements FileBrowser{
    private String _root;
    
    public FileBrowserImpl(String root){
        _root=root;        
    }
    
    public File getFileFromPath(String relativePath){
        return new File(_root+relativePath);
    }
    
    public boolean isValidPath(String path){
        Object files=getFileFromPath(path).list();
        return files!=null;
    }
    
    public String [] ListDirectory(String path){
        if (isValidPath(path)){
            return getFileFromPath(path).list();
        }else{
            System.out.println(path);
            return null;
        }
    }
    
    public boolean isDirectory(String path){
        File file=getFileFromPath(path);
        return file.isDirectory();
    }
}
