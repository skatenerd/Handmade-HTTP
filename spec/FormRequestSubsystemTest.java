import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormRequestSubsystemTest {

    @Test
    public void buildsFormPostResponses(){
        FormRequestSubsystem factory=new FormRequestSubsystem();
        Request mockRequest=new MockRequest("POST",ConfigConstants.formLocation,"foo=22".getBytes(),false,true);
        Response response=factory.buildResponse(mockRequest);
        assertEquals(FormPostResponse.class, response.getClass());
    }

    @Test
    public void buildsFormGetResponses(){
        FormRequestSubsystem factory=new FormRequestSubsystem();
        Request mockRequest=new MockRequest("GET",ConfigConstants.formLocation,"".getBytes(),false,true);
        Response response=factory.buildResponse(mockRequest);
        assertEquals(FormGetResponse.class, response.getClass());
    }
    
    @Test
    public void knowsWhatToHandle(){
        FormRequestSubsystem factory=new FormRequestSubsystem();
        Request formGetRequest=new MockRequest("GET",ConfigConstants.formLocation,"".getBytes(),false,true);
        assertTrue(factory.shouldHandle(formGetRequest));

        Request formPostRequest=new MockRequest("POST",ConfigConstants.formLocation,"".getBytes(),false,true);
        assertTrue(factory.shouldHandle(formPostRequest));
        
        Request randomGetRequest=new MockRequest("GET","/something/way/random","".getBytes(),false,true);
        assertFalse(factory.shouldHandle(randomGetRequest));
    }
}
