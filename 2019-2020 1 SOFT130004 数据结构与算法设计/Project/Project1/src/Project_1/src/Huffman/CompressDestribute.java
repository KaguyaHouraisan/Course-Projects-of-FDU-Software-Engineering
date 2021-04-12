package Huffman;

import java.io.*;

public class CompressDestribute {
    private File output;
    private OutputStream outputStream;
    private String inputname;
    private File outFile;
    private int x;

    public CompressDestribute(String in, String out) throws IOException {
        File input = new File(in);
        output = new File(out);
        if (input == null) {
            throw new IllegalArgumentException("Input is empty");
        } else if (!input.exists()) {
            throw new IllegalArgumentException("Input does not exist");
        } else if (output == null) {
            throw new IllegalArgumentException("Output is empty");
        } else if (!output.exists()) {
            throw new IllegalArgumentException("Output is not exist");
        } else if (!output.isDirectory()) {
            throw new IllegalArgumentException("Output is illegal");
        } else {
            inputname = input.getName();
            String inputfilename = getname(inputname);
            String outfile = "";
            if (!input.isDirectory()) {
                outfile = CreateFile(out, inputfilename);
            }else {
                outfile = CreateFile(out, inputname);
            }
            outFile = new File(outfile);
            outputStream = new FileOutputStream(outFile,true);
            distribute(input,output);
            x = 1;
        }
    }

    public String distribute(File input, File output) throws IOException {
        if (input != null && input.exists() && input.isDirectory() && output != null && output.exists() && output.isDirectory()) {
            File[] files = input.listFiles();
            long[] filesnumber = new long[files.length];
            byte[] foldername = (inputname).getBytes();
            int namelength = foldername.length+100000;
            outputStream.write(intToByte4(namelength));
            int filenumber=files.length;
            outputStream.write(intToByte4(filenumber));
            outputStream.write(foldername);
            for (int i = 0; i < files.length; i++) {
                distribute(files[i], this.output);
              filesnumber[i] = this.outFile.length();
            }
            outputStream = new FileOutputStream(outFile,true);
            for (int i = 0; i < filesnumber.length; i++){
                byte[] fileBegin = LongToBytes(filesnumber[i]);
                System.out.println(BytesToLong(fileBegin));
                outputStream.write(fileBegin);
            }
            int filesNumberLength = filesnumber.length*8;
            byte[] fileEnd=intToByte4(filesNumberLength);
            outputStream.write(fileEnd);
            outputStream.close();
            return "Compress succeed";
        } else if (input != null && input.exists() && output != null && output.exists() && output.isDirectory()) {
            InputStream inputStream = new FileInputStream(input);
            String inputname = input.getName();
            byte[] l = (inputname).getBytes();
            int namelength = l.length;
            outputStream = new FileOutputStream(outFile,true);
            outputStream.write(intToByte4(namelength));
            outputStream.write(l);
            FileCompress fileCompress = new FileCompress(inputStream, outputStream);
            return "Compress succeed";
        } else if (input == null || !input.exists()){
            return "Input's path is illegal";
        } else if (output == null || !output.exists()){
            return "Output's path is illegal";
        } else {
            return "Compress failed";
        }
    }

    private static String CreateFile(String filename, String filepath) {
        Boolean bool = false;
        String filenametemp = filename +"/"+ filepath + ".hzip";
        File file = new File(filenametemp);
        try {
            if (!file.exists()) {
                file.createNewFile();
                bool = true;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Create failed");
        }
        if (bool) {
            return filenametemp;
        } else {
            return "Create failed";
        }
    }

    private static byte[] intToByte4(int i) {
        byte[] targets = new byte[4];
        targets[3] = (byte) (i & 0xFF);
        targets[2] = (byte) (i >> 8 & 0xFF);
        targets[1] = (byte) (i >> 16 & 0xFF);
        targets[0] = (byte) (i >> 24 & 0xFF);
        return targets;
    }

    private String getname(String in){
        int length = in.length();
        String type = "";
        for (int i = 1; i < length + 1; i++){
            char dot = in.charAt(length - i);
            if (dot == '.'){
                type = in.substring(0, length - i);
                break;
            }
        }
        return type;
    }

    private byte[] LongToBytes(long values) {
        byte[] buffer = new byte[8];
        for (int i = 0; i < 8; i++) {
            int offset = 64 - (i + 1) * 8;
            buffer[i] = (byte) ((values >> offset) & 0xff);
        }
        return buffer;
    }

    private long BytesToLong(byte[] buffer) {
        long  values = 0;
        for (int i = 0; i < 8; i++) {
            values <<= 8; values|= (buffer[i] & 0xff);
        }
        return values;
    }

    public int getX() {
        return x;
    }
}
