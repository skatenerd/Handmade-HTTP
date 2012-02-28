import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class PingSubsystemTest
{
    @Test
    public void buildsPingResponses(){
        PingSubsystem factory=new PingSubsystem();
        Request pingRequest=new MockRequest("GET", ConfigConstants.pingLocation, "".getBytes(),false,true);
        Response pingResponse=factory.buildResponse(pingRequest,new ByteArrayOutputStream());
        assertEquals(PingResponse.class,pingResponse.getClass());
    }

}
