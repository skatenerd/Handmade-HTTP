import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;
import java.util.*;
import java.util.zip.GZIPOutputStream;

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
    
    public byte [] getFileBytes(String path)
    throws IOException{
        File file=getFileFromPath(path);
        FileInputStream stream=new FileInputStream(file);

        byte [] fileBytes = new byte [(int)file.length()];
        stream.read(fileBytes);

        return fileBytes;
    }
    
    public byte [] getZippedFileBytes(String path)
    throws IOException{
        byte [] uncompressedBytes=getFileBytes(path);
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        GZIPOutputStream zippedStream=new GZIPOutputStream(output);
        zippedStream.write(uncompressedBytes);
        byte [] compressedBytes=new byte[output.size()];
        zippedStream.write(compressedBytes);
        return compressedBytes;

    }
    
    public boolean isFile(String path){
        File file=getFileFromPath(path);
        return file.isFile();
    }
}
