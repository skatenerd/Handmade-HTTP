import java.io.PrintStream;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrintStreamWriteable implements Writeable{
    PrintStream _stream;
    public PrintStreamWriteable(PrintStream stream){
        _stream = stream;        
    }
    public void PrintLn(String text){
        _stream.println(text);
    }
}
