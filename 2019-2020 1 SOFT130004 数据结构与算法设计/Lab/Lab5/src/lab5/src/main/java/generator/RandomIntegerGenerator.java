/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package generator;

/**
 * Objects of this class generate integers in a random manner,
 * resulting in good hashing performance.
 * @author tcolburn
 */
public class RandomIntegerGenerator extends AbstractKeyGenerator {
    
    public Object getNextKey() {
        Integer key = new Integer(getRandom(MULTIPLIER));
        return key;
    }
}
