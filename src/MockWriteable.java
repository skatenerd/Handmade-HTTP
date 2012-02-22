import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockWriteable implements Writeable{
    private List<String> _writtenText;
    public MockWriteable(){
        _writtenText = new ArrayList<String>();
    }
    
    public void PrintLn(String text){
        _writtenText.add(text);
    }
    
    public List<String> get_writtenText(){
        List<String> rtn=new ArrayList<String>();
        rtn.addAll(_writtenText);
        return rtn;
    }


}
