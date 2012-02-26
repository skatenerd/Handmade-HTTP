import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MarkupGenerator {
    public String pageWithLinks(List<String> url);
    public String submitForm();
    public String displayForm(Map<String,String> values);
}
