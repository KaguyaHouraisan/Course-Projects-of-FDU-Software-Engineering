package main;

import block.Block;
import file.File;
import id.Id;
import id.SmartID;
import manager.BlockManager;
import manager.FileManager;
import java.util.ArrayList;
import java.util.Scanner;

public class SmartFileUtil {
    private static ArrayList<File> openedFile = new ArrayList<>();
    private static File operatingFile;

    static void smartCat(String fileManagerName, String fileName) {
        FileManager fm = Config.smartFileManagerMap.get(new SmartID(fileManagerName));
        File file = fm.getFile(new SmartID(fileName));
        operatingFile = file;
        file.move(0, File.MOVE_HEAD);
        System.out.println(new String(file.read((int)file.size())));
    }

    static void smartMoveCursorAndCat(String fileManagerName, String fileName, long index) {
        FileManager fm = Config.smartFileManagerMap.get(new SmartID(fileManagerName));
        File file = fm.getFile(new SmartID(fileName));
        operatingFile = file;
        if (index >= operatingFile.size() || index < 0) {
            throw new ErrorCode(ErrorCode.CURSOR_ERROR);
        }
        file.move(index, File.MOVE_HEAD);
        System.out.println(new String(file.read((int)file.size() - (int)index)));
    }

    static void smartGetFileSize(String fileManagerName, String fileName) {
        FileManager fm = Config.smartFileManagerMap.get(new SmartID(fileManagerName));
        File file = fm.getFile(new SmartID(fileName));
        operatingFile = file;
        System.out.println("File size: " + file.size());
    }

    static void smartHex(String blockID) {
        Integer blockIndex = Integer.parseInt(blockID);
        boolean isExist = false;
        int checkErrorCount = 0;
        for (Id blockKey : Config.smartBlockManagerMap.keySet()) {
            BlockManager bm = Config.smartBlockManagerMap.get(blockKey);
            Block block = bm.getBlock(new SmartID(blockIndex + ""));
            if (block == null) {
                continue;
            }
            byte[] bytes = block.read();
            if (bytes == null) {
                checkErrorCount++;
                continue;
            }
            for (int i = 0; i < bytes.length; i++) {
                System.out.print("0x" + Integer.toHexString(bytes[i]) + " ");
                if (i % 16 == 15)
                    System.out.print("\n");
            }
            System.out.print("\n");
            isExist = true;
            break;
        }
        if (checkErrorCount >= Config.smartBlockManagerMap.size()) {
            throw new ErrorCode(ErrorCode.CHECKSUM_CHECK_FAILED);
        }
        if (!isExist) {
            throw new ErrorCode(ErrorCode.NO_SUCH_BLOCK);
        }
    }

    static void smartWrite(String fileManagerName, String fileName, long index) {
        FileManager fm = Config.smartFileManagerMap.get(new SmartID(fileManagerName));
        operatingFile = fm.getFile(new SmartID(fileName));
        if (operatingFile == null) {
            throw new ErrorCode(ErrorCode.NO_OPERATING_FILE);
        }
        if (index >= operatingFile.size()) {
            index = operatingFile.size();
        }
        operatingFile.move(index, File.MOVE_HEAD);
        System.out.println("Please type in what you want to write: ");

        Scanner in = new Scanner(System.in);
        String content = in.nextLine();

        operatingFile.write(content.getBytes());
        if (!openedFile.contains(operatingFile)) {
            openedFile.add(operatingFile);
        }
        System.out.println("File has been written successfully. ");
    }

    static void smartCopy(String fmNameFrom, String fileNameFrom, String fmNameTo, String fileNameTo) {
        FileManager fileManagerFrom = Config.smartFileManagerMap.get(new SmartID(fmNameFrom));
        File fileFrom = fileManagerFrom.getFile(new SmartID(fileNameFrom));
        FileManager fileManagerTo = Config.smartFileManagerMap.get(new SmartID(fmNameTo));
        File fileTo = fileManagerTo.newFile(new SmartID(fileNameTo));
        openedFile.add(fileTo);
        fileTo.write(fileFrom.read((int)fileFrom.size()));
        System.out.println("File has been copied successfully. ");
    }

    static void finish() {
        for (File f : openedFile){
            f.close();
        }
    }

    static void smartCreate(String fileManagerName, String fileName) {
        File file;
        FileManager fm = Config.smartFileManagerMap.get(new SmartID(fileManagerName));
        file = fm.newFile(new SmartID(fileName));
        operatingFile = file;
        System.out.println("File has been created successfully.");
    }

    static void smartSetSize(String fileManagerName, String fileName, int newSize) {
        FileManager fm = Config.smartFileManagerMap.get(new SmartID(fileManagerName));
        operatingFile = fm.getFile(new SmartID(fileName));
        if (operatingFile == null) {
            throw new ErrorCode(ErrorCode.NO_OPERATING_FILE);
        }
        operatingFile.setSize(newSize);
        System.out.println("File size has been modified successfully. ");
    }

    static void smartHelp() {
        System.out.print("smart-cat             [FileManagerName] [FileName]\n");
        System.out.print("smart-catWithCursor   [FileManagerName] [FileName] [Index]\n");
        System.out.print("smart-getFileSize     [FileManagerName] [FileName]\n");
        System.out.print("smart-hex             [BlockID]\n");
        System.out.print("smart-write           [FileManagerName] [FileName] [Index]\n");
        System.out.print("smart-copy            [FileManagerNameFrom] [FileNameFrom] [FileManagerNameTo] [FileNameTo]\n");
        System.out.print("smart-create          [FileManagerName] [FileName]\n");
        System.out.print("smart-setSize         [FileManagerName] [FileName] [Size]\n");
        System.out.print("smart-help            Help information will be printed.\n");
        System.out.print("smart-quit            Quit from this system.\n");
    }
}
