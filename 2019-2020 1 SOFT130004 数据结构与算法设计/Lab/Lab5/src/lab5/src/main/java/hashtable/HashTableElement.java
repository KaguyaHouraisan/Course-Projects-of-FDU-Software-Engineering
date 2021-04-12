package hashtable;

/**
 * This class represents hash table elements.
 * A hash table element has an arbitrary object as its key
 * and an arbitrary object as the element's stored data.
 * @author tcolburn
 */
public class HashTableElement {

    public HashTableElement(Object key, Object data) {
        this.key = key;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public Object getKey() {
        return key;
    }

    private Object key;
    private Object data;

}
