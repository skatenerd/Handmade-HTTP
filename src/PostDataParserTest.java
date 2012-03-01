import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 3/1/12
 * Time: 1:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class PostDataParserTest {
    @Test
    public void parsesPostData(){
        String postData="foo=22&zanzibar=far";
        Map<String,String> parsed = PostDataParser.parse(postData);
        assertEquals("22",parsed.get("foo"));
        assertEquals("far",parsed.get("zanzibar"));
        assertNull(parsed.get("fizz"));
    }
}
