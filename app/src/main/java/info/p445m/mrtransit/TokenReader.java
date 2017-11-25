package info.p445m.mrtransit;

/**
 * Created by pmg on 11/24/2017.
 */
import java.io.*;
public class TokenReader {
    PushbackReader pushback;
    public TokenReader(String filename) throws IOException {
        pushback = new PushbackReader(new FileReader(filename), 1500);
    }
    public Token getToken() throws IOException {
        StringBuffer buf = new StringBuffer();
        int red = pushback.read();
        Token t = new Token();
        if (red == -1) {
            t.type = Token.Ttype.EOF;
            t.value = null;
            return t;
        } else if (red == '\n') {
            t.type = Token.Ttype.EOL;
            t.value = null;
            return t;
        } else if (red == '\r') { // windows lnie endings
            int k = pushback.read();
            if (k != '\n') {
                pushback.unread(k);
                throw new IOException("no newline following CR!?!?");
            }
            t.type = Token.Ttype.EOL;
            t.value = null;
            return t;
        } else if (red == ',') {
            t.type = Token.Ttype.COMMA;
            t.value = ",";
            return t;
        } else if (red == '"') {
//	    return getQuotedString();
        } else {   /// last case, unquoted string
            t.value = getSimple(red);
            t.type = Token.Ttype.STRING;
            return t;
        }
        return null;
    }
    private String getSimple(int firstChar) throws IOException  {
        StringBuilder buf = new StringBuilder();
        int red;
        buf.append((char)firstChar);
        while (true) {
            red = pushback.read();
            if (red == '\r') {
                int k = pushback.read();
                if (k != '\n') throw new IOException("no newline follows CR");
                pushback.unread(k);
                pushback.unread(red);
                return buf.toString();
            } else if (red == -1|| red == ',' || red == '\n') {
// includes quote characters that don't start a field.
// This may turn out to be a horrible mistake
                pushback.unread(red);
                return buf.toString();
            } else { // it's just an ordinary character

                buf.append((char)red);
            }
        }
    }
    public Token getQuotedString() throws IOException {
        StringBuilder buf = new StringBuilder();
        Token t = new Token();
        int red;
        while (true) {
            red = pushback.read();
            if (red == -1) {
                t.type = Token.Ttype.ERROR;
                t.value += "::EOF in quoted string";
                return t;
            } else if (red == '"') {
                int k = pushback.read();
                if (k == '"') {
                    buf.append((char)k); // lose one, keep one
                } else { // end of quoted string
                    pushback.unread(k);
                    t.type = Token.Ttype.STRING;
                    return t;
                }

            } else {
                buf.append((char)red);
            }

        }
    }
}
