import org.junit.*;
import java.io.*;
import java.net.*;
import java.security.PublicKey;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/20/12
 * Time: 2:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntegrationTest {
    Server testServer;
    int portNumber=8084;
    
    @Before
    public void setUp()
    {
        testServer = new Server(portNumber);
        testServer.start();
    }

    @After
    public void tearDown(){
        testServer.kill();

    }

    private Socket getTestSocket()
    throws IOException{
        return new Socket("localhost",portNumber);
    }
    
    private String readSocket(Socket testSocket)
    throws IOException{
        String inputLine;
        BufferedReader input = new BufferedReader(new InputStreamReader(testSocket.getInputStream()));
        StringBuilder outputBuilder = new StringBuilder();
        while((inputLine = input.readLine()) != null){
            outputBuilder.append(inputLine);
        }
        return outputBuilder.toString();
    }

    @Test
    public void responds() throws IOException{
        Socket testSocket=getTestSocket();
        String socketResponse=readSocket(testSocket);
        assertTrue(socketResponse.length()>0);
        testSocket.close();
    }


    @Test
    public void respondsToTwoRequests(){
        try{
            Socket firstSocket=getTestSocket();
            String firstResponse=readSocket(firstSocket);
            assertTrue(firstResponse.length()>0);
            firstSocket.close();

            Socket secondSocket=getTestSocket();
            String secondResponse=readSocket(secondSocket);
            assertTrue(secondResponse.length()>0);
            secondSocket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}

