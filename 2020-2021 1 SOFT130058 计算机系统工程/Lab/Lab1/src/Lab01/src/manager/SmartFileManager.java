package manager;

import file.File;
import id.Id;
import file.SmartFile;
import id.SmartID;
import main.Config;
import main.ErrorCode;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SmartFileManager implements FileManager {
    private String path;
    private final long BLOCK_SIZE = 512;

    public String getPath() {
        return path;
    }

    public SmartFileManager(String path) {
        this.path = path;
    }

    @Override
    public File getFile(Id fileId){
        File myFile;
        if (fileId instanceof SmartID) {
            SmartID sid = (SmartID)fileId;
            String id = sid.getId();
            String filePath = path + id + ".meta";
            java.io.File file = new java.io.File(filePath);
            if (!file.exists()) {
                throw new ErrorCode(ErrorCode.NO_SUCH_FILE);
            } else {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String tmp;
                    tmp = br.readLine();
                    long fileSize = Long.parseLong(tmp.split(":")[1]);
                    tmp = br.readLine();
                    long blockSize = Long.parseLong(tmp.split(":")[1]);
                    br.readLine();
                    tmp = br.readLine();
                    ArrayList<Map<Id,Id>> LogicBlockList = new ArrayList<>();

                    while (tmp != null && !"".equals(tmp)) {
                        Map<Id,Id> map = new HashMap<>();
                        if (tmp.split(":").length == 1) {
                            map = Config.fileEmptyMap;
                        } else {
                            tmp = tmp.split(":")[1];

                            for (String s : tmp.split(" ")) {
                                String s1 = s.split(",")[0];
                                String s2 = s.split(",")[1];
                                map.put(new SmartID(s1.substring(s1.indexOf("\"")+1,s1.lastIndexOf("\""))),new SmartID(s2.substring(0,s2.indexOf("]"))));
                            }
                        }
                        LogicBlockList.add(map);
                        tmp = br.readLine();
                    }
                    myFile = new SmartFile(fileId,this,fileSize,blockSize,LogicBlockList,filePath);
                    return myFile;
                } catch (IOException e) {
                    throw new ErrorCode(ErrorCode.IO_EXCEPTION);
                }
            }
        }
        return null;
    }

    @Override
    public File newFile(Id fileId) {
        File myFile;
        if (fileId instanceof SmartID) {
            String id = ((SmartID) fileId).getId();
            java.io.File newFile = new java.io.File(path+id+".meta");

            if (newFile.exists()) {
                throw new ErrorCode(ErrorCode.FILE_ALREADY_EXISTED);
            } else {
                try {
                    newFile.createNewFile();
                    FileOutputStream out = new FileOutputStream(newFile,true);
                    StringBuffer sb = new StringBuffer();
                    sb.append("size:0\n");
                    sb.append("block size:"+BLOCK_SIZE+'\n');
                    sb.append("logic block:\n");
                    sb.append("0:\n");
                    out.write(sb.toString().getBytes(StandardCharsets.UTF_8));
                    out.close();
                } catch (IOException e) {
                    throw new ErrorCode(ErrorCode.IO_EXCEPTION);
                }
            }
            myFile = new SmartFile(fileId,this,0,BLOCK_SIZE,new ArrayList<>(),path+id+".meta");
            return myFile;
        }
        return null;
    }
}
