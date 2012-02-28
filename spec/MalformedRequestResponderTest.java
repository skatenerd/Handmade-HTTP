import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

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
        Request timeoutRequest=new MockRequest("POST","/bbb","".getBytes(),true);

        Response timeoutResponse=factory.buildResponse(timeoutRequest,new ByteArrayOutputStream());
        assertEquals(TimeoutResponse.class,timeoutResponse.getClass());
    }

    @Test
    public void buildsBadRequestResponses(){
        MalformedRequestResponder factory=new MalformedRequestResponder();
        Request mockRequest=new MockRequest(null,"/some/crazy/bad/form/path","".getBytes());
        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream());
        assertEquals(BadRequestResponse.class, response.getClass());
    }
}
