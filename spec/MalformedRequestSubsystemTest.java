import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class MalformedRequestSubsystemTest {
    @Test
    public void buildsTimeoutResponses(){
        MalformedRequestSubsystem factory=new MalformedRequestSubsystem();
        Request timeoutRequest=new MockRequest("POST","/bbb","".getBytes(),true,true);

        Response timeoutResponse=factory.buildResponse(timeoutRequest);
        assertEquals(TimeoutResponse.class,timeoutResponse.getClass());
    }

    @Test
    public void buildsBadRequestResponses(){
        MalformedRequestSubsystem factory=new MalformedRequestSubsystem();
        Request mockRequest=new MockRequest("GET","/some/crazy/bad/form/path","".getBytes(),false,false);
        Response response=factory.buildResponse(mockRequest);
        assertEquals(BadRequestResponse.class, response.getClass());
    }

    @Test
    public void knowsWhatToHandle(){
        MalformedRequestSubsystem factory=new MalformedRequestSubsystem();

        Request malformed=new MockRequest(null,"/some/crazy/bad/form/path","".getBytes(),false,false);
        assertTrue(factory.shouldHandle(malformed));

        Request timedOut=new MockRequest(null,"/some/crazy/bad/form/path","".getBytes(),true,false);
        assertTrue(factory.shouldHandle(timedOut));

        Request valid=new MockRequest(null,"/some/crazy/bad/form/path","".getBytes(),false,true);
        assertFalse(factory.shouldHandle(valid));
    }


}
