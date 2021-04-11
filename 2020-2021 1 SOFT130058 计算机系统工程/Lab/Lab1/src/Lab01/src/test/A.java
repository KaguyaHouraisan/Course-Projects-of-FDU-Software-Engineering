package test;

import main.ErrorCode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class A {
    public static void main(String[] args) {
        String blockManagerName;
        String blockManagerAddress;
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/BlockManagerList.info"));
            String tmp = br.readLine();
            while (tmp != null) {
                blockManagerName = tmp.split(" ")[0];
                blockManagerAddress = tmp.split(" ")[1];
                System.out.println(blockManagerName + " " +blockManagerAddress);
                tmp = br.readLine();
            }
        } catch (IOException e) {
            throw new ErrorCode(ErrorCode.IO_EXCEPTION);
        }
    }
}
