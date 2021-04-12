import Huffman.CompressDestribute;
import Huffman.DeCompressDistribute;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;

public class Main extends JFrame implements ActionListener {
    private static JButton ZH, UH, EX;
    private static File opened_file, write_file;
    private static JLabel firstScore, secondScore, thirdScore, forthScore, fifthScore, sixthScore;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private JPanel createContentPane() {
        JButton openFiles;
        JLabel firstLabel, secondLabel, thirdLabel, forthLabel, fifthLabel;
        JPanel buttonPanel, titlePanel, scorePanel;

        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setLocation(0, 30);
        titlePanel.setSize(300, 190);
        totalGUI.add(titlePanel);

        firstLabel = new JLabel("Name of File: ");
        firstLabel.setLocation(110, 0);
        firstLabel.setSize(160, 30);
        firstLabel.setHorizontalAlignment(0);
        titlePanel.add(firstLabel);

        secondLabel = new JLabel("Size of File Decompressed: ");
        secondLabel.setLocation(110, 30);
        secondLabel.setSize(160, 30);
        secondLabel.setHorizontalAlignment(0);
        titlePanel.add(secondLabel);

        thirdLabel = new JLabel("Size of File Compressed: ");
        thirdLabel.setLocation(110, 60);
        thirdLabel.setSize(160, 30);
        thirdLabel.setHorizontalAlignment(0);
        titlePanel.add(thirdLabel);

        forthLabel = new JLabel("Compression Ratio: ");
        forthLabel.setLocation(110, 90);
        forthLabel.setSize(160, 30);
        forthLabel.setHorizontalAlignment(0);
        titlePanel.add(forthLabel);

        fifthLabel = new JLabel("Time: ");
        fifthLabel.setLocation(110, 120);
        fifthLabel.setSize(160, 30);
        fifthLabel.setHorizontalAlignment(0);
        titlePanel.add(fifthLabel);

        openFiles = new JButton("Destination: ");
        openFiles.setLocation(150, 150);
        openFiles.setSize(120, 30);
        titlePanel.add(openFiles);

        scorePanel = new JPanel();
        scorePanel.setLayout(null);
        scorePanel.setLocation(300, 30);
        scorePanel.setSize(300, 190);
        totalGUI.add(scorePanel);

        firstScore = new JLabel("");
        firstScore.setLocation(30, 0);
        firstScore.setSize(180, 30);
        firstScore.setHorizontalAlignment(0);
        scorePanel.add(firstScore);

        secondScore = new JLabel("");
        secondScore.setLocation(30, 30);
        secondScore.setSize(180, 30);
        secondScore.setHorizontalAlignment(0);
        scorePanel.add(secondScore);

        thirdScore = new JLabel("");
        thirdScore.setLocation(30, 60);
        thirdScore.setSize(180, 30);
        thirdScore.setHorizontalAlignment(0);
        scorePanel.add(thirdScore);

        forthScore = new JLabel("");
        forthScore.setLocation(30, 90);
        forthScore.setSize(180, 30);
        forthScore.setHorizontalAlignment(0);
        scorePanel.add(forthScore);

        fifthScore = new JLabel("");
        fifthScore.setLocation(30, 120);
        fifthScore.setSize(180, 30);
        fifthScore.setHorizontalAlignment(0);
        scorePanel.add(fifthScore);

        sixthScore = new JLabel("");
        sixthScore.setLocation(30, 150);
        sixthScore.setSize(180, 30);
        sixthScore.setHorizontalAlignment(0);
        scorePanel.add(sixthScore);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(0, 230);
        buttonPanel.setSize(600, 90);
        totalGUI.add(buttonPanel);

        ZH = new JButton("Compress");
        ZH.setLocation(150, 0);
        ZH.setSize(120, 30);
        ZH.addActionListener(this);
        ZH.setEnabled(false);
        buttonPanel.add(ZH);

        UH = new JButton("Decompress");
        UH.setLocation(330, 0);
        UH.setSize(120, 30);
        UH.addActionListener(this);
        UH.setEnabled(false);
        buttonPanel.add(UH);

        EX = new JButton("EXIT");
        EX.setLocation(150, 50);
        EX.setSize(300, 30);
        EX.addActionListener(this);
        buttonPanel.add(EX);

        openFiles.addActionListener(
                e -> {
                    JFileChooser jFileChooser = new JFileChooser();
                    jFileChooser.setFileSelectionMode(jFileChooser.DIRECTORIES_ONLY);
                    jFileChooser.showOpenDialog(null);
                    write_file = jFileChooser.getSelectedFile();
                    sixthScore.setText(write_file.getPath());
                }
        );
        totalGUI.setOpaque(true);
        return totalGUI;
    }

    public void actionPerformed(ActionEvent a) {
        File other_file;
        long future;
        DecimalFormat df = new DecimalFormat("#.00");
        if (a.getSource() == ZH) {
            try {
                if (opened_file.isDirectory()) {
                    long startTime = System.currentTimeMillis();
                    if (opened_file.listFiles().length != 0) {
                        CompressDestribute compressDestribute = new CompressDestribute(opened_file.getPath(), write_file.getPath());
                        long endTime = System.currentTimeMillis();
                        if (compressDestribute.getX() == 1) {
                            JOptionPane.showMessageDialog(null,
                                    "........................Compressing Finished........................",
                                    "Status", JOptionPane.PLAIN_MESSAGE);
                            secondScore.setText(getFileSize(opened_file) + "Bytes");
                            other_file = new File(write_file.getPath() + "/" + opened_file.getName() + ".hzip");
                            future = other_file.length();
                            thirdScore.setText(future + "Bytes");
                            fifthScore.setText((endTime - startTime) + " ms");
                            if (opened_file.length() > 0) {
                                System.out.println(future / opened_file.length());
                                forthScore.setText(df.format((double) future / getFileSize(opened_file) * 100) + "%");
                            } else {
                                forthScore.setText("∞");
                            }
                        }
                    } else {
                        File file = new File(write_file.getPath() + "/" + opened_file.getName() + ".hzip");
                        file.createNewFile();
                        Writer out = new FileWriter(file);
                        out.write("d");
                        out.close();
                        long endTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null,
                                "........................Compressing Finished........................",
                                "Status", JOptionPane.PLAIN_MESSAGE);
                        secondScore.setText("0 Bytes");
                        thirdScore.setText(file.length() + "Bytes");
                        forthScore.setText("∞");
                        fifthScore.setText((endTime - startTime) + " ms");
                    }
                } else {
                    long startTime = System.currentTimeMillis();
                    if (opened_file.length() != 0) {
                        CompressDestribute compressDestribute = new CompressDestribute(opened_file.getPath(), write_file.getPath());
                        long endTime = System.currentTimeMillis();
                        if (compressDestribute.getX() == 1) {
                            JOptionPane.showMessageDialog(null,
                                    "........................Compressing Finished........................",
                                    "Status", JOptionPane.PLAIN_MESSAGE);
                            secondScore.setText(opened_file.length() + "Bytes");
                            other_file = new File(write_file.getPath() + "/" + opened_file.getName().substring(0, opened_file.getName().lastIndexOf(".")) + ".hzip");
                            future = other_file.length();
                            thirdScore.setText(future + "Bytes");
                            fifthScore.setText((endTime - startTime) + " ms");
                            if (opened_file.length() > 0) {
                                System.out.println(future / opened_file.length());
                                forthScore.setText(df.format((double) future / opened_file.length() * 100) + "%");
                            } else {
                                forthScore.setText("∞");
                            }
                        }
                    } else {
                        File file = new File(write_file.getPath() + "/" + opened_file.getName() + ".hzip");
                        file.createNewFile();
                        Writer out = new FileWriter(file);
                        out.write("f");
                        out.close();
                        long endTime = System.currentTimeMillis();
                        JOptionPane.showMessageDialog(null,
                                "........................Compressing Finished........................",
                                "Status", JOptionPane.PLAIN_MESSAGE);
                        secondScore.setText("0 Bytes");
                        thirdScore.setText(file.length() + "Bytes");
                        forthScore.setText("∞");
                        fifthScore.setText((endTime - startTime) + " ms");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (a.getSource() == UH) {
            try {
                long startTime = System.currentTimeMillis();
                if (opened_file.length() > 1) {
                    DeCompressDistribute compressDestribute = new DeCompressDistribute(opened_file.getPath(), write_file.getPath());
                    long endTime = System.currentTimeMillis();
                    if (compressDestribute.getX() == 1) {
                        JOptionPane.showMessageDialog(null,
                                ".......................Decompressing Finished.......................",
                                "Status", JOptionPane.PLAIN_MESSAGE);
                        thirdScore.setText(opened_file.length() + "Bytes");
                        secondScore.setText("");
                        forthScore.setText("");
                        fifthScore.setText((endTime - startTime) + " ms");
                    }
                } else if (opened_file.length() == 1) {
                    InputStreamReader reader = new InputStreamReader(new FileInputStream(opened_file));
                    BufferedReader br = new BufferedReader(reader);
                    String line = br.readLine();
                    if (line.equals("f")) {
                        File file = new File(write_file.getPath() + "/" + opened_file.getName().substring(0, opened_file.getName().lastIndexOf(".")));
                        file.createNewFile();
                    } else if (line.equals("d")) {
                        File file = new File(write_file.getPath() + "/" + opened_file.getName().substring(0, opened_file.getName().lastIndexOf(".")));
                        file.mkdir();
                    }
                    long endTime = System.currentTimeMillis();
                    JOptionPane.showMessageDialog(null,
                            ".......................Decompressing Finished.......................",
                            "Status", JOptionPane.PLAIN_MESSAGE);
                    thirdScore.setText("1 Bytes");
                    secondScore.setText("");
                    forthScore.setText("");
                    fifthScore.setText((endTime - startTime) + " ms");
                } else {
                    JOptionPane.showMessageDialog(null,
                            ".......................Damaged File.......................",
                            "Status", JOptionPane.PLAIN_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (a.getSource() == EX) {
            System.exit(0);
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("File Compression Utility");
        Main demo = new Main();

        frame.setContentPane(demo.createContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 200, 600, 410);
        frame.setVisible(true);
        frame.setResizable(false);

        JMenu fileMenu = new JMenu("File");
        JMenuBar bar = new JMenuBar();
        frame.setJMenuBar(bar);
        bar.add(fileMenu);

        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);

        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFileChooser jc = new JFileChooser();
                jc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jc.showOpenDialog(null);
                opened_file = jc.getSelectedFile();
                firstScore.setText(opened_file.getName());
                if (!sixthScore.getText().equals("")) {
                    if ((opened_file.getName().substring(opened_file.getName().lastIndexOf(".") + 1)).equals("hzip")) {
                        ZH.setEnabled(true);
                        UH.setEnabled(true);
                        secondScore.setText("");
                        thirdScore.setText(opened_file.length() + "Bytes");
                        forthScore.setText("");
                        fifthScore.setText("");
                    } else {
                        ZH.setEnabled(true);
                        if (opened_file.isDirectory()) {
                            secondScore.setText(getFileSize(opened_file) + "Bytes");
                        } else {
                            secondScore.setText(opened_file.length() + "Bytes");
                        }
                        thirdScore.setText("");
                        forthScore.setText("");
                        fifthScore.setText("");
                    }
                }
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        JMenu helpMenu = new JMenu("Help");
        frame.setJMenuBar(bar);
        bar.add(helpMenu);

        JMenuItem helpItem = new JMenuItem("How To");
        helpMenu.add(helpItem);
        helpItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(null,
                        "The use of this product is very simple. After opening this product, you should first\n " +
                                "click the \"destination\" button, select the directory you want to compress / decompress, \n" +
                                "and then click the \"file open\" button to select the file you want to compress / decompress. \n" +
                                "When both of them have been selected, the product will automatically determine the file format. \n" +
                                "For non \". Hzip\" files, only the \"compress\" button is available. Click it to compress the \n" +
                                "selected files to the directory you choose. Otherwise, for the \". Hzip\" file, the \"decompress\" \n" +
                                "button is also available, and you can click it to decompress the file. Click \"exit\" or \"file \n" +
                                "exit\" to exit the product. Click the \"help how to\" button to open this user manual. The main \n" +
                                "interface will display the file name, size, path, compression / decompression time and other \n" +
                                "information. After compression, information such as compressed size and compression ratio will \n" +
                                "also be displayed.",
                        "How To", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    private static long getFileSize(File f){
        long size = 0;
        if (f.isDirectory()){
            File flist[] = f.listFiles();
            if (flist.length != 0) {
                for (int i = 0; i < flist.length; i++) {
                    if (flist[i].isDirectory()) {
                        size = size + getFileSize(flist[i]);
                    } else {
                        size = size + flist[i].length();
                    }
                }
            }
        } else {
            size = size + f.length();
        }
        return size;
    }
}

