import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseFactoryTest {

    @Test
    public void takesListOfSubsystems(){
        ResponseSubsystem alwaysPing=new ResponseSubsystem() {
            @Override
            public boolean shouldHandle(Request request) {
                return true;
            }

            @Override
            public Response buildResponse(Request request) {
                return new PingResponse();
            }
        };

        List<ResponseSubsystem> subsystemList=new ArrayList<ResponseSubsystem>();
        subsystemList.add(alwaysPing);
        ResponseFactory factory=new ResponseFactory();
        MockRequest mockRequest=new MockRequest("GET","asdf","".getBytes(),false,true);
        Response response=factory.buildResponse(mockRequest,subsystemList);
        assertEquals(PingResponse.class,response.getClass());
        
        MockRequest malformed=new MockRequest(null,"asfd","".getBytes(),false,false);
        Response malformedResponse=factory.buildResponse(malformed, subsystemList);
        assertEquals(BadRequestResponse.class,malformedResponse.getClass());
    }
}
