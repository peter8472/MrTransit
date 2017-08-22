package info.p445m.mrtransit;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.lang.String;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pmg on 8/5/2017.
 */

public class CSVLine {


    List<String> fields;

    PushbackReader r;

    public CSVLine(String input) throws IOException {
        r = new PushbackReader(new StringReader(input), 1);
        fields = new ArrayList<String>();
        while (r.ready()) {
            fields.add(getNextField());
        }
    }
    public String getNextField() {
        return "unimpleimedt";

    }

}
