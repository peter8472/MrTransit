package info.p445m.mrtransit;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pmg on 8/5/2017.
 */
public class CSVLineTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getNextField() throws Exception {

        String fn = "C:\\\\Users\\\\pmg\\\\AndroidStudioProjects\\\\MrTransit\\\\app\\\\src\\\\main\\\\assets\\\\Unitrans\\\\stops.txt";
        TokenReader tread = new TokenReader(fn);
        Token k;

        do {

             k = tread.getToken();

            System.out.format("%s %s\n", k.type, k.value);
        } while (k.type != Token.Ttype.EOF);




    }

}