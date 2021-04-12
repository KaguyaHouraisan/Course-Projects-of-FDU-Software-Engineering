/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package generator;

import hashtable.HashTable;
import java.util.ArrayList;
import java.util.List;

/**
 * Objects of this class generate integers in an ad hoc manner such that
 * the HashTable.hashInteger() method will return
 * only one of three values in [0, 1, 2, ... CAPACITY-1],
 * resulting in poor hashing performance.
 * @author tcolburn
 */
public class AdHocIntegerGenerator extends CompleteIntegerGenerator {

    public AdHocIntegerGenerator() {
        int index1 = getRandom(HashTable.CAPACITY);  // random int in [0..CAPACITY-1]
        int index2 = getRandom(HashTable.CAPACITY);
        int index3 = getRandom(HashTable.CAPACITY);
        
        while ( index2 == index1 )      // make sure index1, index2, index3
            index2 = getRandom(HashTable.CAPACITY);  // are distinct
        
        while ( index3 == index1 || index3 == index2 )
            index3 = getRandom(HashTable.CAPACITY);

        indices = new ArrayList<Integer>();
        indices.add(index1);
        indices.add(index2);
        indices.add(index3);

        count = 0;
    }

    public Object getNextKey() {
        int index = indices.get(getRandom(indices.size()));
        Integer key = getValues().get(index) + count++ * HashTable.CAPACITY;
        count++;
        return key;
    }

    private int count;

    private List<Integer> indices;

}
