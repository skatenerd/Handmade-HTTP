import org.junit.Test;

import java.util.*;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockMarkupGeneratorTest {
    @Test
    public void recordsCalls(){
        MockMarkupGenerator mock=new MockMarkupGenerator();
        List<String> args=new ArrayList<String>();
        args.add("foo");
        args.add("bar");
        mock.pageWithLinks(args);
        mock.submitForm();
        
        Map<String,String>formValues=new HashMap<String,String>();
        formValues.put("pollution","acid rain");

        mock.displayForm(formValues);
        
        assertEquals("pageWithLinks", mock.calls.get(0));
        assertEquals(args, mock.args.get(0));
        assertEquals("submitForm", mock.calls.get(1));
        assertEquals("displayForm",mock.calls.get(2));
        assertEquals(formValues,mock.lastFormData);

    }
}
