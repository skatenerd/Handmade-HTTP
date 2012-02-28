import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/21/12
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Request {
    String get_requestType();

    String get_path();

    boolean pathSupplied();

    boolean requestTypeSupplied();

    byte[] get_Body();

    boolean get_timedOut();

    boolean isWellFormed();


}
