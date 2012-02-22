import java.io.IOException;
import java.io.InputStream;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestFactory {
    public RequestFactory(){}
    public static Request BuildRequest(InputStream stream)
    throws IOException{
        return new RequestImpl(stream);
    }
}
