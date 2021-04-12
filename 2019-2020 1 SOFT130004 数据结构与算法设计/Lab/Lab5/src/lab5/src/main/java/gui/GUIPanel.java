package gui;

import generator.KeyGenerator;
import hashtable.HashTable;
import hashtable.HashTableElement;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This class provides a GUI for testing hash functions.
 * @author tcolburn
 */
public class GUIPanel extends JPanel {

    /**
     * Creates a GUI for testing hash tables of a given capacity.
     * @param cap the capacity of the hash table being tested
     */
    public GUIPanel() {

//        keyGenerator = new AdHocIntegerGenerator();
//        nextKey = keyGenerator.getNextKey();

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        size = 0;

        capacityLabel = makeBorderedLabel(HashTable.CAPACITY + "", "Capacity");

        sizeLabel = makeBorderedLabel(size + "", "Number of Elements");

        JPanel sizePanel = new JPanel();
        sizePanel.add(capacityLabel);
        sizePanel.add(sizeLabel);

        JLabel label = new JLabel("Chain Lengths");

        h = new HashTable();

        table = new JTable(new AbstractTableModel() {

            public int getColumnCount() {
                return 1;
            }

            public int getRowCount() {
                return HashTable.CAPACITY;
            }

            public Object getValueAt(int row, int col) {
                return h.getChainLengths().get(row);
            }
        });

        selectionModel = table.getSelectionModel();

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getCellRenderer(0,0);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        meanLabel = makeBorderedLabel(h.meanChainLength() + "", "Mean Length");

        devLabel = makeBorderedLabel(h.standardDeviation() + "", "Standard Deviation");

        JPanel statPanel = new JPanel();
        statPanel.add(meanLabel);
        statPanel.add(devLabel);

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                HashTableElement element = new HashTableElement(nextKey, null);
                h.insert(element);
                int index = h.getIndex();
                selectionModel.setSelectionInterval(index, index);
                size++;
                nextKey = keyGenerator.getNextKey();
                updateLabels();
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(insertButton);
        controlPanel.add(clearButton);

        nextKeyLabel = makeBorderedLabel(nextKey + "", "Next Key");

        JPanel nextPanel = new JPanel();
        nextPanel.add(nextKeyLabel);

        add(sizePanel);
        add(label);
        add(table);
        add(statPanel);
        add(nextPanel);
        add(controlPanel);

        guiPanel = this;
    }

    public void clear() {
        int index = h.getIndex();
        selectionModel.removeSelectionInterval(index, index);
        h.clear();
//        keyGenerator.reset();
//        nextKey = keyGenerator.getNextKey();
        size = 0;
        updateLabels();
    }

    public void setKeyGenerator(KeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
        nextKey = keyGenerator.getNextKey();
        updateLabels();
    }


    /**
     * Makes a standard size JLabel component with a given text and
     * border title
     * @param text the label's displayed text
     * @param title the title for the border surrounding the label
     * @return the bordered label
     */
    private JLabel makeBorderedLabel(String text, String title) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(150,50));
        label.setBorder(new TitledBorder(title));
        return label;
    }

    /**
     * Updates the text of labels that change during the running of
     * the test.
     */
    private void updateLabels() {
        sizeLabel.setText(size + "");
        meanLabel.setText(truncate(h.meanChainLength()) + "");
        devLabel.setText(truncate(h.standardDeviation()) + "");
        nextKeyLabel.setText(nextKey + "");
        guiPanel.repaint();
    }

    /**
     * Truncates a double value for display as XX.XXX
     * @param n the double value to be truncated
     * @return the truncated double
     */
    private double truncate(double n) {
        return Math.round(n * 1000.0)/1000.0;
    }

    private HashTable h;
    private int size;

    private JLabel capacityLabel;
    private JLabel sizeLabel;
    private JLabel meanLabel;
    private JLabel devLabel;
    private JLabel nextKeyLabel;

    private Object nextKey;

    private JTable table;

    private ListSelectionModel selectionModel;

    private JPanel guiPanel;

    private KeyGenerator keyGenerator;
}
