package frontend.base.panel;

import backend.ParserBigDecimal;
import backend.ParserFraction;
import frontend.base.frame.OpenVectorGUI;
import middleware.base.DataType;
import middleware.base.Fraction;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;

/**
 * This class creates a panel to be added for each vector calculation.
 * The panel comes with a text field for inline vector declaration,
 * a button to open a dialog with a vector view and a button which loads
 * the vector from a csv file.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/04)
 */
public class VectorPanel extends JPanel {
    private final JLabel label;
    private final JTextField textField_inlineMatrix;
    private Object[] vector;
    /**
     * Constructor which creates a JPanel customized for vectors.
     */
    public VectorPanel(JFrame parent) {
        label = new JLabel();
        textField_inlineMatrix = new JTextField();
        var button_openMatrixDialog = new JButton();
        var button_openEditMatrixDialog = new JButton();
        var button_loadMatrixCSV = new JButton();

        label.setText("Vector:");

        textField_inlineMatrix.setColumns(30);

        button_openMatrixDialog.setText("Open Vector");
        button_openMatrixDialog.addActionListener(e-> {
            if (DataType.current == DataType.BIG_DECIMAL) {
                vector = ParserBigDecimal.getVectorFromInline(textField_inlineMatrix.getText());
                new OpenVectorGUI(parent, vector);
            } else if (DataType.current == DataType.FRACTION) {
                vector = ParserFraction.getMatrixFromInline(textField_inlineMatrix.getText());
                new OpenVectorGUI(parent, vector);
            }
        });

        //--------------------------------------------------------------------------------------------------------------

        button_openEditMatrixDialog.setText("Open Edit Vector");
        button_openEditMatrixDialog.addActionListener(e -> {
            if (DataType.current == DataType.BIG_DECIMAL)
                vector = ParserBigDecimal.getVectorFromInline(textField_inlineMatrix.getText());
            else if (DataType.current == DataType.FRACTION)
                vector = ParserFraction.getVectorFromInline(textField_inlineMatrix.getText());
            if (vector == null) {
                var frame = new JFrame();
                var panel = new JPanel();
                var button = new JButton();
                var textField_rowCount = new JTextField();

                parent.setVisible(false);

                panel.setLayout(new GridLayout(1,2));
                panel.add(new JLabel("Rows"));
                panel.add(textField_rowCount);

                button.setText("Create Vector");
                button.addActionListener(e1 -> {
                    // open matrix create dialog
                    try {
                        var rowCount = Integer.parseInt(textField_rowCount.getText());
                        if (rowCount <= 1) {
                            JOptionPane.showMessageDialog(null, "Row count is not a valid number",
                                    "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        }
                        frame.setVisible(false);

                        if (DataType.current == DataType.BIG_DECIMAL)
                            vector = new BigDecimal[rowCount];
                        else if (DataType.current == DataType.FRACTION)
                            vector = new Fraction[rowCount];

                        var frame_matrix = new JFrame();
                        var panel_matrix = new JPanel();
                        var button_close = new JButton();
                        var textFields_matrix = new JTextField[rowCount];

                        for (int i = 0; i < rowCount; i++) {
                            textFields_matrix[i] = new JTextField();
                            panel_matrix.add(textFields_matrix[i]);
                        }

                        panel_matrix.setLayout(new GridLayout(rowCount, 1));
                        button_close.setText("Close");
                        button_close.addActionListener(e2 -> {
                            frame_matrix.setVisible(false);
                            parent.setVisible(true);
                            var y = 0;
                            for (int row = 0; row < rowCount; row++) {
                                try {
                                    if (DataType.current == DataType.BIG_DECIMAL)
                                        vector[row] = new BigDecimal(textFields_matrix[y].getText());
                                    else if (DataType.current == DataType.FRACTION) {
                                        vector[row] = new Fraction(textFields_matrix[y].getText());
                                    }
                                } catch (NumberFormatException nfe) {
                                    JOptionPane.showMessageDialog(null, "Matrix could not be parsed!",
                                            "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                                    return;
                                }
                                y++;
                            }
                            if (DataType.current == DataType.BIG_DECIMAL)
                                updateInline((BigDecimal[]) vector);
                            else if (DataType.current == DataType.FRACTION)
                                updateInline((Fraction[]) vector);
                        });

                        frame_matrix.add(panel_matrix, BorderLayout.CENTER);
                        frame_matrix.add(button_close, BorderLayout.SOUTH);
                        frame_matrix.pack();
                        frame_matrix.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        frame_matrix.setMinimumSize(new Dimension(frame_matrix.getWidth(), frame_matrix.getHeight()));
                        frame_matrix.setVisible(true);
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Row or column count is not a valid number",
                                "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                    }
                });

                frame.add(panel, BorderLayout.CENTER);
                frame.add(button, BorderLayout.SOUTH);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.pack();
                frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
                frame.setVisible(true);
                return;
            }
            // open matrix edit dialog
            var rowCount = vector.length;
            parent.setVisible(false);

            var frame = new JFrame();
            var panel = new JPanel();
            var button_close = new JButton();
            var textFields_matrix = new JTextField[rowCount];

            for (int i = 0; i < rowCount; i++) {
                textFields_matrix[i] = new JTextField();
                panel.add(textFields_matrix[i]);
            }

            var x = 0;
            if (DataType.current == DataType.BIG_DECIMAL) {
                for (BigDecimal rows : (BigDecimal[]) vector) {
                    textFields_matrix[x].setText(rows.toString());
                    x++;
                }
            } else if (DataType.current == DataType.FRACTION) {
                for (Fraction rows : (Fraction[]) vector) {
                    textFields_matrix[x].setText(rows.toString());
                    x++;
                }
            }

            panel.setLayout(new GridLayout(rowCount, 1));
            button_close.setText("Close");
            button_close.addActionListener(e1 -> {
                frame.setVisible(false);
                parent.setVisible(true);
                var y = 0;
                for (int row = 0; row < rowCount; row++) {
                    try {
                        if (DataType.current == DataType.BIG_DECIMAL)
                            vector[row] = new BigDecimal(textFields_matrix[y].getText());
                        else if (DataType.current == DataType.FRACTION) {
                            vector[row] = new Fraction(textFields_matrix[y].getText());
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Matrix could not be parsed!",
                                "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                    y++;
                }
                if (DataType.current == DataType.BIG_DECIMAL)
                    updateInline((BigDecimal[]) vector);
                else if (DataType.current == DataType.FRACTION)
                    updateInline((Fraction[]) vector);
            });

            frame.add(panel, BorderLayout.CENTER);
            frame.add(button_close, BorderLayout.SOUTH);
            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
            frame.setVisible(true);
        });

        //--------------------------------------------------------------------------------------------------------------

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

        //--------------------------------------------------------------------------------------------------------------

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
    /**
     * This method sets the inline textfield editable or not.
     * @param editable Boolean value which represent the editable state.
     */
    public void setEditable(boolean editable) {
        textField_inlineMatrix.setEditable(editable);
    }
    /**
     * This method sets the text of the JLabel.
     * @param text The text which should be displayed in the JLabel.
     */
    public void setText(String text) {
        label.setText(text);
    }
    /**
     * This method returns the inline vector of the textfield.
     * @return The inline vector of the textfield.
     */
    public String getInline() {
        return textField_inlineMatrix.getText();
    }
    /**
     * This method updates the inline textfield by BigDecimal values.
     * @param vector The vector as a BigDecimal array
     */
    public void updateInline(BigDecimal[] vector) {
        textField_inlineMatrix.setText(ParserBigDecimal.vectorToString(vector));
    }
    /**
     * This method updates the inline textfield by Fraction values.
     * @param vector The vector as a Fraction array
     */
    public void updateInline(Fraction[] vector) {
        textField_inlineMatrix.setText(ParserFraction.vectorToString(vector));
    }
    /**
     * This method loads the vector from a csv file.
     * @param file The csv file selected by the JFileChooser
     * @throws IOException Will be thrown if file is not readable.
     */
    private void loadMatrixCSV(File file) throws IOException {
        var reader = new BufferedReader(new FileReader(file));
        var lines = reader.lines().toList();
        reader.close();
        var rowsCount = lines.size();
        String[] string_matrix = new String[rowsCount];
        if (lines.size() == 0) {
            JOptionPane.showMessageDialog(null,"CSV file does not contain data!",
                    "Parse Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        if (DataType.current == DataType.BIG_DECIMAL) {
            vector = new BigDecimal[rowsCount];
            for (int row = 0; row < rowsCount; row++) {
                try {
                    vector[row] = new BigDecimal((String) lines.toArray()[row]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null,"One ore more values in the CSV file are not parsable!",
                            "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                    return;
                    }
            }
        } else if (DataType.current == DataType.FRACTION) {
            vector = new Fraction[rowsCount];
            for (int row = 0; row < rowsCount; row++) {
                try {
                    vector[row] = new Fraction((String) lines.toArray()[row]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null,"One ore more values in the CSV file are not parsable!",
                            "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
            }
        }

        if (DataType.current == DataType.BIG_DECIMAL && vector instanceof BigDecimal[]) {
            textField_inlineMatrix.setText(ParserBigDecimal.vectorToString((BigDecimal[]) vector));
        }
        else if (DataType.current == DataType.FRACTION && vector instanceof Fraction[]) {
            textField_inlineMatrix.setText(ParserFraction.vectorToString((Fraction[]) vector));
        }
    }
}
