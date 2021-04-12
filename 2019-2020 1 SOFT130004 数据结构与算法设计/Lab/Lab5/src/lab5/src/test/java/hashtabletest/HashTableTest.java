/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hashtabletest;

import hashtable.HashTable;
import hashtable.HashTableElement;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author tcolburn
 */
public class HashTableTest {

    /**
     * Create a hash table that has a number of elements that is 3 times
     * its capacity so that there will be chains.
     * This table will be used for testing hash table search and removal.
     */
    public HashTableTest() {

        elements = new ArrayList<HashTableElement>(3 * HashTable.CAPACITY);

        for (int i = 0; i < 3 * HashTable.CAPACITY; i++) {
            Object key = new Integer(getUniqueRandom());
            Object data = new Integer(getRandom());
            elements.add(new HashTableElement(key, data));
        }

        hashTable = new HashTable();

        for (HashTableElement element : elements) {
            hashTable.insert(element);
        }

        System.out.println(hashTable.getChainLengths());
    }


    /**
     * This test looks up all elements of the hash table by searching
     * for their keys.  When found, the element is confirmed to be non-null
     * and to have the correct data.
     */
    @Test
    public void testSearch() {

        for (HashTableElement element : elements) {
            Object key = element.getKey();
            Object data = element.getData();
            HashTableElement found = hashTable.search(key);
            assertTrue(found != null);
            assertTrue(found.getData().equals(data));
        }
    }


    /**
     * This test looks up all elements of the hash table by searching
     * for their keys.  When found, each element is removed from the
     * table, which is confirmed by attempting to remove it again.
     * At the end, the hash table is confirmed to be empty.
     */
    @Test
    public void testRemove() {

        assertTrue(hashTable.getSize() == elements.size());

        for (HashTableElement element : elements) {
            Object key = element.getKey();
            HashTableElement found = hashTable.search(key);
            assertTrue(hashTable.remove(found));  // remove the element
            assertFalse(hashTable.remove(found)); // confirm removal
        }

        assertTrue(hashTable.getSize() == 0);
    }


    //************************************************************
    // Private methods and fields follow.

    private static Random random = new Random();

    private static final int LIMIT = 1000;

    private static int getRandom() {
        return random.nextInt(LIMIT);
    }

    private int getUniqueRandom() {
        int n = 0;
        do {
            n = getRandom();
        }
        while ( alreadyUsed(n) );
        return n;
    }

    private boolean alreadyUsed(int key) {
        for (HashTableElement element : elements) {
            if ( (Integer)element.getKey() == key ) return true;
        }
        return false;
    }

    private HashTable hashTable;

    private ArrayList<HashTableElement> elements;

}