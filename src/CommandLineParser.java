import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/26/12
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommandLineParser {
    private int _port;
    private String _path;
    private boolean _portValid;
    private boolean _pathValid;


    public CommandLineParser(String [] args, FileBrowser browser){
        String pathArg="";
        String portArg="";
        String previousArg="";

        for(String arg:args){
            if(previousArg.equals("-p")){
                parsePort(arg);
            }else if(previousArg.equals("-d")){
                parsePath(arg,browser);
            }
            previousArg=arg;
        }
                
    }
    
    private void parsePath(String path, FileBrowser browser){
        if(browser.isDirectory(path)){
            _path=path;
            _pathValid=true;
        }else{
            _pathValid=false;
        }
    }
    
    private void parsePort(String port){
        boolean isValid=false;
        try{
            _port=Integer.parseInt(port);
            _portValid=true;
        }catch(NumberFormatException e) {
            _portValid=false;
        }
    }
    
    
    
    public int port(){
        return _port;
    }
    
    public String path(){
        return _path;
    }

    public boolean isValidInput(){
        return _portValid && _pathValid;
    }




}
