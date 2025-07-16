package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class calculadoraGUI extends JFrame {

    private double ans = 0.0; 

    private final JTextArea outputArea = new JTextArea(5, 40);

    // --- Pestaña Básicas ---
    private JTextArea basicNumbersInput;

    // --- Pestaña Potencia / Raíz ---
    private JTextField basePotenciaField;
    private JTextField exponentePotenciaField;
    private JTextField radicandoRaizField;
    private JTextField indiceRaizField;

    // --- Pestaña Vectores ---
    private JTextField[][] vectorFields1;
    private JTextField[][] vectorFields2;
    private JTextField escalarVectorField;
    private JComboBox<String> vectorOperationComboBox;
    private JComboBox<String> vectorDimensionComboBox;
    private JPanel vectorInputPanel1TwoVecs; 
    private JPanel vectorInputPanel2TwoVecs; 
    private JPanel vectorInputPanelScalarVec; 


    // --- Pestaña Matrices ---
 
    private JSpinner rowsA_Spinner;
    private JSpinner colsA_Spinner;
    private JSpinner rowsB_Spinner;
    private JSpinner colsB_Spinner;

    private JTextField[][] matrixAFields; 
    private JTextField[][] matrixBFields; 


    private JPanel matrixAGridPanelTwoMats;
    private JPanel matrixBGridPanelTwoMats;
    private JPanel matrixAGridPanelScalarMat;
    private JPanel matrixAGridPanelOneMat;
    private JTextField scalarMatrixField;
    private JComboBox<String> matrixOperationComboBox;


    // --- Pestaña Sistema 2x2 ---
    private JTextField a1Field, b1Field, c1Field;
    private JTextField a2Field, b2Field, c2Field;

    // --- Pestaña Sistema 3x3 ---
    private JTextField s3_a1Field, s3_b1Field, s3_c1Field, s3_d1Field;
    private JTextField s3_a2Field, s3_b2Field, s3_c2Field, s3_d2Field;
    private JTextField s3_a3Field, s3_b3Field, s3_c3Field, s3_d3Field;


    public calculadoraGUI() {
        setTitle("Calculadora Avanzada – GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);

         try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("No se pudo establecer el Look and Feel del sistema: " + e.getMessage());
        }

        // Crear pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Básicas", createBasicPanel());
        tabbedPane.addTab("Potencia / Raíz", createPowerRootPanel());
        tabbedPane.addTab("Vectores", createVectorPanel());
        tabbedPane.addTab("Matrices", createMatrixPanel());
        tabbedPane.addTab("Sistema 2x2", createSystem2x2Panel());
        tabbedPane.addTab("Sistema 3x3", createSystem3x3Panel()); // Nuevo: Pestaña para sistema 3x3



        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Resultado / Errores"));
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.SOUTH);

        // Botón para insertar 'ans'
        JButton insertAnsButton = new JButton("Insertar Ans");
        insertAnsButton.addActionListener(e -> insertAns());
        JPanel ansButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ansButtonPanel.add(insertAnsButton);
        outputPanel.add(ansButtonPanel, BorderLayout.NORTH);

        add(mainPanel);
        setVisible(true);

 
        rowsA_Spinner.setValue(2);
        colsA_Spinner.setValue(2);
        rowsB_Spinner.setValue(2);
        colsB_Spinner.setValue(2);
        SwingUtilities.invokeLater(() -> {
            System.out.println("DEBUG: Inicializando campos de matriz después de GUI visible.");
            updateMatrixInputFields();
        });
    }

    private JPanel createBasicPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));


        basicNumbersInput = new JTextArea(3, 30);
        basicNumbersInput.setBorder(BorderFactory.createTitledBorder("Números (separados por espacios o comas)"));
        basicNumbersInput.setLineWrap(true);
        basicNumbersInput.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(basicNumbersInput);
        panel.add(scrollPane, BorderLayout.NORTH);

 
        // Solo las 4 operaciones principales
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        String[] operations = {"Suma", "Resta", "Multiplicación", "División"};
        for (String op : operations) {
            JButton btn = new JButton(op);
            btn.addActionListener(e -> executeBasicOperation(op));
            buttonPanel.add(btn);
        }
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPowerRootPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Potencia
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Base (Potencia):"), gbc);
        gbc.gridx = 1;
        basePotenciaField = new JTextField(4);
        panel.add(basePotenciaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Exponente:"), gbc);
        gbc.gridx = 1;
        exponentePotenciaField = new JTextField(4); 
        panel.add(exponentePotenciaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton btnPotencia = new JButton("Calcular Potencia");
        btnPotencia.addActionListener(e -> executePowerOperation());
        panel.add(btnPotencia, gbc);
        // Separador
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(new JSeparator(), gbc);

        // Raíz
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Radicando (Raíz):"), gbc);
        gbc.gridx = 1;
        radicandoRaizField = new JTextField(4); 
        panel.add(radicandoRaizField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Índice (ej. 2 para raíz cuadrada):"), gbc);
        gbc.gridx = 1;
        indiceRaizField = new JTextField(4); 
        panel.add(indiceRaizField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JButton btnRaiz = new JButton("Calcular Raíz");
        btnRaiz.addActionListener(e -> executeRootOperation());
        panel.add(btnRaiz, gbc);
        return panel;
    }

    private JPanel createVectorPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

  
        JPanel configPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        vectorDimensionComboBox = new JComboBox<>(new String[]{"2", "3"});
        vectorOperationComboBox = new JComboBox<>(new String[]{"Suma", "Resta", "Producto Escalar", "Producto Vectorial", "Escalar por Vector"});

        configPanel.add(new JLabel("Dimensión:"));
        configPanel.add(vectorDimensionComboBox);
        configPanel.add(new JLabel("Operación:"));
        configPanel.add(vectorOperationComboBox);
        panel.add(configPanel, BorderLayout.NORTH);


        vectorInputPanel1TwoVecs = new JPanel();
        vectorInputPanel2TwoVecs = new JPanel();
        vectorInputPanelScalarVec = new JPanel();


        vectorInputPanel1TwoVecs.setName("Vector 1 Panel");
        vectorInputPanel2TwoVecs.setName("Vector 2 Panel");
        vectorInputPanelScalarVec.setName("Vector Scalar Panel");


        JPanel inputAreaPanel = new JPanel(new CardLayout());
        inputAreaPanel.add(createVectorTwoInputWrapperPanel(), "2_vecs_input");
        inputAreaPanel.add(createVectorOneInputWrapperPanel(), "1_vec_input"); 

        panel.add(inputAreaPanel, BorderLayout.CENTER);
        // Botón de cálculo
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(e -> executeVectorOperation());
        panel.add(btnCalcular, BorderLayout.SOUTH);


        ActionListener updateVectorInputs = e -> {
            updateVectorInputFields();
            CardLayout cl = (CardLayout) (inputAreaPanel.getLayout());
            String selectedOperation = (String) vectorOperationComboBox.getSelectedItem();
            if ("Escalar por Vector".equals(selectedOperation)) {
                cl.show(inputAreaPanel, "1_vec_input");
            } else {
                cl.show(inputAreaPanel, "2_vecs_input");
            }
        };

        vectorDimensionComboBox.addActionListener(updateVectorInputs);
        vectorOperationComboBox.addActionListener(updateVectorInputs);

       
        SwingUtilities.invokeLater(this::updateVectorInputFields);

        return panel;
    }


    private JPanel createVectorTwoInputWrapperPanel() {
        JPanel wrapper = new JPanel(new GridLayout(2, 1, 5, 5));
        JPanel v1Section = new JPanel(new BorderLayout());
        v1Section.add(new JLabel("Vector 1:", SwingConstants.LEFT), BorderLayout.NORTH);
        v1Section.add(vectorInputPanel1TwoVecs, BorderLayout.CENTER);

        JPanel v2Section = new JPanel(new BorderLayout());
        v2Section.add(new JLabel("Vector 2:", SwingConstants.LEFT), BorderLayout.NORTH);
        v2Section.add(vectorInputPanel2TwoVecs, BorderLayout.CENTER);

        wrapper.add(v1Section);
        wrapper.add(v2Section);
        return wrapper;
    }

    private JPanel createVectorOneInputWrapperPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(5, 5));
        wrapper.add(vectorInputPanelScalarVec, BorderLayout.CENTER);
        return wrapper;
    }


    private void updateVectorInputFields() {
        int dimension = Integer.parseInt((String) vectorDimensionComboBox.getSelectedItem());
        String selectedOperation = (String) vectorOperationComboBox.getSelectedItem();

    
        if (vectorInputPanel1TwoVecs != null) vectorInputPanel1TwoVecs.removeAll();
        if (vectorInputPanel2TwoVecs != null) vectorInputPanel2TwoVecs.removeAll();
        if (vectorInputPanelScalarVec != null) vectorInputPanelScalarVec.removeAll();
        if ("Escalar por Vector".equals(selectedOperation)) {
            vectorFields1 = new JTextField[1][dimension];
            vectorInputPanelScalarVec.setLayout(new GridLayout(2, 1, 5, 5));
            vectorInputPanelScalarVec.setBorder(new EmptyBorder(0, 0, 0, 0));

            
            JPanel scalarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            scalarPanel.add(new JLabel("Escalar:"));
            escalarVectorField = new JTextField(4); 
            scalarPanel.add(escalarVectorField);
            vectorInputPanelScalarVec.add(scalarPanel);

          
            JPanel vecPanel = new JPanel(new GridLayout(1, dimension, 3, 3)); 
            vecPanel.setBorder(BorderFactory.createTitledBorder("Vector"));
            for (int i = 0; i < dimension; i++) {
                JTextField field = new JTextField(3);
                // Más compacto
                vectorFields1[0][i] = field;
                vecPanel.add(field);
            }
            vectorInputPanelScalarVec.add(vecPanel);
        } else {
            
            vectorFields1 = new JTextField[1][dimension];
            vectorFields2 = new JTextField[1][dimension];

            vectorInputPanel1TwoVecs.setLayout(new GridLayout(1, dimension, 3, 3)); 
            for (int i = 0; i < dimension; i++) {
                JTextField field = new JTextField(3);
                vectorFields1[0][i] = field;
                vectorInputPanel1TwoVecs.add(field);
            }

            vectorInputPanel2TwoVecs.setLayout(new GridLayout(1, dimension, 3, 3));
            for (int i = 0; i < dimension; i++) {
                JTextField field = new JTextField(3);
                vectorFields2[0][i] = field;
                vectorInputPanel2TwoVecs.add(field);
            }
        }
      
        if (vectorInputPanel1TwoVecs != null) {
            vectorInputPanel1TwoVecs.revalidate();
            vectorInputPanel1TwoVecs.repaint();
        }
        if (vectorInputPanel2TwoVecs != null) {
            vectorInputPanel2TwoVecs.revalidate();
            vectorInputPanel2TwoVecs.repaint();
        }
        if (vectorInputPanelScalarVec != null) {
            vectorInputPanelScalarVec.revalidate();
            vectorInputPanelScalarVec.repaint();
        }
    }


    private JPanel createMatrixPanel() {
        System.out.println("DEBUG: Creando panel de matrices.");
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

   
        JPanel configPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Dimensiones Matriz A
        gbc.gridx = 0; gbc.gridy = 0;
        configPanel.add(new JLabel("Matriz A - Filas:"), gbc);
        gbc.gridx = 1; rowsA_Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1)); configPanel.add(rowsA_Spinner, gbc);
        gbc.gridx = 2; configPanel.add(new JLabel("Cols:"), gbc);
        gbc.gridx = 3; colsA_Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1)); configPanel.add(colsA_Spinner, gbc);
        // Dimensiones Matriz B
        gbc.gridx = 0; gbc.gridy = 1;
        configPanel.add(new JLabel("Matriz B - Filas:"), gbc);
        gbc.gridx = 1; rowsB_Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1)); configPanel.add(rowsB_Spinner, gbc);
        gbc.gridx = 2; configPanel.add(new JLabel("Cols:"), gbc);
        gbc.gridx = 3; colsB_Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1)); configPanel.add(colsB_Spinner, gbc);
        // Selector de operación de matrices
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2; configPanel.add(new JLabel("Operación:"), gbc);
        gbc.gridx = 2; gbc.gridwidth = 2;
        matrixOperationComboBox = new JComboBox<>(new String[]{
                "Suma (A + B)", "Resta (A - B)", "Multiplicación (A * B)",
                "Escalar por Matriz", "Determinante (Matriz A)", "Transpuesta (Matriz A)", "Inversa (Matriz A)"
        });
        configPanel.add(matrixOperationComboBox, gbc);
        panel.add(configPanel, BorderLayout.NORTH);


    
        matrixAGridPanelTwoMats = new JPanel();
        matrixBGridPanelTwoMats = new JPanel();
        matrixAGridPanelScalarMat = new JPanel();
        matrixAGridPanelOneMat = new JPanel();
        JPanel inputAreaPanel = new JPanel(new CardLayout());
        inputAreaPanel.add(createMatrixTwoInputWrapperPanel(), "two_mats_input");
        inputAreaPanel.add(createMatrixScalarInputWrapperPanel(), "scalar_mat_input");
        inputAreaPanel.add(createMatrixOneInputWrapperPanel(), "one_mat_input");
        panel.add(inputAreaPanel, BorderLayout.CENTER);
        // Botón de cálculo
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(e -> executeMatrixOperation());
        panel.add(btnCalcular, BorderLayout.SOUTH);


        ActionListener updateInputListener = e -> {
            System.out.println("DEBUG: Cambiando dimensiones o operación de matriz. Actualizando campos.");
            updateMatrixInputFields();
            CardLayout cl = (CardLayout) (inputAreaPanel.getLayout());
            String selectedOperation = (String) matrixOperationComboBox.getSelectedItem();
            if ("Suma (A + B)".equals(selectedOperation) || "Resta (A - B)".equals(selectedOperation) || "Multiplicación (A * B)".equals(selectedOperation)) {
                cl.show(inputAreaPanel, "two_mats_input");
            } else if ("Escalar por Matriz".equals(selectedOperation)) {
                cl.show(inputAreaPanel, "scalar_mat_input");
            } else { 
                cl.show(inputAreaPanel, "one_mat_input");
            }
        };

        rowsA_Spinner.addChangeListener(e -> updateInputListener.actionPerformed(null));
        colsA_Spinner.addChangeListener(e -> updateInputListener.actionPerformed(null));
        rowsB_Spinner.addChangeListener(e -> updateInputListener.actionPerformed(null));
        colsB_Spinner.addChangeListener(e -> updateInputListener.actionPerformed(null));
        matrixOperationComboBox.addActionListener(updateInputListener);


        SwingUtilities.invokeLater(this::updateMatrixInputFields);

        return panel;
    }


    private JPanel createMatrixTwoInputWrapperPanel() {
        JPanel wrapper = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel matASection = new JPanel(new BorderLayout());
        matASection.add(new JLabel("Matriz A:", SwingConstants.LEFT), BorderLayout.NORTH); 
        matASection.add(matrixAGridPanelTwoMats, BorderLayout.CENTER);

        JPanel matBSection = new JPanel(new BorderLayout());
        matBSection.add(new JLabel("Matriz B:", SwingConstants.LEFT), BorderLayout.NORTH); 
        matBSection.add(matrixBGridPanelTwoMats, BorderLayout.CENTER);

        wrapper.add(matASection);
        wrapper.add(matBSection);
        return wrapper;
    }

    private JPanel createMatrixScalarInputWrapperPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(5, 5));
        JPanel scalarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        scalarPanel.add(new JLabel("Escalar:"));
        scalarMatrixField = new JTextField(4); 
        scalarPanel.add(scalarMatrixField);
        wrapper.add(scalarPanel, BorderLayout.NORTH);
        JPanel matSection = new JPanel(new BorderLayout());
        matSection.add(new JLabel("Matriz:", SwingConstants.LEFT), BorderLayout.NORTH); 
        matSection.add(matrixAGridPanelScalarMat, BorderLayout.CENTER);
        wrapper.add(matSection, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel createMatrixOneInputWrapperPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(5, 5));
        JPanel matSection = new JPanel(new BorderLayout());
        matSection.add(new JLabel("Matriz A:", SwingConstants.LEFT), BorderLayout.NORTH);
        matSection.add(matrixAGridPanelOneMat, BorderLayout.CENTER);
        wrapper.add(matSection, BorderLayout.CENTER);
        return wrapper;
    }

    private void updateMatrixInputFields() {
        System.out.println("DEBUG: Ejecutando updateMatrixInputFields.");
        int rowsA = (Integer) rowsA_Spinner.getValue();
        int colsA = (Integer) colsA_Spinner.getValue();
        int rowsB = (Integer) rowsB_Spinner.getValue();
        int colsB = (Integer) colsB_Spinner.getValue();
        String selectedOperation = (String) matrixOperationComboBox.getSelectedItem();
        matrixAGridPanelTwoMats.removeAll();
        matrixBGridPanelTwoMats.removeAll();
        matrixAGridPanelScalarMat.removeAll();
        matrixAGridPanelOneMat.removeAll();
        if ("Suma (A + B)".equals(selectedOperation) || "Resta (A - B)".equals(selectedOperation) || "Multiplicación (A * B)".equals(selectedOperation)) {
            matrixAFields = new JTextField[rowsA][colsA];
            matrixBFields = new JTextField[rowsB][colsB];
            matrixAGridPanelTwoMats.setLayout(new GridLayout(rowsA, colsA, 3, 3));
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsA; j++) {
                    JTextField field = new JTextField(3);
                    matrixAFields[i][j] = field;
                    matrixAGridPanelTwoMats.add(field);
                }
            }
            matrixBGridPanelTwoMats.setLayout(new GridLayout(rowsB, colsB, 3, 3));
            for (int i = 0; i < rowsB; i++) {
                for (int j = 0; j < colsB; j++) {
                    JTextField field = new JTextField(3);
                    matrixBFields[i][j] = field;
                    matrixBGridPanelTwoMats.add(field);
                }
            }
        } else if ("Escalar por Matriz".equals(selectedOperation)) {
            matrixAFields = new JTextField[rowsA][colsA];
            matrixAGridPanelScalarMat.setLayout(new GridLayout(rowsA, colsA, 3, 3));
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsA; j++) {
                    JTextField field = new JTextField(3);
                    matrixAFields[i][j] = field;
                    matrixAGridPanelScalarMat.add(field);
                }
            }
            if (scalarMatrixField == null) {
                scalarMatrixField = new JTextField(4);
            }
        } else {
            matrixAFields = new JTextField[rowsA][colsA];
            matrixAGridPanelOneMat.setLayout(new GridLayout(rowsA, colsA, 3, 3));
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsA; j++) {
                    JTextField field = new JTextField(3);
                    matrixAFields[i][j] = field;
                    matrixAGridPanelOneMat.add(field);
                }
            }
        }
        matrixAGridPanelTwoMats.revalidate();
        matrixAGridPanelTwoMats.repaint();
        matrixBGridPanelTwoMats.revalidate();
        matrixBGridPanelTwoMats.repaint();
        matrixAGridPanelScalarMat.revalidate();
        matrixAGridPanelScalarMat.repaint();
        matrixAGridPanelOneMat.revalidate();
        matrixAGridPanelOneMat.repaint();
        System.out.println("DEBUG: updateMatrixInputFields completado. matrixAFields debería contener " + (matrixAFields != null ? matrixAFields.length + "x" + (matrixAFields.length > 0 ? matrixAFields[0].length : "0") : "nulo") + " campos.");
    }

    private JPanel createSystem2x2Panel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Ecuación 1: a1x + b1y = c1
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Ecuación 1:"), gbc);
        gbc.gridx = 1; a1Field = new JTextField(4); panel.add(a1Field, gbc);
        gbc.gridx = 2; panel.add(new JLabel("x +"), gbc);
        gbc.gridx = 3; b1Field = new JTextField(4); panel.add(b1Field, gbc);
        gbc.gridx = 4; panel.add(new JLabel("y ="), gbc);
        gbc.gridx = 5; c1Field = new JTextField(4); panel.add(c1Field, gbc);

        // Ecuación 2: a2x + b2y = c2
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Ecuación 2:"), gbc);
        gbc.gridx = 1; a2Field = new JTextField(4); panel.add(a2Field, gbc);
        gbc.gridx = 2; panel.add(new JLabel("x +"), gbc);
        gbc.gridx = 3; b2Field = new JTextField(4); panel.add(b2Field, gbc);
        gbc.gridx = 4; panel.add(new JLabel("y ="), gbc);
        gbc.gridx = 5; c2Field = new JTextField(4); panel.add(c2Field, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 6;
        JButton btnSolve2x2 = new JButton("Resolver Sistema 2x2");
        btnSolve2x2.addActionListener(e -> executeSystem2x2Solve());
        panel.add(btnSolve2x2, gbc);

        return panel;
    }

    private JPanel createSystem3x3Panel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Ecuación 1: a1x + b1y + c1z = d1
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Ecuación 1:"), gbc);
        gbc.gridx = 1; s3_a1Field = new JTextField(4); panel.add(s3_a1Field, gbc); gbc.gridx = 2; panel.add(new JLabel("x +"), gbc);
        gbc.gridx = 3; s3_b1Field = new JTextField(4); panel.add(s3_b1Field, gbc); gbc.gridx = 4; panel.add(new JLabel("y +"), gbc);
        gbc.gridx = 5; s3_c1Field = new JTextField(4); panel.add(s3_c1Field, gbc); gbc.gridx = 6; panel.add(new JLabel("z ="), gbc);
        gbc.gridx = 7; s3_d1Field = new JTextField(4); panel.add(s3_d1Field, gbc);

        // Ecuación 2: a2x + b2y + c2z = d2
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Ecuación 2:"), gbc);
        gbc.gridx = 1; s3_a2Field = new JTextField(4); panel.add(s3_a2Field, gbc); gbc.gridx = 2; panel.add(new JLabel("x +"), gbc);
        gbc.gridx = 3; s3_b2Field = new JTextField(4); panel.add(s3_b2Field, gbc); gbc.gridx = 4; panel.add(new JLabel("y +"), gbc);
        gbc.gridx = 5; s3_c2Field = new JTextField(4); panel.add(s3_c2Field, gbc); gbc.gridx = 6; panel.add(new JLabel("z ="), gbc);
        gbc.gridx = 7; s3_d2Field = new JTextField(4); panel.add(s3_d2Field, gbc);

        // Ecuación 3: a3x + b3y + c3z = d3
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Ecuación 3:"), gbc);
        gbc.gridx = 1; s3_a3Field = new JTextField(4); panel.add(s3_a3Field, gbc); gbc.gridx = 2; panel.add(new JLabel("x +"), gbc);
        gbc.gridx = 3; s3_b3Field = new JTextField(4); panel.add(s3_b3Field, gbc); gbc.gridx = 4; panel.add(new JLabel("y +"), gbc);
        gbc.gridx = 5; s3_c3Field = new JTextField(4); panel.add(s3_c3Field, gbc); gbc.gridx = 6; panel.add(new JLabel("z ="), gbc);
        gbc.gridx = 7; s3_d3Field = new JTextField(4); panel.add(s3_d3Field, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 8;
        JButton btnSolve3x3 = new JButton("Resolver Sistema 3x3");
        btnSolve3x3.addActionListener(e -> executeSystem3x3Solve());
        panel.add(btnSolve3x3, gbc);

        return panel;
    }


    // --- Métodos de Ejecución (Stubs) ---

    private void executeBasicOperation(String operation) {
        try {
            List<Double> numbers = Arrays.stream(basicNumbersInput.getText().split("[\\s,]+"))
                                        .filter(s -> !s.isEmpty())
                                        .map(Double::parseDouble)
                                        .collect(Collectors.toList());
            if (numbers.isEmpty()) {
                printErr("Por favor, introduce al menos un número para la operación básica.");
                return;
            }

            double result = numbers.get(0);
            if (numbers.size() > 1) {
                for (int i = 1; i < numbers.size(); i++) {
                    switch (operation) {
                        case "Suma":
                            result += numbers.get(i);
                            break;
                        case "Resta":
                            result -= numbers.get(i);
                            break;
                        case "Multiplicación":
                            result *= numbers.get(i);
                            break;
                        case "División":
                            if (numbers.get(i) == 0) {
                                throw new ArithmeticException("División por cero no permitida.");
                            }
                            result /= numbers.get(i);
                            break;
                    }
                }
            } else if (numbers.size() == 1 && ("Resta".equals(operation) || "División".equals(operation))) {
                // If only one number for subtraction or division, assume it's unary
                // For division, 1/x; for subtraction, 0-x. Adjust as per desired behavior.
                // For simplicity here, let's just output the number itself for these unary cases,
                // or you might want to consider 0 - number for subtraction.
                // For now, let's make it clear this is for multiple numbers.
                printErr("Para " + operation + ", por favor, introduce al menos dos números.");
                return;
            }

            print("Resultado " + operation + ": " + String.format("%.4f", result));
        } catch (NumberFormatException ex) {
            printErr("Entrada inválida. Asegúrate de introducir números válidos.");
        } catch (ArithmeticException ex) {
            printErr("Error de cálculo: " + ex.getMessage());
        } catch (Exception ex) {
            printErr(ex);
        }
    }


    private void executePowerOperation() {
        try {
            double base = Double.parseDouble(basePotenciaField.getText());
            double exponente = Double.parseDouble(exponentePotenciaField.getText());
            double result = Math.pow(base, exponente);
            print("Resultado Potencia: " + String.format("%.4f", result));
        } catch (NumberFormatException ex) {
            printErr("Entrada inválida para potencia. Asegúrate de introducir números válidos.");
        } catch (Exception ex) {
            printErr(ex);
        }
    }

    private void executeRootOperation() {
        try {
            double radicando = Double.parseDouble(radicandoRaizField.getText());
            double indice = Double.parseDouble(indiceRaizField.getText());

            if (radicando < 0 && indice % 2 == 0) {
                printErr("No se puede calcular la raíz par de un número negativo.");
                return;
            }
            if (indice == 0) {
                printErr("El índice de la raíz no puede ser cero.");
                return;
            }

            double result = Math.pow(radicando, 1.0 / indice);
            print("Resultado Raíz: " + String.format("%.4f", result));
        } catch (NumberFormatException ex) {
            printErr("Entrada inválida para raíz. Asegúrate de introducir números válidos.");
        } catch (Exception ex) {
            printErr(ex);
        }
    }


    private void executeVectorOperation() {
        try {
            String selectedOperation = (String) vectorOperationComboBox.getSelectedItem();
            int dimension = Integer.parseInt((String) vectorDimensionComboBox.getSelectedItem());

            double[] v1 = parseVector(vectorFields1, 0, dimension);

            switch (selectedOperation) {
                case "Suma":
                    double[] v2_sum = parseVector(vectorFields2, 0, dimension);
                    double[] sumResult = addVectors(v1, v2_sum);
                    print("Suma de Vectores: " + vecToString(sumResult));
                    break;
                case "Resta":
                    double[] v2_resta = parseVector(vectorFields2, 0, dimension);
                    double[] subResult = subtractVectors(v1, v2_resta);
                    print("Resta de Vectores: " + vecToString(subResult));
                    break;
                case "Producto Escalar":
                    double[] v2_dot = parseVector(vectorFields2, 0, dimension);
                    double dotResult = dotProduct(v1, v2_dot);
                    print("Producto Escalar: " + String.format("%.4f", dotResult));
                    break;
                case "Producto Vectorial":
                    if (dimension != 3) {
                        printErr("El producto vectorial solo está definido para vectores 3D.");
                        return;
                    }
                    double[] v2_cross = parseVector(vectorFields2, 0, dimension);
                    double[] crossResult = crossProduct(v1, v2_cross);
                    print("Producto Vectorial: " + vecToString(crossResult));
                    break;
                case "Escalar por Vector":
                    double scalar = Double.parseDouble(escalarVectorField.getText());
                    double[] scalarVecResult = scalarMultiplyVector(scalar, v1);
                    print("Escalar por Vector: " + vecToString(scalarVecResult));
                    break;
            }

        } catch (NumberFormatException ex) {
            printErr("Entrada inválida. Asegúrate de introducir números válidos en los campos de vector.");
        } catch (IllegalArgumentException ex) {
            printErr("Error: " + ex.getMessage());
        } catch (Exception ex) {
            printErr(ex);
        }
    }


    private void executeMatrixOperation() {
        try {
            String selectedOperation = (String) matrixOperationComboBox.getSelectedItem();
            int rowsA = (Integer) rowsA_Spinner.getValue();
            int colsA = (Integer) colsA_Spinner.getValue();
            int rowsB = (Integer) rowsB_Spinner.getValue();
            int colsB = (Integer) colsB_Spinner.getValue();

            double[][] matA = parseMatrix(matrixAFields, rowsA, colsA);

            switch (selectedOperation) {
                case "Suma (A + B)":
                    double[][] matB_sum = parseMatrix(matrixBFields, rowsB, colsB);
                    if (rowsA != rowsB || colsA != colsB) {
                        printErr("Las matrices deben tener las mismas dimensiones para la suma.");
                        return;
                    }
                    double[][] sumResult = addMatrices(matA, matB_sum);
                    print("Suma de Matrices:\n" + matToString(sumResult));
                    break;
                case "Resta (A - B)":
                    double[][] matB_sub = parseMatrix(matrixBFields, rowsB, colsB);
                    if (rowsA != rowsB || colsA != colsB) {
                        printErr("Las matrices deben tener las mismas dimensiones para la resta.");
                        return;
                    }
                    double[][] subResult = subtractMatrices(matA, matB_sub);
                    print("Resta de Matrices:\n" + matToString(subResult));
                    break;
                case "Multiplicación (A * B)":
                    double[][] matB_mult = parseMatrix(matrixBFields, rowsB, colsB);
                    if (colsA != rowsB) {
                        printErr("Para la multiplicación (A*B), el número de columnas de A debe ser igual al número de filas de B.");
                        return;
                    }
                    double[][] multResult = multiplyMatrices(matA, matB_mult);
                    print("Multiplicación de Matrices:\n" + matToString(multResult));
                    break;
                case "Escalar por Matriz":
                    double scalar = Double.parseDouble(scalarMatrixField.getText());
                    double[][] scalarMatResult = scalarMultiplyMatrix(scalar, matA);
                    print("Escalar por Matriz:\n" + matToString(scalarMatResult));
                    break;
                case "Determinante (Matriz A)":
                    if (rowsA != colsA) {
                        printErr("La matriz debe ser cuadrada para calcular el determinante.");
                        return;
                    }
                    double detResult = determinant(matA);
                    print("Determinante (Matriz A): " + String.format("%.4f", detResult));
                    break;
                case "Transpuesta (Matriz A)":
                    double[][] transposeResult = transposeMatrix(matA);
                    print("Transpuesta (Matriz A):\n" + matToString(transposeResult));
                    break;
                case "Inversa (Matriz A)":
                    if (rowsA != colsA) {
                        printErr("La matriz debe ser cuadrada para calcular la inversa.");
                        return;
                    }
                    double detForInverse = determinant(matA);
                    if (detForInverse == 0) {
                        printErr("La matriz no tiene inversa (determinante es cero).");
                        return;
                    }
                    double[][] inverseResult = inverseMatrix(matA);
                    print("Inversa (Matriz A):\n" + matToString(inverseResult));
                    break;
            }
        } catch (NumberFormatException ex) {
            printErr("Entrada inválida. Asegúrate de introducir números válidos en los campos de la matriz.");
        } catch (IllegalArgumentException ex) {
            printErr("Error: " + ex.getMessage());
        } catch (ArithmeticException ex) {
            printErr("Error de cálculo: " + ex.getMessage());
        }
        catch (Exception ex) {
            printErr(ex);
        }
    }


    private void executeSystem2x2Solve() {
        try {
            double a1 = Double.parseDouble(a1Field.getText());
            double b1 = Double.parseDouble(b1Field.getText());
            double c1 = Double.parseDouble(c1Field.getText());
            double a2 = Double.parseDouble(a2Field.getText());
            double b2 = Double.parseDouble(b2Field.getText());
            double c2 = Double.parseDouble(c2Field.getText());

            double determinant = a1 * b2 - a2 * b1;

            if (determinant == 0) {
                print("El sistema no tiene solución única (determinante es cero). Puede tener infinitas soluciones o ninguna.");
            } else {
                double x = (c1 * b2 - c2 * b1) / determinant;
                double y = (a1 * c2 - a2 * c1) / determinant;
                print(String.format("Solución Sistema 2x2:\nx = %.4f\ny = %.4f", x, y));
            }
        } catch (NumberFormatException ex) {
            printErr("Entrada inválida. Asegúrate de introducir números válidos para el sistema 2x2.");
        } catch (Exception ex) {
            printErr(ex);
        }
    }

    private void executeSystem3x3Solve() {
        try {
            double a1 = Double.parseDouble(s3_a1Field.getText());
            double b1 = Double.parseDouble(s3_b1Field.getText());
            double c1 = Double.parseDouble(s3_c1Field.getText());
            double d1 = Double.parseDouble(s3_d1Field.getText());

            double a2 = Double.parseDouble(s3_a2Field.getText());
            double b2 = Double.parseDouble(s3_b2Field.getText());
            double c2 = Double.parseDouble(s3_c2Field.getText());
            double d2 = Double.parseDouble(s3_d2Field.getText());

            double a3 = Double.parseDouble(s3_a3Field.getText());
            double b3 = Double.parseDouble(s3_b3Field.getText());
            double c3 = Double.parseDouble(s3_c3Field.getText());
            double d3 = Double.parseDouble(s3_d3Field.getText());

            // Crear matriz de coeficientes y vector de resultados
            double[][] A = {
                {a1, b1, c1},
                {a2, b2, c2},
                {a3, b3, c3}
            };
            double[] B = {d1, d2, d3};

            // Calcular el determinante de la matriz de coeficientes
            double detA = determinant(A);

            if (detA == 0) {
                print("El sistema no tiene solución única (determinante es cero). Puede tener infinitas soluciones o ninguna.");
            } else {
                // Usar la regla de Cramer o la inversa de la matriz
                // Para simplificar, usaremos un método para resolver el sistema (Cramer)
                // Aunque no esté implementada la regla de Cramer directamente, el concepto es el mismo:
                // Sustituir la columna de resultados en cada columna de la matriz original
                // y calcular su determinante, luego dividir por el determinante principal.

                double[][] Ax = {{d1, b1, c1}, {d2, b2, c2}, {d3, b3, c3}};
                double[][] Ay = {{a1, d1, c1}, {a2, d2, c2}, {a3, d3, c3}};
                double[][] Az = {{a1, b1, d1}, {a2, b2, d2}, {a3, b3, d3}};

                double detAx = determinant(Ax);
                double detAy = determinant(Ay);
                double detAz = determinant(Az);

                double x = detAx / detA;
                double y = detAy / detA;
                double z = detAz / detA;

                print(String.format("Solución Sistema 3x3:\nx = %.4f\ny = %.4f\nz = %.4f", x, y, z));
            }

        } catch (NumberFormatException ex) {
            printErr("Entrada inválida. Asegúrate de introducir números válidos para el sistema 3x3.");
        } catch (Exception ex) {
            printErr(ex);
        }
    }


    // --- Utilidades de Parsing ---
    private double[] parseVector(JTextField[][] fields, int row, int dimension) {
        double[] vector = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            vector[i] = Double.parseDouble(fields[row][i].getText());
        }
        return vector;
    }

    private double[][] parseMatrix(JTextField[][] fields, int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Double.parseDouble(fields[i][j].getText());
            }
        }
        return matrix;
    }

    // --- Operaciones de Vectores ---
    private double[] addVectors(double[] v1, double[] v2) {
        if (v1.length != v2.length) throw new IllegalArgumentException("Los vectores deben tener la misma dimensión.");
        double[] result = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] + v2[i];
        }
        return result;
    }

    private double[] subtractVectors(double[] v1, double[] v2) {
        if (v1.length != v2.length) throw new IllegalArgumentException("Los vectores deben tener la misma dimensión.");
        double[] result = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] - v2[i];
        }
        return result;
    }

    private double dotProduct(double[] v1, double[] v2) {
        if (v1.length != v2.length) throw new IllegalArgumentException("Los vectores deben tener la misma dimensión.");
        double result = 0;
        for (int i = 0; i < v1.length; i++) {
            result += v1[i] * v2[i];
        }
        return result;
    }

    private double[] crossProduct(double[] v1, double[] v2) {
        if (v1.length != 3 || v2.length != 3) throw new IllegalArgumentException("El producto vectorial requiere vectores 3D.");
        double[] result = new double[3];
        result[0] = v1[1] * v2[2] - v1[2] * v2[1];
        result[1] = v1[2] * v2[0] - v1[0] * v2[2];
        result[2] = v1[0] * v2[1] - v1[1] * v2[0];
        return result;
    }

    private double[] scalarMultiplyVector(double scalar, double[] v) {
        double[] result = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            result[i] = scalar * v[i];
        }
        return result;
    }

    // --- Operaciones de Matrices ---
    private double[][] addMatrices(double[][] m1, double[][] m2) {
        if (m1.length != m2.length || m1[0].length != m2[0].length) {
            throw new IllegalArgumentException("Las matrices deben tener las mismas dimensiones para la suma.");
        }
        double[][] result = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                result[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return result;
    }

    private double[][] subtractMatrices(double[][] m1, double[][] m2) {
        if (m1.length != m2.length || m1[0].length != m2[0].length) {
            throw new IllegalArgumentException("Las matrices deben tener las mismas dimensiones para la resta.");
        }
        double[][] result = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                result[i][j] = m1[i][j] - m2[i][j];
            }
        }
        return result;
    }

    private double[][] multiplyMatrices(double[][] m1, double[][] m2) {
        if (m1[0].length != m2.length) {
            throw new IllegalArgumentException("El número de columnas de la primera matriz debe ser igual al número de filas de la segunda para la multiplicación.");
        }
        int rows1 = m1.length;
        int cols1 = m1[0].length;
        int cols2 = m2[0].length;
        double[][] result = new double[rows1][cols2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return result;
    }

    private double[][] scalarMultiplyMatrix(double scalar, double[][] m) {
        double[][] result = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                result[i][j] = scalar * m[i][j];
            }
        }
        return result;
    }

    private double determinant(double[][] matrix) {
        int n = matrix.length;
        if (n != matrix[0].length) {
            throw new IllegalArgumentException("La matriz debe ser cuadrada para calcular el determinante.");
        }

        if (n == 1) {
            return matrix[0][0];
        }
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double det = 0;
        for (int j = 0; j < n; j++) {
            det += Math.pow(-1, j) * matrix[0][j] * determinant(minor(matrix, 0, j));
        }
        return det;
    }

    private double[][] minor(double[][] matrix, int row, int col) {
        int n = matrix.length;
        double[][] minorMatrix = new double[n - 1][n - 1];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                minorMatrix[r][c] = matrix[i][j];
                c++;
            }
            r++;
        }
        return minorMatrix;
    }

    private double[][] transposeMatrix(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transposed = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    private double[][] inverseMatrix(double[][] matrix) {
        int n = matrix.length;
        if (n != matrix[0].length) {
            throw new IllegalArgumentException("La matriz debe ser cuadrada para calcular la inversa.");
        }

        double det = determinant(matrix);
        if (det == 0) {
            throw new ArithmeticException("La matriz no tiene inversa (determinante es cero).");
        }

        if (n == 1) {
            return new double[][]{{1 / matrix[0][0]}};
        }

        // Matriz de cofactores
        double[][] cofactor = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cofactor[i][j] = Math.pow(-1, i + j) * determinant(minor(matrix, i, j));
            }
        }

        // Adjunta (transpuesta de la matriz de cofactores)
        double[][] adjugate = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjugate[j][i] = cofactor[i][j]; // Transpuesta
            }
        }

        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = adjugate[i][j] / det;
            }
        }
        return inverse;
    }


    // --- Utilidades de Formato ---
    private String vecToString(double[] v) {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < v.length; i++) {
            sb.append(String.format("%.4f", v[i]));
            if (i < v.length - 1) sb.append(" "); // Espacio en lugar de coma
        }
        sb.append(")");
        return sb.toString();
    }

    private String matToString(double[][] m) {
        StringBuilder sb = new StringBuilder();
        // Encuentra la máxima longitud de número para alinear bien
        int maxLength = 0;
        for (double[] row : m) {
            for (double val : row) {
                maxLength = Math.max(maxLength, String.format("%.4f", val).length());
            }
        }
        // Formatear la cadena con alineación
        for (double[] row : m) {
            sb.append("[ ");
            for (double val : row) {
                sb.append(String.format("%" + (maxLength + 2) + ".4f", val)); // Añade un poco de espacio extra
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }

    // --- Impresión en área de salida ---
    private void print(String msg) {
        try {
 
            String numStr = msg.replaceAll("[^\\d.-]+", " ").trim(); 
            if (!numStr.isEmpty()) {
                String[] parts = numStr.split("\\s+");
                if (parts.length > 0 && !parts[0].isEmpty()) {
                    ans = Double.parseDouble(parts[0]);
                }
            }
        } catch (NumberFormatException e) {

        } catch (Exception e) {
            
            System.err.println("Error al intentar parsear 'ans' desde el mensaje: " + e.getMessage());
        }
        outputArea.setText(msg);
    }


    private void printErr(String msg) {
        outputArea.setText("❌ Error: " + msg);
    }

    // ESTE MÉTODO FALTABA Y CAUSABA EL ERROR DE COMPILACIÓN
    private void printErr(Exception ex) {
        outputArea.setText("❌ Error: " + ex.getMessage());
        // ex.printStackTrace(); // Para depuración, puedes descomentar esto
    }


    private void insertAns() {
        Component comp = getFocusOwner(); 
        System.out.println("DEBUG: Component with focus: " + comp); 

        if (comp instanceof JTextField) {
            JTextField field = (JTextField) comp;
            field.setText(String.valueOf(ans));
        } else if (comp instanceof JTextArea) {
            JTextArea area = (JTextArea) comp;
            area.replaceSelection(String.valueOf(ans)); 
        } else {
           
            printErr("Ningún campo de texto válido tiene el foco para insertar 'Ans'. Haz clic en un campo antes de usar el botón 'Insertar Ans'.");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(calculadoraGUI::new);
    }
}
