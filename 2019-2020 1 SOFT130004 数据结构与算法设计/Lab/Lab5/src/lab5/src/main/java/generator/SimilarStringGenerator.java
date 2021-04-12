/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package generator;

/**
 *
 * @author tcolburn
 */
public class SimilarStringGenerator extends RandomStringGenerator {

    public SimilarStringGenerator() {
        reset();
    }

    public Object getNextKey() {
        count++;
        return randomString + count.toString();
    }

    public void reset() {
        StringBuilder buf = new StringBuilder(INIT);
        for (int i = 0; i < SIZE; i++) {
            buf.setCharAt(i, ALPHABET.charAt(getRandom(ALPHABET.length())));
        }
        randomString = buf.toString();
        count = 0;
    }

    private String randomString;
    private Integer count;

}
