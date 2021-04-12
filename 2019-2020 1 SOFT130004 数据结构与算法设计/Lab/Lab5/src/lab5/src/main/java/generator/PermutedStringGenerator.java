/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author tcolburn
 */
public class PermutedStringGenerator extends RandomStringGenerator {

    public PermutedStringGenerator() {
        charList = new ArrayList<Character>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            charList.add(null);
        }
        builder = new StringBuilder(INIT);
        reset();
    }

    public Object getNextKey() {
        for (int i = 0; i < SIZE; i++) {
            charList.set(i, builder.charAt(i));
        }
        Collections.shuffle(charList);
        for (int i = 0; i < SIZE; i++) {
            builder.setCharAt(i, charList.get(i));
        }
        return builder.toString();
    }

    public void reset() {
        for (int i = 0; i < SIZE; i++) {
            char randomChar = ALPHABET.charAt(getRandom(ALPHABET.length()));
            builder.setCharAt(i, randomChar);
        }
    }

    private StringBuilder builder;
    private List<Character> charList;
}
