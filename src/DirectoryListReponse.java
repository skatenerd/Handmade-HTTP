import java.io.FileNotFoundException;
import java.util.*;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class DirectoryListReponse extends Response {
    private static String _contentType="text";
    private byte [] _body;
    private MarkupGenerator _markup;

    public DirectoryListReponse(Request request, FileBrowser browser, OutputStream output, MarkupGenerator markup){
        super(request, browser,output);
        _body=_defaultBody;
        _markup=markup;
    }

    
    protected int contentLength()
    throws FileNotFoundException{
        return getBody().length;
    }
    
    protected byte[] getBody()
    throws FileNotFoundException{
        if(_body==_defaultBody){
            _body=getBodyText().getBytes();
        }
        return _body;
    }
    
    private String getBodyText()
    throws FileNotFoundException{
        return _markup.pageWithLinks(listFileURLS());
    }

    
    protected String contentType(){
        return _contentType;
    }
    
    protected String status(){
        String [] files = _browser.ListDirectory(_request.get_path());
        if (files==null){
            return "404 Not Found";
        }else{
            return "200 OK";
        }
    }

    
    protected List<String> listFileURLS()
    throws FileNotFoundException{
        List<String> rtn=new ArrayList<String>();
        String [] files = (_browser.ListDirectory(_request.get_path()));
        if(files!=null){
            for(String file:files){
                String url="http://localhost:8080"+_request.get_path()+file+"/";
                rtn.add(url);
            }
        }else{
            throw new FileNotFoundException("Should only receive calls to existing directories");
        }
        return rtn;
    }

}
