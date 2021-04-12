package bloomfilter;

import java.util.BitSet;

public class BloomFilter {

	//determine the size of bit array
	private int size = 1048576 * 25;
	//determine the number of hash function (different seeds)
	private int[] seeds = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
	private BitSet bits = new BitSet(size);

	public BloomFilter() {}

	//add an element to Bloom Filter
	public void add(String str) {
		for (int seed: seeds) {
		    bits.set(hash(seed, str), true);
        }
	}

	//query whether Bloom Filter contains the element
	public boolean query(String str) {
        for (int seed: seeds) {
            if (!bits.get(hash(seed, str))) {
                return false;
            }
        }
        return true;
	}

	//Your hash function
	private int hash(int seed, String str) {
        long h = 0;
        for (int i = 0; i < str.length(); i++) {
            h = (h << 4) + (int)str.charAt(i) + seed;
            long g = h & 0xF0000000L;
            if (g != 0) h ^= g >>> 24;
            h &= ~g;
        }
        return (int)(h % size);
	}
}
