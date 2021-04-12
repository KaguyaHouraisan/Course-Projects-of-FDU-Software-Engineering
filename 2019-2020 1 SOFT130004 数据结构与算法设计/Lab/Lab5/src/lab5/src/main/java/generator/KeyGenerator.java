/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package generator;

/**
 *
 * @author tcolburn
 */
public interface KeyGenerator {

    Object getNextKey();

    void reset();

}
