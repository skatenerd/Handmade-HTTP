/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockFileBrowser implements FileBrowser{
    private String _directoryPath;
    private String [] _fileNames;
    
    public MockFileBrowser(String path, String [] fileNames){
        _directoryPath =path;
        _fileNames = fileNames;
    }
    
    public String [] ListDirectory(String path){
       if(isValidPath(path)){
           return _fileNames;
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
    
    public byte [] getFileBytes(String path){
        return "overfishing is a major problem".getBytes();
    }
    
    public boolean isFile(String path){
        boolean isFile=false;
        for(String fileName:_fileNames){
            if(path.equals(_directoryPath+fileName)){
                isFile = true;
            }
        }
        return isFile;
    }
}
