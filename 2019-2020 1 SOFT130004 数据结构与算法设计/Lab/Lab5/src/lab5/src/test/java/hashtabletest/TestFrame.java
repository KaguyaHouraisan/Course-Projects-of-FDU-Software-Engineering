/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hashtabletest;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author tcolburn
 */
public class TestFrame extends JFrame {

    public TestFrame() {

        super("Hash Function Test");
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(550,450));

        add(new TestPanel());

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TestFrame();
    }

}
