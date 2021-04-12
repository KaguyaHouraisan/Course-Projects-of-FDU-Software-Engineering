package Huffman;

import java.io.*;

public class DeCompressDistribute {
    private File input;
    private File output;
    private InputStream inputStream;
    private int x = 0;

    public DeCompressDistribute(String in, String out) throws IOException {
        String m = gettype(in);
        if (m.equals(".hzip")) {
            this.input = new File(in);
            this.output = new File(out);
            if (!input.exists()) {
                throw new IllegalArgumentException("Input file is not exist");
            } else {
                this.inputStream = new FileInputStream(input);
                distribute(input, output, 0, input.length());
                x = 1;
            }
        }
    }

    public String distribute(File input, File output, long CurrentLoctation, long NextLocation) throws IOException{
        if (!this.output.exists()){
           return "Outfolder is not exist";
        } else if (!this.output.isDirectory()){
            return "Outputpath is not a folder";
        } else {
            this.inputStream = new FileInputStream(input);
            inputStream.skip(CurrentLoctation);
            byte[] filenamelength = new byte[4];
            inputStream.read(filenamelength);
            int length = byte4ToInt(filenamelength, 0);
            if (length > 100000) {
                byte[] filesNumber = new byte[4];
                this.inputStream = new FileInputStream(input);
                inputStream.skip(NextLocation - 4);
                inputStream.read(filesNumber);
                int filesLocation = byte4ToInt(filesNumber,0);
                this.inputStream = new FileInputStream(input);
                inputStream.skip(NextLocation - 4 - filesLocation);
                long[] Locations = new long[filesLocation / 8];
                for (int i = 0; i < filesLocation / 8; i++){
                    byte[] Location = new byte[8];
                    inputStream.read(Location);
                    Locations[i] = BytesToLong(Location);
                }
                this.inputStream = new FileInputStream(input);
                inputStream.skip(4 + CurrentLoctation);
                byte[] filenumber = new byte[4];
                inputStream.read(filenumber);
                int filesnumber = byte4ToInt(filenumber,0);
                byte[] filename = new byte[length - 100000];
                inputStream.read(filename);
                String fileName = new String(filename);
                StringBuilder add = new StringBuilder(output.getAbsolutePath());
                add.append("/"+fileName);
                File create = new File(add.toString());
                if (!create.exists()){
                    create.mkdirs();
                }else {
                    throw new IllegalArgumentException("Folder does exist");
                }
                inputStream.close();
                for (int i = 0; i < filesnumber; i++) {
                    if (i == 0) {
                        distribute(this.input, create, (CurrentLoctation+8 + length - 100000), Locations[0]);
                        continue;
                    }
                    if (i < filesnumber - 1) {
                        distribute(this.input, create, Locations[i - 1], Locations[i + 1]);
                    } else {
                        distribute(this.input,create,Locations[i - 1],0);
                    }
                }
            } else {
                byte[] filename = new byte[length];
                inputStream.read(filename);
                StringBuilder add = new StringBuilder(output.getAbsolutePath());
                String addString = new String(filename);
                add.append("/" + addString);
                String createpath = add.toString();
                File create = new File(createpath);
                if (!create.exists()) {
                    create.createNewFile();
                }
                OutputStream outputStream = new FileOutputStream(create);
                FileDecompress fileDecompress = new FileDecompress(this.inputStream,outputStream);
                return "DeCompress Succeed";
            }
        }
        return "DeCompress failed";
    }

    private static int byte4ToInt(byte[] bytes, int off) {
        int b0 = bytes[off] & 0xFF;
        int b1 = bytes[off + 1] & 0xFF;
        int b2 = bytes[off + 2] & 0xFF;
        int b3 = bytes[off + 3] & 0xFF;
        return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
    }

    private String gettype(String in){
        int length = in.length();
        String type = "";
        for (int i = 1; i < length + 1; i++){
            char dot = in.charAt(length - i);
            if (dot=='.'){
                type = in.substring(length - i, length);
                break;
            }
        }
        return type;
    }

    private long BytesToLong(byte[] buffer) {
        long  values = 0;
        for (int i = 0; i < 8; i++) {
            values <<= 8; values |= (buffer[i] & 0xff);
        }
        return values;
    }

    public int getX() {
        return x;
    }
}
