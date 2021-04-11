package main;

import id.SmartID;
import java.util.Scanner;

public class SmartFileSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to SmartFileSystem! ");
        System.out.println("Type in 'smart-help' to find how to use this system. ");
        while (true) {
            System.out.print(">");
            String command = scanner.nextLine();
            String op = command.split(" ")[0];
            String[] commandArray;
            try {
                switch (op) {
                    case "smart-cat":
                        commandArray = command.split(" ", 3);
                        if (commandArray.length == 3 && isFm(commandArray[1])) {
                            SmartFileUtil.smartCat(commandArray[1], commandArray[2]);
                        } else if (commandArray.length != 3) {
                            throw new ErrorCode(ErrorCode.WRONG_PARAMETER);
                        } else if (!isFm(commandArray[1])) {
                            throw new ErrorCode(ErrorCode.NO_FILE_MANAGER);
                        } else {
                            throw new ErrorCode(ErrorCode.COMMAND_ERROR);
                        }
                        break;
                    case "smart-catWithCursor":
                        commandArray = command.split(" ",4);
                        if (commandArray.length == 4 && isFm(commandArray[1]) && isNumber(commandArray[3])) {
                            SmartFileUtil.smartMoveCursorAndCat(commandArray[1], commandArray[2], Long.parseLong(commandArray[3]));
                        } else if (commandArray.length != 4) {
                            throw new ErrorCode(ErrorCode.WRONG_PARAMETER);
                        } else if (!isFm(commandArray[1])) {
                            throw new ErrorCode(ErrorCode.NO_FILE_MANAGER);
                        } else {
                            throw new ErrorCode(ErrorCode.COMMAND_ERROR);
                        }
                        break;
                    case "smart-getFileSize":
                        commandArray = command.split(" ",3);
                        if (commandArray.length == 3 && isFm(commandArray[1])) {
                            SmartFileUtil.smartGetFileSize(commandArray[1], commandArray[2]);
                        } else if (commandArray.length != 3) {
                            throw new ErrorCode(ErrorCode.WRONG_PARAMETER);
                        } else if (!isFm(commandArray[1])) {
                            throw new ErrorCode(ErrorCode.NO_FILE_MANAGER);
                        } else {
                            throw new ErrorCode(ErrorCode.COMMAND_ERROR);
                        }
                        break;
                    case "smart-hex":
                        commandArray = command.split(" ",2);
                        if (commandArray.length == 2  && isNumber(commandArray[1])) {
                            SmartFileUtil.smartHex(commandArray[1]);
                        } else if (commandArray.length != 2) {
                            throw new ErrorCode(ErrorCode.WRONG_PARAMETER);
                        } else if (!isBm(commandArray[1])) {
                            throw new ErrorCode(ErrorCode.NO_BLOCK_MANAGER);
                        } else {
                            throw new ErrorCode(ErrorCode.COMMAND_ERROR);
                        }
                        break;
                    case "smart-write":
                        commandArray = command.split(" ",4);
                        if (commandArray.length == 4 && isFm(commandArray[1]) && isNumber(commandArray[3])) {
                            if (Long.parseLong(commandArray[3]) < (long)0) {
                                throw new ErrorCode(ErrorCode.CURSOR_ERROR);
                            }
                            SmartFileUtil.smartWrite(commandArray[1], commandArray[2], Long.parseLong(commandArray[3]));
                        } else if (commandArray.length != 4) {
                            throw new ErrorCode(ErrorCode.WRONG_PARAMETER);
                        } else if (!isFm(commandArray[1])) {
                            throw new ErrorCode(ErrorCode.NO_FILE_MANAGER);
                        } else {
                            throw new ErrorCode(ErrorCode.COMMAND_ERROR);
                        }
                        break;
                    case "smart-copy":
                        commandArray = command.split(" ",5);
                        if (commandArray.length == 5 && isFm(commandArray[1]) && isFm(commandArray[3])){
                            SmartFileUtil.smartCopy(commandArray[1], commandArray[2], commandArray[3], commandArray[4]);
                        } else if (commandArray.length != 5) {
                            throw new ErrorCode(ErrorCode.WRONG_PARAMETER);
                        } else if (!isFm(commandArray[1]) || !isFm(commandArray[3])) {
                            throw new ErrorCode(ErrorCode.NO_FILE_MANAGER);
                        } else {
                            throw new ErrorCode(ErrorCode.COMMAND_ERROR);
                        }
                        break;
                    case "smart-quit":
                        SmartFileUtil.finish();
                        System.exit(0);
                        break;
                    case "smart-create":
                        commandArray = command.split(" ",3);
                        if (commandArray.length == 3) {
                            SmartFileUtil.smartCreate(commandArray[1], commandArray[2]);
                        } else {
                            throw new ErrorCode(ErrorCode.COMMAND_ERROR);
                        }
                        break;
                    case "smart-setSize":
                        commandArray = command.split(" ",4);
                        if (commandArray.length == 4 && isFm(commandArray[1]) && isNumber(commandArray[3])) {
                            if (Integer.parseInt(commandArray[3]) < 0) {
                                throw new ErrorCode(ErrorCode.COMMAND_ERROR);
                            }
                            SmartFileUtil.smartSetSize(commandArray[1], commandArray[2], Integer.parseInt(commandArray[3]));
                        } else if (commandArray.length != 4) {
                            throw new ErrorCode(ErrorCode.WRONG_PARAMETER);
                        } else if (!isFm(commandArray[1])) {
                            throw new ErrorCode(ErrorCode.NO_FILE_MANAGER);
                        } else {
                            throw new ErrorCode(ErrorCode.COMMAND_ERROR);
                        }
                        break;
                    case "smart-help":
                        SmartFileUtil.smartHelp();
                        break;
                    default:
                        throw new ErrorCode(ErrorCode.COMMAND_ERROR);
                }
            } catch (ErrorCode errorCode){
                System.out.println(errorCode.getErrorText(errorCode.getErrorCode()));
            }
        }
    }
    
    private static boolean isNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }
    
    private static boolean isFm(String str) {
        return Config.smartFileManagerMap.containsKey(new SmartID(str));
    }
    
    private static boolean isBm(String str) {
        return Config.smartBlockManagerMap.containsKey(new SmartID(str));
    }
}
