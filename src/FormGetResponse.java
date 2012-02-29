import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/25/12
 * Time: 5:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormGetResponse extends Response {
    MarkupGenerator _generator;

    public FormGetResponse(MarkupGenerator generator) {
        super(null);
        _generator = generator;
    }

    public byte[] getBody() {
        return _generator.submitForm().getBytes();
    }

    public String contentType() {
        return "text/html";
    }

    public String status() {
        return "200 OK";
    }

}
