/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package generator;

import hashtable.HashTable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Objects of this class generate integers in such a way that
 * a sequence of them hashes to complete and non-colliding slots
 * in the hash table,
 * resulting in perfect hashing performance.
 * @author tcolburn
 */
public class CompleteIntegerGenerator extends AbstractKeyGenerator {

    public CompleteIntegerGenerator() {
        reset();
    }

    public Object getNextKey() {
        if ( index == HashTable.CAPACITY ) {
            reset();
        }
        return values.get(index++);
    }

    public void reset() {
        values = new ArrayList<Integer>();
        int randomFactor = getNonMultipleRandom();
        for (int i = 0; i < HashTable.CAPACITY; i++) {
            values.add((i+1) * randomFactor);
        }
        Collections.shuffle(values);
        index = 0;
    }

    public List<Integer> getValues() {
        return values;
    }


    private static int getNonMultipleRandom() {
        int n = 0;
        do {
            n = getRandom(MULTIPLIER);
        }
        while ( n % HashTable.CAPACITY == 0 );
        return n;
    }

    private int index;
    private List<Integer> values;
}
