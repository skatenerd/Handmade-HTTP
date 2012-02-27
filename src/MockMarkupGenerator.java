import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockMarkupGenerator implements MarkupGenerator {
    public List<String> calls;
    public List<List<String>> args;
    public Map<String, String> lastFormData;

    public MockMarkupGenerator() {
        calls = new ArrayList<String>();
        args = new ArrayList<List<String>>();
    }


    public String pageWithLinks(List<String> urls) {
        calls.add("pageWithLinks");
        args.add(urls);
        return "Bzzzzzzz";
    }

    public String submitForm() {
        calls.add("submitForm");
        return "fnorb";
    }

    public String displayForm(Map<String, String> values) {
        calls.add("displayForm");
        lastFormData = values;
        return "smog";
    }
}
