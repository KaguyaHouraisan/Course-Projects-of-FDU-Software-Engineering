package block;

import id.Id;
import manager.BlockManager;

public interface Block {
    Id getIndexId();
    BlockManager getBlockManager();
    byte[] read();
    int blockSize();
}
