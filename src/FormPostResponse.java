import java.io.OutputStream;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/25/12
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormPostResponse extends Response {
    MarkupGenerator _generator;

    public FormPostResponse(Request request, MarkupGenerator generator) {
        super(request);
        _generator = generator;
    }

    public byte[] getBody() {
        Map<String, String> values = PostDataParser.parse(new String(_request.get_Body()));
        return _generator.displayForm(values).getBytes();
    }


    public String contentType() {
        return "text/html";
    }

    public String status() {
        return "200 OK";
    }

}
