package main;

public class StringText {
    private String operatingStr;

    StringText() {
        operatingStr = "";
    }

    void showString() {
        System.out.println(operatingStr + "\n");
    }

    public void addStringToHead(String add) {
        operatingStr = add + operatingStr;
    }

    public void addStringToTail(String add) {
        operatingStr = operatingStr + add;
    }

    public String deleteStringFromHead(int size) {
        if (size > operatingStr.length()) {
            size = operatingStr.length();
        }
        byte[] bytes = operatingStr.getBytes();
        byte[] temp = new byte[bytes.length - size];
        byte[] deleted = new byte[size];
        System.arraycopy(bytes, size, temp, 0, bytes.length - size);
        System.arraycopy(bytes, 0, deleted, 0, size);
        operatingStr = new String(temp);
        return new String(deleted);
    }

    public String deleteStringFromTail(int size) {
        if (size > operatingStr.length()) {
            size = operatingStr.length();
        }
        byte[] bytes = operatingStr.getBytes();
        byte[] temp = new byte[bytes.length - size];
        byte[] deleted = new byte[size];
        System.arraycopy(bytes, 0, temp, 0, bytes.length - size);
        System.arraycopy(bytes, bytes.length - size, deleted, 0, size);
        operatingStr = new String(temp);
        return new String(deleted);
    }
}
