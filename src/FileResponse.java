import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileResponse extends Response {

    public FileResponse(Request request, FileBrowser browser, OutputStream output) {
        super(request, browser, output);
    }

    public byte[] getBody() throws IOException {
        String path = _request.get_path();
        return _browser.getFileBytes(path);
    }

    public String contentType() {
        String path = _request.get_path();
        String type = null;
        if (hasExtension(path, "pdf")) {
            type = "application/pdf";
        } else if (hasExtension(path, "jpg") || (hasExtension(path, "jpeg"))) {
            type = "image/jpeg";
        } else if (hasExtension(path, "bmp")) {
            type = "image/bmp";
        } else if (hasExtension(path, "gif")) {
            type = "image/gif";
        } else if (hasExtension(path, "png")) {
            type = "image/png";
        } else if (hasExtension(path, "html")) {
            type = "text/html";
        } else {
            type = "text/plain";
        }
        return type;
    }

    private boolean hasExtension(String path, String extension) {
        String trimmed = path;
        if (path.endsWith("/")) {
            trimmed = path.substring(0, path.length() - 1);
        }
        return trimmed.endsWith(extension);
    }

    public String status() {
        return "200 OK";
    }

    public int contentLength() throws IOException {
        return getBody().length;
    }

    public List<String> getHeader()
            throws IOException {
        List<String> baseHeader = super.getHeader();
        if (contentType().equals("application/pdf")) {
            String[] splittedPath = _request.get_path().split("/");
            String filename = splittedPath[splittedPath.length - 1];
            baseHeader.add("Content-Disposition: attachment; filename=" + filename);
        }
        return baseHeader;
    }

}
