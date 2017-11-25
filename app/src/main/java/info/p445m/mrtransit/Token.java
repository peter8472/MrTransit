package info.p445m.mrtransit;

/**
 * represents a token.  There are 4 kinds of token:
 *
 * Everything else should be handled by the tokenizer.  quoted strings are
 * returned with the quotes removed, and the quoted quotes dequoted.
 * Created by pmg on 9/27/2017.
 *
 */


public class Token {
    public enum Ttype { STRING, COMMA, EOL, EOF, ERROR }
    String value;
    Ttype type;
}