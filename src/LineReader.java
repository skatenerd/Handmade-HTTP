import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class LineReader {
    InputStreamReader _streamReader;
    public LineReader(InputStreamReader streamReader){
        _streamReader=streamReader;
    }
    
    public String readLine()
    throws IOException{
        char curChar;
        StringBuilder builder=new StringBuilder();
        while(!shouldStopReading(curChar=(char)_streamReader.read())){
            builder.append(curChar);
        }
        return builder.toString();
    }
    
    private boolean shouldStopReading(char curChar){
        return (curChar=='\n' || curChar ==-1 || curChar==65535);
    }
    
}
