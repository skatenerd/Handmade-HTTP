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
        String desiredMarkup="<html><head/><body>"+
                "<ul><li><a href=foo>foo</a></li><li><a href=bar>bar</a></li></ul></body></html>";
        
        assertEquals(desiredMarkup, markup);
    }

    @Test
    public void BuildsForm(){
        MarkupGenerator generator=new MarkupGeneratorImpl();
        String form=generator.submitForm();
        String firstInput= ConfigConstants.inputs[0];

        assertTrue(form.indexOf("<input type=text name=\""+firstInput+"\" />")>0);
        assertTrue(form.indexOf("<input type=\"submit\" value=\"Submit\" />")>0);
    }

    @Test
    public void DisplaysPostData(){
        MarkupGenerator generator=new MarkupGeneratorImpl();
        Map<String,String>formValues=new HashMap<String, String>();
        for(String inputName:ConfigConstants.inputs){
            formValues.put(inputName,inputName);
        }
        String displayedForm=generator.displayForm(formValues);
        for(String inputName:ConfigConstants.inputs){
            assertTrue(displayedForm.indexOf(inputName)>0);
        }
    }
}
