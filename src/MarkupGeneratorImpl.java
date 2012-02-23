import java.util.List;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/23/12
 * Time: 1:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarkupGeneratorImpl implements MarkupGenerator{
    public MarkupGeneratorImpl(){}
    
    public String pageWithLinks(List<String> urls){
        StringBuilder builder=new StringBuilder();
        builder.append("<html>");
        builder.append("<head/>");
        builder.append("<body>");
        builder.append("<ul>");

        for(String url:urls){
            builder.append("<li>");
            builder.append(link(url));
            builder.append("</li>");
        }
        
        builder.append("</ul>");
        builder.append("</body>");
        builder.append("</html>");
        return builder.toString();
    }
    private String link(String url){
        return "<a href="+url+">"+url+"</a>";
    }
}
