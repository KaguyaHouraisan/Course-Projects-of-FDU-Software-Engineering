/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package generator;

/**
 *
 * @author tcolburn
 */
public class RandomStringGenerator extends AbstractKeyGenerator {

    public RandomStringGenerator() {
        buf = new StringBuilder(INIT);
    }
    
    public Object getNextKey() {
        for (int i = 0; i < SIZE; i++) {
            buf.setCharAt(i, ALPHABET.charAt(getRandom(ALPHABET.length())));
        }
        return buf.toString();
    }

    protected static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    protected static final String INIT = "12345678";

    protected static final int SIZE = INIT.length();

    private StringBuilder buf;

}
