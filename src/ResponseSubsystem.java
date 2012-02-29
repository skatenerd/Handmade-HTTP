import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/28/12
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ResponseSubsystem {
    boolean shouldHandle(Request request);
    Response buildResponse(Request request);

}
