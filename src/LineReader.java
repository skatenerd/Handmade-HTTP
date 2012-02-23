import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class LineReader {
    InputStream _stream;
    public LineReader(InputStream stream){
        _stream=stream;
    }
    
    public String readLine()
    throws IOException{
        char curChar;
        StringBuilder builder=new StringBuilder();
        while(!shouldStopReading(curChar=(char)_stream.read())){
            builder.append(curChar);
        }
        System.out.println(builder.toString());
        return builder.toString();
    }
    
    private boolean shouldStopReading(char curChar){
        return (curChar=='\n' || curChar ==-1 || curChar==65535);
    }
    
}
