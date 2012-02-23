import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarkupGeneratorImplTest {
    @Test
    public void generatesPageOfLinks(){
        MarkupGenerator generator=new MarkupGeneratorImpl();
        List<String> urls=new ArrayList<String>();
        urls.add("foo");
        urls.add("bar");
        
        String markup=generator.pageWithLinks(urls);
        String desiredMarkup="<html><head/><body><a href=foo>foo</a><a href=bar>bar</a></body></html>";
        
        assertEquals(desiredMarkup, markup);
    }
}
