package manager;

import block.Block;
import id.Id;

public interface BlockManager {
    Block getBlock(Id indexId);
    Block newBlock(byte[] b);
    default Block newEmptyBlock(int blockSize){
        return newBlock(new byte[blockSize]);
    }
}
