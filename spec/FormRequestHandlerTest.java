import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormRequestHandlerTest {

    @Test
    public void buildsFormPostResponses(){
        FormRequestHandler factory=new FormRequestHandler();
        Request mockRequest=new MockRequest("POST",ConfigConstants.formLocation,"foo=22".getBytes(),false,true);
        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream());
        assertEquals(FormPostResponse.class, response.getClass());
    }

    @Test
    public void buildsFormGetResponses(){
        FormRequestHandler factory=new FormRequestHandler();
        Request mockRequest=new MockRequest("GET",ConfigConstants.formLocation,"".getBytes(),false,true);
        Response response=factory.buildResponse(mockRequest, new ByteArrayOutputStream());
        assertEquals(FormGetResponse.class, response.getClass());
    }
}
