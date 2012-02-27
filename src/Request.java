import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/21/12
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Request {
    String get_RequestType();
    String get_path();
    boolean pathSupplied();
    boolean requestTypeSupplied();
    byte [] get_Body();
}
