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
public class MalformedRequestResponderTest {
    @Test
    public void buildsTimeoutResponses(){
        MalformedRequestResponder factory=new MalformedRequestResponder();
        Request timeoutRequest=new MockRequest("POST","/bbb","".getBytes(),true,true);

        Response timeoutResponse=factory.buildResponse(timeoutRequest,new ByteArrayOutputStream());
        assertEquals(TimeoutResponse.class,timeoutResponse.getClass());
    }

    @Test
    public void buildsBadRequestResponses(){
        MalformedRequestResponder factory=new MalformedRequestResponder();
        Request mockRequest=new MockRequest("GET","/some/crazy/bad/form/path","".getBytes(),false,false);
        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream());
        assertEquals(BadRequestResponse.class, response.getClass());
    }

    @Test
    public void buildsNotAllowedResponses(){
        MalformedRequestResponder factory=new MalformedRequestResponder();
        Request randomPut=new MockRequest(null,"/","".getBytes(),false,true);
        Response putResponse=factory.buildResponse(randomPut, new ByteArrayOutputStream());
        assertEquals(NotAllowedResponse.class, putResponse.getClass());
    }

    @Test
    public void knowsWhatToHandle(){
        MalformedRequestResponder factory=new MalformedRequestResponder();

        Request malformed=new MockRequest(null,"/some/crazy/bad/form/path","".getBytes(),false,false);
        assertTrue(factory.shouldHandle(malformed));

        Request timedOut=new MockRequest(null,"/some/crazy/bad/form/path","".getBytes(),true,false);
        assertTrue(factory.shouldHandle(timedOut));

        Request valid=new MockRequest(null,"/some/crazy/bad/form/path","".getBytes(),false,true);
        assertFalse(factory.shouldHandle(valid));
    }


}
