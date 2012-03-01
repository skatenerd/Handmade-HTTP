import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 3/1/12
 * Time: 1:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class PostDataParser {

    public static Map<String, String> parse(String postdata) {
        Map<String, String> rtn = new HashMap<String, String>();
        String[] values = postdata.split("&");
        for (String value : values) {
            String[] keyAndVal = value.split("=");
            if(keyAndVal.length==2){
                rtn.put(keyAndVal[0], keyAndVal[1]);
            }
        }
        return rtn;
    }
}
