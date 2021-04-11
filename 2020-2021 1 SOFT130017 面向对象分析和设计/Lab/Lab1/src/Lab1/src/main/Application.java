package main;

import commands.*;
import java.util.HashMap;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        StringText stringText = new StringText();
        UndoStack undoStack = new UndoStack();
        RedoStack redoStack = new RedoStack();
        Scanner scanner = new Scanner(System.in);
        HashMap<String, MacroCommand> macroCommandHashMap = new HashMap<>();
        String cmd;
        Command temp;
        int num;
        MacroCommand tempMacro;

        while(true) {
            cmd = scanner.nextLine();
            try {
                switch (cmd.split(" ")[0]) {
                    case "s":
                        stringText.showString();
                        break;
                    case "A":
                        if (redoStack.getSize() > 0) {
                            redoStack.clear();
                        }
                        temp = new AddStringToTailCommand(stringText, getContent(cmd));
                        temp.execute();
                        undoStack.push(temp);
                        stringText.showString();
                        break;
                    case "a":
                        if (redoStack.getSize() > 0) {
                            redoStack.clear();
                        }
                        temp = new AddStringToHeadCommand(stringText, getContent(cmd));
                        temp.execute();
                        undoStack.push(temp);
                        stringText.showString();
                        break;
                    case "D":
                        if (redoStack.getSize() > 0) {
                            redoStack.clear();
                        }
                        temp = new DeleteStringFromTailCommand(stringText, Integer.parseInt(getNumber(cmd)));
                        temp.execute();
                        undoStack.push(temp);
                        stringText.showString();
                        break;
                    case "d":
                        if (redoStack.getSize() > 0) {
                            redoStack.clear();
                        }
                        temp = new DeleteStringFromHeadCommand(stringText, Integer.parseInt(getNumber(cmd)));
                        temp.execute();
                        undoStack.push(temp);
                        stringText.showString();
                        break;
                    case "l":
                        undoStack.showList(Integer.parseInt(getNumber(cmd)));
                        System.out.print("\n");
                        break;
                    case "u":
                        temp = undoStack.pop();
                        redoStack.push(temp);
                        temp.unExecute();
                        stringText.showString();
                        break;
                    case "r":
                        temp = redoStack.pop();
                        undoStack.push(temp);
                        temp.execute();
                        stringText.showString();
                        break;
                    case "m":
                        num = Integer.parseInt(cmd.split(" ")[1]);
                        tempMacro = new MacroCommand(cmd.split(" ")[2], undoStack.getModifiedCommands(num));
                        macroCommandHashMap.put(cmd.split(" ")[2], tempMacro);
                        break;
                    case "q":
                        System.exit(0);
                        break;
                    default:
                        if (cmd.charAt(0) == '$') {
                            if (redoStack.getSize() > 0) {
                                redoStack.clear();
                            }
                            tempMacro = macroCommandHashMap.get(cmd.replace("$", ""));
                            tempMacro.execute();
                            undoStack.push(tempMacro);
                            stringText.showString();
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid command: " + e.getMessage());
            }
        }
    }

    private static String getContent(String cmd) {
        return cmd.split("\"")[1].replace("\n", "");
    }

    private static String getNumber(String cmd) {
        byte[] tempBytes = new byte[cmd.length() - 2];
        System.arraycopy(cmd.getBytes(), 2, tempBytes, 0, cmd.length() - 2);
        return new String(tempBytes);
    }
}
