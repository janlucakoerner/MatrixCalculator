package frontend.base.panel;

import frontend.base.frame.OpenMatrixGUI;
import backend.Parser;
import middleware.base.DataType;
import middleware.base.Fraction;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;

/**
 * This class creates a panel to be added for each matrix calculation.
 * The panel comes with a text field for inline matrix declaration,
 * a button to open a dialog with a matrix view and a button which loads
 * the matrix from a csv file.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/04)
 */
public class MatrixPanel extends JPanel {
    private final JLabel label;
    private final JTextField textField_inlineMatrix;
    private final JButton button_openMatrixDialog;
    private final JButton button_openEditMatrixDialog;
    private final JButton button_loadMatrixCSV;
    private GridBagConstraints gbc;
    private Object[][] matrix;
    private OpenMatrixGUI openMatrixGUI;
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
            if (DataType.current == DataType.BIG_DECIMAL) {
                matrix = Parser.instance_bigDecimal.getMatrixFromInline(textField_inlineMatrix.getText());
                openMatrixGUI = new OpenMatrixGUI<BigDecimal>(parent, (BigDecimal[][]) matrix);
            } else if (DataType.current == DataType.FRACTION) {
                matrix = Parser.instance_fraction.getMatrixFromInline(textField_inlineMatrix.getText());
                openMatrixGUI = new OpenMatrixGUI<Fraction>(parent, (Fraction[][]) matrix);
            }
        });

        //--------------------------------------------------------------------------------------------------------------

        button_openEditMatrixDialog.setText("Open Edit Matrix");
        button_openEditMatrixDialog.addActionListener(e -> {
            if (DataType.current == DataType.BIG_DECIMAL)
                matrix = Parser.instance_bigDecimal.getMatrixFromInline(textField_inlineMatrix.getText());
            else if (DataType.current == DataType.FRACTION)
                matrix = Parser.instance_fraction.getMatrixFromInline(textField_inlineMatrix.getText());
            boolean createNew = false;
            if (matrix == null) {
                createNew = true;
            } else if (matrix[0] == null) {
                createNew = true;
            }
            if (createNew) {
                var frame = new JFrame();
                var panel = new JPanel();
                var button = new JButton();
                var textField_rowCount = new JTextField();
                var textField_columnCount = new JTextField();

                parent.setVisible(false);

                panel.setLayout(new GridLayout(2,2));
                panel.add(new JLabel("Rows"));
                panel.add(textField_rowCount);
                panel.add(new JLabel("Columns"));
                panel.add(textField_columnCount);

                button.setText("Create Matrix");
                button.addActionListener(e1 -> {
                    // open matrix create dialog
                    try {
                        var rowCount = Integer.parseInt(textField_rowCount.getText());
                        var columnCount = Integer.parseInt(textField_columnCount.getText());
                        if (rowCount <= 1 || columnCount <= 1) {
                            JOptionPane.showMessageDialog(null, "Row or column count is not a valid number",
                                    "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                        }
                        frame.setVisible(false);

                        if (DataType.current == DataType.BIG_DECIMAL)
                            matrix = new BigDecimal[rowCount][columnCount];
                        else if (DataType.current == DataType.FRACTION)
                            matrix = new Fraction[rowCount][columnCount];

                        var frame_matrix = new JFrame();
                        var panel_matrix = new JPanel();
                        var button_close = new JButton();
                        var textFields_matrix = new JTextField[rowCount * columnCount];

                        for (int i = 0; i < rowCount * columnCount; i++) {
                            textFields_matrix[i] = new JTextField();
                            panel_matrix.add(textFields_matrix[i]);
                        }

                        panel_matrix.setLayout(new GridLayout(rowCount, columnCount));
                        button_close.setText("Close");
                        button_close.addActionListener(e2 -> {
                            frame_matrix.setVisible(false);
                            parent.setVisible(true);
                            var y = 0;
                            for (int row = 0; row < rowCount; row++) {
                                for (int column = 0; column < columnCount; column++) {
                                    try {
                                        if (DataType.current == DataType.BIG_DECIMAL)
                                            matrix[row][column] = new BigDecimal(textFields_matrix[y].getText());
                                        else if (DataType.current == DataType.FRACTION) {
                                            matrix[row][column] = new Fraction(textFields_matrix[y].getText());
                                        }
                                    } catch (NumberFormatException nfe) {
                                        JOptionPane.showMessageDialog(null, "Matrix could not be parsed!",
                                                "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                                        return;
                                    }
                                    y++;
                                }
                            }
                            if (DataType.current == DataType.BIG_DECIMAL)
                                updateInline((BigDecimal[][]) matrix);
                            else if (DataType.current == DataType.FRACTION)
                                updateInline((Fraction[][]) matrix);
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
            var rowCount = matrix.length;
            var columnCount = matrix[0].length;
            parent.setVisible(false);

            var frame = new JFrame();
            var panel = new JPanel();
            var button_close = new JButton();
            var textFields_matrix = new JTextField[rowCount * columnCount];

            for (int i = 0; i < rowCount * columnCount; i++) {
                textFields_matrix[i] = new JTextField();
                panel.add(textFields_matrix[i]);
            }

            var x = 0;
            if (DataType.current == DataType.BIG_DECIMAL) {
                for (BigDecimal[] rows : (BigDecimal[][]) matrix) {
                    for (BigDecimal value : rows) {
                        textFields_matrix[x].setText(value.toString());
                        x++;
                    }
                }
            } else if (DataType.current == DataType.FRACTION) {
                for (Fraction[] rows : (Fraction[][]) matrix) {
                    for (Fraction value : rows) {
                        textFields_matrix[x].setText(value.toString());
                        x++;
                    }
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
                            if (DataType.current == DataType.BIG_DECIMAL)
                                matrix[row][column] = new BigDecimal(textFields_matrix[y].getText());
                            else if (DataType.current == DataType.FRACTION) {
                                matrix[row][column] = new Fraction(textFields_matrix[y].getText());
                            }
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null, "Matrix could not be parsed!",
                                    "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                            return;
                        }
                        y++;
                    }
                }
                if (DataType.current == DataType.BIG_DECIMAL)
                    updateInline((BigDecimal[][]) matrix);
                else if (DataType.current == DataType.FRACTION)
                    updateInline((Fraction[][]) matrix);
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

    public void setEditable(boolean editable) {
        textField_inlineMatrix.setEditable(editable);
    }

    public void setText(String text) {
        label.setText(text);
    }

    public String getInline() {
        return textField_inlineMatrix.getText();
    }

    public void updateInline(BigDecimal[][] matrix) {
        textField_inlineMatrix.setText(Parser.instance_bigDecimal.toString(matrix));
    }
    public void updateInline(Fraction[][] matrix) {
        textField_inlineMatrix.setText(Parser.instance_fraction.toString(matrix));
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
        if (DataType.current == DataType.BIG_DECIMAL) {
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
        } else if (DataType.current == DataType.FRACTION) {
            matrix = new Fraction[rowsCount][columnCount];
            for (int row = 0; row < rowsCount; row++) {
                for (int column = 0; column < columnCount; column++) {
                    try {
                        matrix[row][column] = new Fraction(string_matrix[row][column]);
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null,"One ore more values in the CSV file are not parsable!",
                                "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                }
            }
        }

        if (DataType.current == DataType.BIG_DECIMAL && matrix instanceof BigDecimal[][]) {
            textField_inlineMatrix.setText(Parser.instance_bigDecimal.toString((BigDecimal[][]) matrix));
        }
        else if (DataType.current == DataType.FRACTION && matrix instanceof Fraction[][]) {
            textField_inlineMatrix.setText(Parser.instance_fraction.toString((Fraction[][]) matrix));
        }
    }
}
