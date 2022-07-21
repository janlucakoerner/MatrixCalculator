import gui.arithmeticoperations.MatrixAdditionGUI;
import gui.base.MatrixPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Main class of the software project MatrixCalculation.
 * This software project is my final project for the CS50 course.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/04)
 */
public class Main {
    public static void main(String[] args) {
        /*
        var frame = new JFrame();
        frame.add(new MatrixPanel(frame));
        frame.pack();
        frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

         */
        new MatrixAdditionGUI();
    }
}
