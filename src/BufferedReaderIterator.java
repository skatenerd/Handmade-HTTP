import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/22/12
 * Time: 1:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class BufferedReaderIterator implements Iterator{
    BufferedReader _reader;
    public BufferedReaderIterator(BufferedReader reader){
        _reader = reader;
    }

    public boolean hasNext() {
        throw new UnsupportedOperationException();
//        boolean rtn = false;
//        try {
//            rtn = _reader.ready();
//        } catch (IOException e) {
//        }
//        return rtn;
    }

    public String next(){
        String rtn=null;
        try{
        rtn=_reader.readLine();
        }catch(IOException e){

        }
        return rtn;
    }

    public void remove(){
        throw new UnsupportedOperationException();
    }

}
