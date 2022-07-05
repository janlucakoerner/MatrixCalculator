package gui;

import parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;

/**
 * This class creates a panel to be added for each matrix calculation.
 * The panel comes with a textfield for inline matrix declaration,
 * a button to open a dialog with a matrix view and a button which loads
 * the matrix from a csv file.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/04)
 */
public class MatrixPanel extends JPanel {
    private final MatrixPanel instance = this;
    private JLabel label;
    private JTextField textField_inlineMatrix;
    private JButton button_openMatrixDialog;
    private JButton button_openEditMatrixDialog;
    private JButton button_loadMatrixCSV;
    private GridBagConstraints gbc;
    private BigDecimal[][] matrix;
    public MatrixPanel(JFrame parent) {
        label = new JLabel();
        textField_inlineMatrix = new JTextField();
        button_openMatrixDialog = new JButton();
        button_openEditMatrixDialog = new JButton();
        button_loadMatrixCSV = new JButton();

        label.setText("Matrix:");

        textField_inlineMatrix.setColumns(30);

        button_openMatrixDialog.setText("Open Matrix");
        button_openMatrixDialog.addActionListener(e-> {
            // todo: implement
            matrix = Parser.getMatrixFromInline(textField_inlineMatrix.getText());
            var openMatrixGui = new OpenMatrixGUI(this, matrix);
        });

        button_openEditMatrixDialog.setText("Open Edit Matrix");
        button_openEditMatrixDialog.addActionListener(e -> {
            matrix = Parser.getMatrixFromInline(textField_inlineMatrix.getText());
            if (matrix == null) {
                JOptionPane.showMessageDialog(null, "Matrix does not contain data!",
                        "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                return;
            } else if (matrix[0] == null) {
                JOptionPane.showMessageDialog(null, "Matrix does not contain data!",
                        "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            parent.setVisible(false);
            var rowCount = matrix.length;
            var columnCount = matrix[0].length;

            var frame = new JFrame();
            var panel = new JPanel();
            var button_close = new JButton();
            var textFields_matrix = new JTextField[rowCount * columnCount];

            for (int i = 0; i < rowCount * columnCount; i++) {
                textFields_matrix[i] = new JTextField();
                panel.add(textFields_matrix[i]);
            }

            var x = 0;
            for (BigDecimal[] rows : matrix) {
                for (BigDecimal value : rows) {
                    textFields_matrix[x].setText(value.toString());
                    x++;
                }
            }

            panel.setLayout(new GridLayout(rowCount, columnCount));
            button_close.setText("Close");
            button_close.addActionListener(e1 -> {
                frame.setVisible(false);
                parent.setVisible(true);
                var y = 0;
                for (int row = 0; row < rowCount; row++) {
                    for (int column = 0; column < columnCount; column++) {
                        try {
                            matrix[row][column] = new BigDecimal(textFields_matrix[y].getText());
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null, "Matrix could not be parsed!",
                                    "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        }
                        y++;
                    }
                }
                updateInline(matrix);
            });

            frame.add(panel, BorderLayout.CENTER);
            frame.add(button_close, BorderLayout.SOUTH);
            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
            frame.setVisible(true);
        });

        button_loadMatrixCSV.setText("Load Matrix");
        button_loadMatrixCSV.addActionListener(e -> {
            // show file chooser to pick a csv file
            setVisible(false);
            var fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            parent.setVisible(false);
            var state = fileChooser.showOpenDialog(parent);
            parent.setVisible(true);
            if (state == JFileChooser.APPROVE_OPTION) {
                var file = fileChooser.getSelectedFile();
                var extension = file.getName().substring(file.getName().lastIndexOf('.'));
                if (extension.equalsIgnoreCase(".csv")) {
                    try {
                        loadMatrixCSV(file);
                    } catch (FileNotFoundException fnfe) {
                        JOptionPane.showMessageDialog(null,"Selected file does not exists!",
                                "File Error", JOptionPane.ERROR_MESSAGE, null);
                    } catch (IOException io) {
                        JOptionPane.showMessageDialog(null,"Selected file could not be read!",
                                "File Error", JOptionPane.ERROR_MESSAGE, null);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "The selected file is no csv file!", "File Error", JOptionPane.ERROR_MESSAGE,
                            null);
                }
            }
            setVisible(true);
        });

        setLayout(new GridBagLayout());
        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(label, gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField_inlineMatrix, gbc);
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(button_openMatrixDialog, gbc);
        gbc.gridx = 3;
        add(button_openEditMatrixDialog, gbc);
        gbc.gridx = 4;
        add(button_loadMatrixCSV, gbc);
    }
    public void setEditable(boolean editable) {
        textField_inlineMatrix.setEditable(editable);
    }
    public void setText(String text) {
        label.setText(text);
    }
    private void updateInline(BigDecimal[][] matrix) {
        textField_inlineMatrix.setText(Parser.toString(matrix));
    }
    private void loadMatrixCSV(File file) throws IOException {
        var reader = new BufferedReader(new FileReader(file));
        var lines = reader.lines().toList();
        reader.close();
        var rowsCount = lines.size();
        String[][] string_matrix = new String[rowsCount][];
        if (lines.size() == 0) {
            JOptionPane.showMessageDialog(null,"CSV file does not contain data!",
                    "Parse Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        var columnCount = lines.get(0).split(";").length;
        for (int i = 0; i < rowsCount; i++) {
            string_matrix[i] = lines.get(i).split(";");
            if (columnCount != string_matrix[i].length) {
                JOptionPane.showMessageDialog(null,"Matrix does not have the same column count!",
                        "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
        }
        matrix = new BigDecimal[rowsCount][columnCount];
        for (int row = 0; row < rowsCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                try {
                    matrix[row][column] = new BigDecimal(string_matrix[row][column]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null,"One ore more values in the CSV file are not parsable!",
                            "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
            }
        }
        textField_inlineMatrix.setText(Parser.toString(matrix));
    }
}
