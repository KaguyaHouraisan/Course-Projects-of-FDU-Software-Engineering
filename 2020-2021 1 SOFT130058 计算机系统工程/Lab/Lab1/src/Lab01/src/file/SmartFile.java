package file;

import block.Block;
import id.Id;
import id.SmartID;
import main.ErrorCode;
import manager.BlockManager;
import manager.FileManager;
import main.Config;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SmartFile implements File {
    private String path;
    private long fileSize;
    private long blockSize;
    private ArrayList<Map<Id,Id>> LogicBlockList;
    private FileManager fileManager;
    private Id fileId;
    private long cursor = 0;
    public SmartFile(Id id, FileManager fileManager, long fileSize, long blockSize, ArrayList<Map<Id,Id>> list, String path){
        this.fileId = id;
        this.fileManager = fileManager;
        this.fileSize = fileSize;
        this.blockSize = blockSize;
        this.LogicBlockList = list;
        this.path = path;
    }

    public Id getFileId(){
        return fileId;
    }

    public FileManager getFileManager(){
        return fileManager;
    }

    public byte[] read(int length){
        if (length < 0) {
            throw new ErrorCode(ErrorCode.READ_LENGTH_ERROR);
        }
        if (length == 0) {
            return "".getBytes();
        }
        if (length > fileSize) {
            length = (int) fileSize;
        }
        if (cursor < 0 || (cursor >= fileSize && fileSize != 0) || (fileSize == 0 && cursor != 0)) {
            throw new ErrorCode(ErrorCode.CURSOR_ERROR);
        }
        long indexBegin = cursor / blockSize;
        long indexEnd = (cursor + length > fileSize) ? fileSize / blockSize : (cursor + length) / blockSize;
        byte[] retBytes = new byte[length];
        int retBytesIndex = 0;

        byte[] bytes;
        int i = (int)indexBegin;
        bytes = chooseDuplication(LogicBlockList.get(i));
        for (int j = (int)(cursor % blockSize); j < bytes.length; j++) {
            retBytes[retBytesIndex] = bytes[j];
            retBytesIndex++;
            if (retBytesIndex == length) {
                break;
            }
        }
        i++;
        for (; i < indexEnd; i++) {
            bytes = chooseDuplication(LogicBlockList.get(i));
            for(int j = 0; j < bytes.length; j++){
                retBytes[retBytesIndex] = bytes[j];
                retBytesIndex++;
            }
        }

        if (retBytesIndex != length) {
            bytes = chooseDuplication(LogicBlockList.get((int)indexEnd));
            for(int j = 0; j < bytes.length & retBytesIndex < length; j++){
                retBytes[retBytesIndex] = bytes[j];
                retBytesIndex++;
                if (retBytesIndex == length) {
                    break;
                }
            }
        }

        cursor += length;
        return retBytes;
    }

    public void write(byte[] b) {
        if (cursor < 0) {
            throw new ErrorCode(ErrorCode.CURSOR_ERROR);
        }
        if (cursor > fileSize && fileSize != 0) {
            byte[] n = new byte[(int)(cursor - fileSize) + b.length];
            for (int i = 0; i < cursor - fileSize; i++) {
                n[i] = 0x00;
            }
            for (int i = 0; i < b.length; i++){
                n[i] = b[i];
            }
            b = n;
        }
        int indexBegin = (int)(cursor / blockSize);
        int indexEnd = (int)((cursor + b.length) / blockSize);
        int writeIndex = 0;
        byte[] newBytes = new byte[(int)blockSize];
        Map<Id,Id> map;
        try {
            map = LogicBlockList.get(indexBegin);
        } catch (IndexOutOfBoundsException e){
            map = null;
        }
        byte[] oldBytes = chooseDuplication(map);
        int s = LogicBlockList.size();
        for (int i = indexBegin; i < s; i++) {
            LogicBlockList.remove(indexBegin);
        }
        if (indexBegin != indexEnd) {
            int i = 0;
            for (; i < cursor % blockSize; i++) {
                //?
                newBytes[i] = oldBytes[i];
            }
            for (; i < blockSize; i++) {
                newBytes[i] = b[writeIndex];
                writeIndex++;
            }
            LogicBlockList.add(writeDuplication(newBytes));

            for (i = indexBegin + 1; i < indexEnd; i++) {
                newBytes = new byte[(int)blockSize];
                for (int j = 0; j < blockSize; j++) {
                    newBytes[j] = b[writeIndex];
                    writeIndex++;
                }
                LogicBlockList.add(writeDuplication(newBytes));
            }
            newBytes = new byte[b.length - writeIndex];
            for (int j = 0; j < newBytes.length; j++) {
                //?
                newBytes[j] = b[writeIndex];
                writeIndex++;
            }
            if (newBytes.length != 0) {
                LogicBlockList.add(writeDuplication(newBytes));
            }
        } else {
            try {
                newBytes = new byte[(int)(cursor % blockSize) + b.length];
                int i = 0;
                for (; i < cursor % blockSize; i++) {
                    newBytes[i] = oldBytes[i];
                }
                for (; i < newBytes.length; i++) {
                    newBytes[i] = b[writeIndex];
                    writeIndex++;
                }
                if (newBytes.length != 0) {
                    LogicBlockList.add(writeDuplication(newBytes));
                }
            } catch (IndexOutOfBoundsException e) {
                throw new ErrorCode(ErrorCode.COMMAND_ERROR);
            }
        }

        //更新file.meta
        long newFileSize = cursor + b.length;
        updateFileMeta(newFileSize);
        cursor += b.length;
    }

    public long move(long offset, int where) {
        switch (where) {
            case MOVE_CURR:
                cursor += offset;
                break;
            case MOVE_HEAD:
                cursor = offset;
                break;
            case MOVE_TAIL:
                cursor = fileSize - offset;
                break;
        }
        return 0;
    }

    public void close(){
        cursor = 0;
    }

    public long size(){
        return fileSize;
    }

    public void setSize(long newSize){
        if(newSize < 0) {
            throw new ErrorCode(ErrorCode.NEW_SIZE_ERROR);
        }
        long oldSize = this.fileSize;

        if (newSize > oldSize) {
            int oldBlockEndIndex = (int)(oldSize / blockSize);
            int newBlockEndIndex = (int)(newSize / blockSize);

            byte[] oldBlockEnd = chooseDuplication(LogicBlockList.get(oldBlockEndIndex));
            int s = LogicBlockList.size();
            for (int i = oldBlockEndIndex; i < s; i++) {
                LogicBlockList.remove(oldBlockEndIndex);
            }
            int index = 0;
            byte[] newBlock;
            if (newBlockEndIndex == oldBlockEndIndex) {
                newBlock = new byte[(int)(newSize%blockSize)];
            } else {
                newBlock = new byte[(int)blockSize];
            }

            for (int i = 0; i < oldBlockEnd.length; i++) {
                newBlock[index] = oldBlockEnd[i];
                index++;
            }

            for(; index < blockSize; index++) {
                newBlock[index] = 0x00;
                if (index == newBlock.length - 1) {
                    break;
                }
            }

            LogicBlockList.add(writeDuplication(newBlock));
            for (int i = oldBlockEndIndex+1; i < newBlockEndIndex; i++) {
                LogicBlockList.add(writeDuplication(new byte[(int)blockSize]));
            }

            if (oldBlockEndIndex != newBlockEndIndex) {
                LogicBlockList.add(writeDuplication(new byte[(int) (newSize % blockSize)]));
            }

            updateFileMeta(newSize);
        } else if (newSize < oldSize) {
            int newBlockEndIndex = (int)(newSize / blockSize); //newIndex oldIndex
            byte[] newBlockEnd = chooseDuplication(LogicBlockList.get(newBlockEndIndex));
            int s = LogicBlockList.size();
            for (int i = newBlockEndIndex; i < s; i++) {
                LogicBlockList.remove(newBlockEndIndex);
            }

            byte[] newBlock = new byte[(int)(newSize % blockSize)];
            for (int i = 0; i < newBlock.length; i++) {
                newBlock[i] = newBlockEnd[i];
            }
            LogicBlockList.add(writeDuplication(newBlock));
            updateFileMeta(newSize);
        }
    }

    private byte[] chooseDuplication(Map<Id,Id> map) {
        byte[] blockData = null;
        if (map == null) {
            blockData = new byte[(int)blockSize];
        } else {
            for (Map.Entry<Id,Id> entry : map.entrySet()) {
                if ((entry.getKey()).equals(new SmartID("FILE_EMPTY"))) {
                    blockData = new byte[(int)blockSize];
                    break;
                } else {
                    BlockManager blockManager = Config.smartBlockManagerMap.get(entry.getKey());
                    Block block = blockManager.getBlock(entry.getValue());
                    if (block != null) {
                        blockData = block.read();
                        if (blockData != null) {
                            break;
                        }
                    }
                }
            }
        }

        if (blockData == null) {
            throw new ErrorCode(ErrorCode.MEMORY_ERROR);
        } else {
            return blockData;
        }
    }

    private Map<Id,Id> writeDuplication(byte[] b) {
        Map<Id,Id> ret = new HashMap<>();
        boolean isEmpty1 = true;
        for (byte b1 : b) {
            if (b1 != 0x00) {
                isEmpty1 = false;
            }
        }
        if (isEmpty1) {
            return Config.fileEmptyMap;
        } else {
            SmartID smartID1 = new SmartID("bm-1");
            Block block1 = writeBlock(smartID1, b);
            SmartID smartID2 = new SmartID("bm-2");
            Block block2 = writeBlock(smartID2, b);
            SmartID smartID3 = new SmartID("bm-3");
            Block block3 = writeBlock(smartID3, b);

            ret.put(smartID1, block1.getIndexId());
            ret.put(smartID2, block2.getIndexId());
            ret.put(smartID3, block3.getIndexId());
            return ret;
        }
    }

    private Block writeBlock(SmartID id, byte[] b){
        BlockManager blockManager = Config.smartBlockManagerMap.get(id);
        return blockManager.newBlock(b);
    }

    private void updateFileMeta(long newFileSize){
        java.io.File changeMetaFile = new java.io.File(path);
        if (!changeMetaFile.exists()) {
            throw new ErrorCode(ErrorCode.MEMORY_ERROR);
        }
        try {
            FileOutputStream out = new FileOutputStream(changeMetaFile,false);
            StringBuilder sb = new StringBuilder();
            sb.append("size:").append(newFileSize).append("\n");
            sb.append("block size:").append(blockSize).append("\n");
            sb.append("logic block:\n");
            for(int k = 0; k < LogicBlockList.size(); k++) {
                sb.append(k).append(":");
                if (!LogicBlockList.get(k).equals(Config.fileEmptyMap)){
                    for(Map.Entry<Id,Id> entry : LogicBlockList.get(k).entrySet()){
                        sb.append("[\"").append(((SmartID) entry.getKey()).getId()).append("\",").append(((SmartID) entry.getValue()).getId()).append("] ");
                    }
                }
                sb.append("\n");
            }
            out.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        }catch (IOException e) {
            throw new ErrorCode(ErrorCode.IO_EXCEPTION);
        }
        this.fileSize = newFileSize;
    }
}
