import java.util.*;
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
        StringBuilder bodyBuilder=new StringBuilder();
        bodyBuilder.append("<ul>");
        for(String url:urls){
            bodyBuilder.append("<li>");
            bodyBuilder.append(link(url));
            bodyBuilder.append("</li>");
        }        
        bodyBuilder.append("</ul>");

        return pageWithBody(bodyBuilder.toString());
    }
    
    private String link(String url){
        String groomedUrl=url.replace(" ","%20");
        return "<a href="+groomedUrl+">"+url+"</a>";
    }
    
    private String pageWithBody(String body){
        StringBuilder builder=new StringBuilder();
        builder.append("<html>");
        builder.append("<head/>");
        builder.append("<body>");
        builder.append(body);
        builder.append("</body>");
        builder.append("</html>");
        return builder.toString();
    }
    
    public String submitForm(){
        StringBuilder bodyBuilder=new StringBuilder();
        bodyBuilder.append("<form name=\"input\" action=\"form\" method=\"post\">");
        for(String name: ConfigConstants.inputs){
            bodyBuilder.append(name+": ");
            bodyBuilder.append("<input type=text name=\""+name+"\" />");
            bodyBuilder.append("<br/>");
        }
        bodyBuilder.append("<input type=\"submit\" value=\"Submit\" />");
        bodyBuilder.append("</form>");
        return pageWithBody(bodyBuilder.toString());

    }

    public String displayForm(Map<String,String> values){
        StringBuilder bodyBuilder=new StringBuilder();
        Iterator valueIterator = values.entrySet().iterator();
        bodyBuilder.append("<ul>");
        while(valueIterator.hasNext()){
            Map.Entry entry=(Map.Entry<String,String>)(valueIterator.next());
            bodyBuilder.append("<li>");
            bodyBuilder.append((String)entry.getKey()+": "+(String)entry.getValue());
            bodyBuilder.append("</li>");
        }
        bodyBuilder.append("</ul>");

        return pageWithBody(bodyBuilder.toString());
    }


}
