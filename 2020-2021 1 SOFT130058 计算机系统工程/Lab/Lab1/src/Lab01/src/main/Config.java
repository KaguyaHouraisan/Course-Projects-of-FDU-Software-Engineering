package main;

import id.Id;
import id.SmartID;
import manager.SmartBlockManager;
import manager.SmartFileManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {
    public static Map<Id, SmartBlockManager> smartBlockManagerMap;
    public static Map<Id, SmartFileManager> smartFileManagerMap;
    public static Map<Id, Id> fileEmptyMap;

    static {
        smartBlockManagerMap = new HashMap<>();
        String blockManagerName;
        String blockManagerAddress;
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/BlockManagerList.info"));
            String tmp = br.readLine();
            while (tmp != null) {
                blockManagerName = tmp.split(" ")[0];
                blockManagerAddress = tmp.split(" ")[1];
                smartBlockManagerMap.put(new SmartID(blockManagerName), new SmartBlockManager(blockManagerAddress));
                tmp = br.readLine();
            }
        } catch (IOException e) {
            throw new ErrorCode(ErrorCode.IO_EXCEPTION);
        }

        smartFileManagerMap = new HashMap<>();
        String fileManagerName;
        String fileManagerAddress;
        try {
            BufferedReader fr = new BufferedReader(new FileReader("./data/FileManagerList.info"));
            String tmp = fr.readLine();
            while (tmp != null) {
                fileManagerName = tmp.split(" ")[0];
                fileManagerAddress = tmp.split(" ")[1];
                smartFileManagerMap.put(new SmartID(fileManagerName), new SmartFileManager(fileManagerAddress));
                tmp = fr.readLine();
            }
        } catch (IOException e) {
            throw new ErrorCode(ErrorCode.IO_EXCEPTION);
        }

        fileEmptyMap = new HashMap<>();
        fileEmptyMap.put(new SmartID("FILE_EMPTY"), new SmartID("FILE_EMPTY"));
    }
}
