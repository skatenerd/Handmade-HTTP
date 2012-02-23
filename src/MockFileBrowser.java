/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockFileBrowser implements FileBrowser{
    private String _directoryPath;
    private String [] _files;
    
    public MockFileBrowser(String path, String [] files){
        _directoryPath =path;
        _files = files;
    }
    
    public String [] ListDirectory(String path){
       if(isValidPath(path)){
           return _files;
       }else{
           return null;
       }

    }
    public boolean isValidPath(String path){
        return path.equals(_directoryPath);
    }
    
    public boolean isDirectory(String path){
        return path.equals(_directoryPath);
    }
}
