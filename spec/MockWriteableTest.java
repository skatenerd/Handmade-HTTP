import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockWriteableTest {
    @Test
    public void recordsWrites(){
        MockWriteable mock=new MockWriteable();
        String [] prints={"fizz","buzz"};
        for (String line:prints){
            mock.PrintLn(line);
        }
        assertArrayEquals(mock.get_writtenText().toArray(),prints);
        
    }
}
