import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ResponseFactory {
    public Response buildResponse(Request request,
                                  OutputStream stream,
                                  FileBrowser browser);
}
