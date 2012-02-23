import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class LineReaderTest {
    @Test
    public void ReadsLines()
    throws IOException{
        String toRead="foo fing telephon\nfizz\n\nballast";
        byte [] toReadBytes=toRead.getBytes();
        InputStream streamToRead = new ByteArrayInputStream(toReadBytes);
        
        LineReader reader = new LineReader(streamToRead);
        assertEquals("foo fing telephon",reader.readLine());
        assertEquals("fizz", reader.readLine());
        assertEquals("",reader.readLine());
        assertEquals("ballast", reader.readLine());

    }
}
