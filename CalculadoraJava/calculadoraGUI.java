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

    // Área de salida unificada para todos los resultados y errores
    private final JTextArea outputArea = new JTextArea(5, 40);

    // --- Pestaña Básicas ---
    private JTextArea basicNumbersInput;

    // --- Pestaña Potencia / Raíz ---
    private JTextField basePotenciaField;
    private JTextField exponentePotenciaField;
    private JTextField radicandoRaizField;
    private JTextField indiceRaizField;

    // --- Pestaña Vectores ---
    private JTextField[][] vectorFields1; // Para el primer vector
    private JTextField[][] vectorFields2; // Para el segundo vector (cuando aplica)
    private JTextField escalarVectorField;
    private JComboBox<String> vectorOperationComboBox;
    private JComboBox<String> vectorDimensionComboBox;
    private JPanel vectorInputPanel1TwoVecs; // Panel para Vector 1 en vista de dos vectores
    private JPanel vectorInputPanel2TwoVecs; // Panel para Vector 2 en vista de dos vectores
    private JPanel vectorInputPanelScalarVec; // Panel para el vector en vista escalar


    // --- Pestaña Matrices ---
    // Instancias de los Spinners que son ÚNICAS para cada dimensión (A y B)
    private JSpinner rowsA_Spinner;
    private JSpinner colsA_Spinner;
    private JSpinner rowsB_Spinner; // Spinner para filas de Matriz B
    private JSpinner colsB_Spinner; // Spinner para columnas de Matriz B

    private JTextField[][] matrixAFields; // Matriz A (TextFields)
    private JTextField[][] matrixBFields; // Matriz B (TextFields)

    // Paneles para la cuadrícula de JTextFields. Ahora declarados a nivel de instancia.
    private JPanel matrixAGridPanelTwoMats;
    private JPanel matrixBGridPanelTwoMats;
    private JPanel matrixAGridPanelScalarMat;
    private JPanel matrixAGridPanelOneMat;
    private JTextField scalarMatrixField;
    private JComboBox<String> matrixOperationComboBox;


    // --- Pestaña Sistema 2x2 ---
    private JTextField a1Field, b1Field, c1Field;
    private JTextField a2Field, b2Field, c2Field;


    public calculadoraGUI() {
        setTitle("Calculadora Avanzada – GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600); // Tamaño inicial ajustado, más compacto
        setLocationRelativeTo(null); // Centrar la ventana

        // Configuración global de Look and Feel y estilos
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
        tabbedPane.addTab("Matrices", createMatrixPanel()); // Asegúrate de que este panel se crea correctamente
        tabbedPane.addTab("Sistema 2x2", createSystem2x2Panel());


        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Panel para el área de salida en la parte inferior
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Resultado / Errores"));
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        // Inicializar dimensiones de matriz a 2x2 por defecto al iniciar
        rowsA_Spinner.setValue(2);
        colsA_Spinner.setValue(2);
        rowsB_Spinner.setValue(2);
        colsB_Spinner.setValue(2);

        // Llamar a updateMatrixInputFields para inicializar los campos de matriz 2x2
        // Esto se llama después de que la GUI es visible, para asegurar que los paneles existan
        SwingUtilities.invokeLater(() -> {
            System.out.println("DEBUG: Inicializando campos de matriz después de GUI visible.");
            updateMatrixInputFields();
        });
    }

    private JPanel createBasicPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Entrada para números básicos
        basicNumbersInput = new JTextArea(3, 30);
        basicNumbersInput.setBorder(BorderFactory.createTitledBorder("Números (separados por espacios o comas)"));
        basicNumbersInput.setLineWrap(true);
        basicNumbersInput.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(basicNumbersInput);
        panel.add(scrollPane, BorderLayout.NORTH);

        // Panel de botones para operaciones básicas
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        String[] operations = {"Suma", "Resta", "Multiplicación", "División", "Modulo", "Seno", "Coseno", "Tangente"};
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
        basePotenciaField = new JTextField(4); // Más compacto
        panel.add(basePotenciaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Exponente:"), gbc);
        gbc.gridx = 1;
        exponentePotenciaField = new JTextField(4); // Más compacto
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
        radicandoRaizField = new JTextField(4); // Más compacto
        panel.add(radicandoRaizField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Índice (ej. 2 para raíz cuadrada):"), gbc);
        gbc.gridx = 1;
        indiceRaizField = new JTextField(4); // Más compacto
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

        // Panel de configuración (dimensiones y operación)
        JPanel configPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        vectorDimensionComboBox = new JComboBox<>(new String[]{"2", "3"});
        vectorOperationComboBox = new JComboBox<>(new String[]{"Suma", "Resta", "Producto Escalar", "Producto Vectorial", "Escalar por Vector"});

        configPanel.add(new JLabel("Dimensión:"));
        configPanel.add(vectorDimensionComboBox);
        configPanel.add(new JLabel("Operación:"));
        configPanel.add(vectorOperationComboBox);
        panel.add(configPanel, BorderLayout.NORTH);

        // Paneles para la entrada de vectores (dinámicos)
        vectorInputPanel1TwoVecs = new JPanel();
        vectorInputPanel2TwoVecs = new JPanel();
        vectorInputPanelScalarVec = new JPanel();

        // No BorderFactory.createTitledBorder aquí, se usará JLabel para el título si es necesario
        vectorInputPanel1TwoVecs.setName("Vector 1 Panel");
        vectorInputPanel2TwoVecs.setName("Vector 2 Panel");
        vectorInputPanelScalarVec.setName("Vector Scalar Panel");


        JPanel inputAreaPanel = new JPanel(new CardLayout());
        inputAreaPanel.add(createVectorTwoInputWrapperPanel(), "2_vecs_input"); // Wrapper para dos vectores
        inputAreaPanel.add(createVectorOneInputWrapperPanel(), "1_vec_input"); // Wrapper para un vector (escalar por vector)

        panel.add(inputAreaPanel, BorderLayout.CENTER);

        // Botón de cálculo
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(e -> executeVectorOperation());
        panel.add(btnCalcular, BorderLayout.SOUTH);

        // Listener para cambiar los campos de entrada según la operación y dimensión
        ActionListener updateVectorInputs = e -> {
            updateVectorInputFields(); // Llama a la función para redibujar campos
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

        // Asegúrate de que los campos iniciales se configuren correctamente
        SwingUtilities.invokeLater(this::updateVectorInputFields);

        return panel;
    }

    // Nuevo método wrapper para el diseño de dos vectores
    private JPanel createVectorTwoInputWrapperPanel() {
        JPanel wrapper = new JPanel(new GridLayout(2, 1, 5, 5)); // 2 filas, 1 columna para los dos paneles
        
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

    // Nuevo método wrapper para el diseño de un vector (escalar por vector)
    private JPanel createVectorOneInputWrapperPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(5, 5));
        // Aquí no hay label "Vector:" general, ya que escalar tiene su propio label y el vector usa el título del panel de campos
        wrapper.add(vectorInputPanelScalarVec, BorderLayout.CENTER);
        return wrapper;
    }


    private void updateVectorInputFields() {
        int dimension = Integer.parseInt((String) vectorDimensionComboBox.getSelectedItem());
        String selectedOperation = (String) vectorOperationComboBox.getSelectedItem();

        // Clear previous vector fields and panels
        if (vectorInputPanel1TwoVecs != null) vectorInputPanel1TwoVecs.removeAll();
        if (vectorInputPanel2TwoVecs != null) vectorInputPanel2TwoVecs.removeAll();
        if (vectorInputPanelScalarVec != null) vectorInputPanelScalarVec.removeAll();

        if ("Escalar por Vector".equals(selectedOperation)) {
            vectorFields1 = new JTextField[1][dimension];
            
            vectorInputPanelScalarVec.setLayout(new GridLayout(2, 1, 5, 5)); // Grid para escalar y vector
            vectorInputPanelScalarVec.setBorder(new EmptyBorder(0, 0, 0, 0)); // Limpiar borde

            // Setup scalar field
            JPanel scalarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            scalarPanel.add(new JLabel("Escalar:"));
            escalarVectorField = new JTextField(4); // Más compacto
            scalarPanel.add(escalarVectorField);
            vectorInputPanelScalarVec.add(scalarPanel);

            // Setup vector 1 fields
            JPanel vecPanel = new JPanel(new GridLayout(1, dimension, 3, 3)); // Más compacto
            vecPanel.setBorder(BorderFactory.createTitledBorder("Vector")); // Mantiene el título del vector
            for (int i = 0; i < dimension; i++) {
                JTextField field = new JTextField(3); // Más compacto
                vectorFields1[0][i] = field;
                vecPanel.add(field);
            }
            vectorInputPanelScalarVec.add(vecPanel);

        } else {
            // Operations requiring two vectors
            vectorFields1 = new JTextField[1][dimension];
            vectorFields2 = new JTextField[1][dimension];

            vectorInputPanel1TwoVecs.setLayout(new GridLayout(1, dimension, 3, 3)); // Más compacto
            for (int i = 0; i < dimension; i++) {
                JTextField field = new JTextField(3); // Más compacto
                vectorFields1[0][i] = field;
                vectorInputPanel1TwoVecs.add(field);
            }

            vectorInputPanel2TwoVecs.setLayout(new GridLayout(1, dimension, 3, 3)); // Más compacto
            for (int i = 0; i < dimension; i++) {
                JTextField field = new JTextField(3); // Más compacto
                vectorFields2[0][i] = field;
                vectorInputPanel2TwoVecs.add(field);
            }
        }
        // Repaint the panels to reflect changes
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

        // Panel de configuración (dimensiones y operación)
        JPanel configPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dimensiones Matriz A
        gbc.gridx = 0; gbc.gridy = 0; configPanel.add(new JLabel("Matriz A - Filas:"), gbc);
        gbc.gridx = 1; rowsA_Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1)); configPanel.add(rowsA_Spinner, gbc);
        gbc.gridx = 2; configPanel.add(new JLabel("Cols:"), gbc);
        gbc.gridx = 3; colsA_Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1)); configPanel.add(colsA_Spinner, gbc);

        // Dimensiones Matriz B
        gbc.gridx = 0; gbc.gridy = 1; configPanel.add(new JLabel("Matriz B - Filas:"), gbc);
        gbc.gridx = 1; rowsB_Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1)); configPanel.add(rowsB_Spinner, gbc);
        gbc.gridx = 2; configPanel.add(new JLabel("Cols:"), gbc);
        gbc.gridx = 3; colsB_Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1)); configPanel.add(colsB_Spinner, gbc);

        // Selector de operación de matrices
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; configPanel.add(new JLabel("Operación:"), gbc);
        gbc.gridx = 2; gbc.gridwidth = 2;
        matrixOperationComboBox = new JComboBox<>(new String[]{
                "Suma (A + B)", "Resta (A - B)", "Multiplicación (A * B)",
                "Escalar por Matriz", "Determinante (Matriz A)", "Transpuesta (Matriz A)", "Inversa (Matriz A)"
        });
        configPanel.add(matrixOperationComboBox, gbc);
        panel.add(configPanel, BorderLayout.NORTH);


        // Paneles de entrada para matrices (dinámicos)
        // Inicializar los paneles que contendrán los JTextFields
        matrixAGridPanelTwoMats = new JPanel();
        matrixBGridPanelTwoMats = new JPanel();
        matrixAGridPanelScalarMat = new JPanel();
        matrixAGridPanelOneMat = new JPanel();


        JPanel inputAreaPanel = new JPanel(new CardLayout());
        inputAreaPanel.add(createMatrixTwoInputWrapperPanel(), "two_mats_input"); // Para A+B, A-B, A*B
        inputAreaPanel.add(createMatrixScalarInputWrapperPanel(), "scalar_mat_input"); // Para Escalar * Matriz
        inputAreaPanel.add(createMatrixOneInputWrapperPanel(), "one_mat_input"); // Para Det, Transpuesta, Inversa

        panel.add(inputAreaPanel, BorderLayout.CENTER);

        // Botón de cálculo
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(e -> executeMatrixOperation());
        panel.add(btnCalcular, BorderLayout.SOUTH);

        // Listeners para spinners y combobox que actualizan los campos de entrada
        ActionListener updateInputListener = e -> {
            System.out.println("DEBUG: Cambiando dimensiones o operación de matriz. Actualizando campos.");
            updateMatrixInputFields();
            CardLayout cl = (CardLayout) (inputAreaPanel.getLayout());
            String selectedOperation = (String) matrixOperationComboBox.getSelectedItem();

            // Cambiar la vista de los paneles según la operación seleccionada
            if ("Suma (A + B)".equals(selectedOperation) || "Resta (A - B)".equals(selectedOperation) || "Multiplicación (A * B)".equals(selectedOperation)) {
                cl.show(inputAreaPanel, "two_mats_input");
            } else if ("Escalar por Matriz".equals(selectedOperation)) {
                cl.show(inputAreaPanel, "scalar_mat_input");
            } else { // Determinante, Transpuesta, Inversa
                cl.show(inputAreaPanel, "one_mat_input");
            }
        };

        rowsA_Spinner.addChangeListener(e -> updateInputListener.actionPerformed(null));
        colsA_Spinner.addChangeListener(e -> updateInputListener.actionPerformed(null));
        rowsB_Spinner.addChangeListener(e -> updateInputListener.actionPerformed(null));
        colsB_Spinner.addChangeListener(e -> updateInputListener.actionPerformed(null));
        matrixOperationComboBox.addActionListener(updateInputListener);

        // Inicializar los campos de matriz al final de la construcción del panel,
        // o después de que el frame sea visible para asegurar que los componentes estén completamente renderizados.
        SwingUtilities.invokeLater(this::updateMatrixInputFields); // Llama a la inicialización

        return panel;
    }

    // Método wrapper para el panel de entrada de dos matrices (con labels)
    private JPanel createMatrixTwoInputWrapperPanel() {
        JPanel wrapper = new JPanel(new GridLayout(2, 1, 10, 10)); // 2 filas, 1 columna para secciones A y B

        JPanel matASection = new JPanel(new BorderLayout());
        matASection.add(new JLabel("Matriz A:", SwingConstants.LEFT), BorderLayout.NORTH); // Etiqueta simple
        matASection.add(matrixAGridPanelTwoMats, BorderLayout.CENTER);

        JPanel matBSection = new JPanel(new BorderLayout());
        matBSection.add(new JLabel("Matriz B:", SwingConstants.LEFT), BorderLayout.NORTH); // Etiqueta simple
        matBSection.add(matrixBGridPanelTwoMats, BorderLayout.CENTER);

        wrapper.add(matASection);
        wrapper.add(matBSection);
        return wrapper;
    }

    // Método wrapper para el panel de entrada de escalar por matriz (con labels)
    private JPanel createMatrixScalarInputWrapperPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(5, 5));
        JPanel scalarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        scalarPanel.add(new JLabel("Escalar:"));
        scalarMatrixField = new JTextField(4); // Más compacto
        scalarPanel.add(scalarMatrixField);
        wrapper.add(scalarPanel, BorderLayout.NORTH);

        JPanel matSection = new JPanel(new BorderLayout());
        matSection.add(new JLabel("Matriz:", SwingConstants.LEFT), BorderLayout.NORTH); // Etiqueta simple
        matSection.add(matrixAGridPanelScalarMat, BorderLayout.CENTER);
        wrapper.add(matSection, BorderLayout.CENTER);
        return wrapper;
    }

    // Método wrapper para el panel de entrada de una sola matriz (con label)
    private JPanel createMatrixOneInputWrapperPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(5, 5));
        JPanel matSection = new JPanel(new BorderLayout());
        matSection.add(new JLabel("Matriz A:", SwingConstants.LEFT), BorderLayout.NORTH); // Etiqueta simple
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

        // Limpiar todos los paneles de entrada de matrices antes de redibujar
        matrixAGridPanelTwoMats.removeAll();
        matrixBGridPanelTwoMats.removeAll();
        matrixAGridPanelScalarMat.removeAll();
        matrixAGridPanelOneMat.removeAll();

        // 1. Configurar campos para operaciones de dos matrices (Suma, Resta, Multiplicación)
        if ("Suma (A + B)".equals(selectedOperation) || "Resta (A - B)".equals(selectedOperation) || "Multiplicación (A * B)".equals(selectedOperation)) {
            // Redimensionar los arrays de JTextFields a las nuevas dimensiones
            matrixAFields = new JTextField[rowsA][colsA];
            matrixBFields = new JTextField[rowsB][colsB];

            matrixAGridPanelTwoMats.setLayout(new GridLayout(rowsA, colsA, 3, 3)); // Más compacto
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsA; j++) {
                    JTextField field = new JTextField(3); // Más compacto
                    matrixAFields[i][j] = field; // ASIGNACIÓN CRÍTICA
                    matrixAGridPanelTwoMats.add(field);
                }
            }

            matrixBGridPanelTwoMats.setLayout(new GridLayout(rowsB, colsB, 3, 3)); // Más compacto
            for (int i = 0; i < rowsB; i++) {
                for (int j = 0; j < colsB; j++) {
                    JTextField field = new JTextField(3); // Más compacto
                    matrixBFields[i][j] = field; // ASIGNACIÓN CRÍTICA
                    matrixBGridPanelTwoMats.add(field);
                }
            }
        }
        // 2. Configurar campos para Escalar por Matriz
        else if ("Escalar por Matriz".equals(selectedOperation)) {
            matrixAFields = new JTextField[rowsA][colsA]; // Solo se necesita Matriz A

            matrixAGridPanelScalarMat.setLayout(new GridLayout(rowsA, colsA, 3, 3)); // Más compacto
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsA; j++) {
                    JTextField field = new JTextField(3); // Más compacto
                    matrixAFields[i][j] = field; // ASIGNACIÓN CRÍTICA
                    matrixAGridPanelScalarMat.add(field);
                }
            }
            // Asegurarse de que el campo escalar esté disponible
            if (scalarMatrixField == null) {
                scalarMatrixField = new JTextField(4); // Más compacto
            }
        }
        // 3. Configurar campos para operaciones de una sola matriz (Determinante, Transpuesta, Inversa)
        else { // Determinante, Transpuesta, Inversa
            matrixAFields = new JTextField[rowsA][colsA]; // Solo se necesita Matriz A

            matrixAGridPanelOneMat.setLayout(new GridLayout(rowsA, colsA, 3, 3)); // Más compacto
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsA; j++) {
                    JTextField field = new JTextField(3); // Más compacto
                    matrixAFields[i][j] = field; // ASIGNACIÓN CRÍTICA
                    matrixAGridPanelOneMat.add(field);
                }
            }
        }

        // Revalidar y repintar todos los paneles para asegurar que los cambios se reflejen
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
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Ecuación 1:"), gbc);
        gbc.gridx = 1; a1Field = new JTextField(4); panel.add(a1Field, gbc); // Más compacto
        gbc.gridx = 2; panel.add(new JLabel("x +"), gbc);
        gbc.gridx = 3; b1Field = new JTextField(4); panel.add(b1Field, gbc); // Más compacto
        gbc.gridx = 4; panel.add(new JLabel("y ="), gbc);
        gbc.gridx = 5; c1Field = new JTextField(4); panel.add(c1Field, gbc); // Más compacto

        // Ecuación 2: a2x + b2y = c2
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Ecuación 2:"), gbc);
        gbc.gridx = 1; a2Field = new JTextField(4); panel.add(a2Field, gbc); // Más compacto
        gbc.gridx = 2; panel.add(new JLabel("x +"), gbc);
        gbc.gridx = 3; b2Field = new JTextField(4); panel.add(b2Field, gbc); // Más compacto
        gbc.gridx = 4; panel.add(new JLabel("y ="), gbc);
        gbc.gridx = 5; c2Field = new JTextField(4); panel.add(c2Field, gbc); // Más compacto

        // Botón Resolver
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 6;
        JButton btnSolve = new JButton("Resolver Sistema");
        btnSolve.addActionListener(e -> executeSystem2x2Operation());
        panel.add(btnSolve, gbc);

        return panel;
    }


    // --- Métodos de ejecución de operaciones ---

    private void executeBasicOperation(String operation) {
        try {
            List<Double> numbers = parseBasicNumbersInput();
            if (numbers.isEmpty()) {
                printErr("No se ingresaron números.");
                return;
            }

            double result = 0;
            switch (operation) {
                case "Suma":
                    result = numbers.stream().mapToDouble(Double::doubleValue).sum();
                    print(String.format("Suma: %.4f", result));
                    break;
                case "Resta":
                    result = numbers.get(0);
                    for (int i = 1; i < numbers.size(); i++) result -= numbers.get(i);
                    print(String.format("Resta: %.4f", result));
                    break;
                case "Multiplicación":
                    result = 1;
                    for (double num : numbers) result *= num;
                    print(String.format("Multiplicación: %.4f", result));
                    break;
                case "División":
                    if (numbers.size() < 2) throw new IllegalArgumentException("Se necesitan al menos dos números para dividir.");
                    result = numbers.get(0);
                    for (int i = 1; i < numbers.size(); i++) {
                        if (numbers.get(i) == 0) throw new ArithmeticException("División por cero.");
                        result /= numbers.get(i);
                    }
                    print(String.format("División: %.4f", result));
                    break;
                case "Modulo":
                    if (numbers.size() < 2) throw new IllegalArgumentException("Se necesitan al menos dos números para el módulo.");
                    result = numbers.get(0) % numbers.get(1);
                    print(String.format("Módulo: %.4f", result));
                    break;
                case "Seno":
                    if (numbers.size() != 1) throw new IllegalArgumentException("Ingresa solo un número para el seno.");
                    result = Math.sin(Math.toRadians(numbers.get(0)));
                    print(String.format("Seno(%.2f°): %.4f", numbers.get(0), result));
                    break;
                case "Coseno":
                    if (numbers.size() != 1) throw new IllegalArgumentException("Ingresa solo un número para el coseno.");
                    result = Math.cos(Math.toRadians(numbers.get(0)));
                    print(String.format("Coseno(%.2f°): %.4f", numbers.get(0), result));
                    break;
                case "Tangente":
                    if (numbers.size() != 1) throw new IllegalArgumentException("Ingresa solo un número para la tangente.");
                    result = Math.tan(Math.toRadians(numbers.get(0)));
                    print(String.format("Tangente(%.2f°): %.4f", numbers.get(0), result));
                    break;
            }
        } catch (NumberFormatException e) {
            printErr("Entrada numérica inválida: " + e.getMessage());
        } catch (IllegalArgumentException | ArithmeticException e) {
            printErr("Error: " + e.getMessage());
        }
    }

    private void executePowerOperation() {
        try {
            double base = parseDoubleField(basePotenciaField, "Base");
            double exponente = parseDoubleField(exponentePotenciaField, "Exponente");
            double result = Math.pow(base, exponente);
            print(String.format("Potencia (%.2f^%.2f): %.4f", base, exponente, result));
        } catch (NumberFormatException e) {
            printErr("Entrada numérica inválida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            printErr("Error: " + e.getMessage());
        }
    }

    private void executeRootOperation() {
        try {
            double radicando = parseDoubleField(radicandoRaizField, "Radicando");
            double indice = parseDoubleField(indiceRaizField, "Índice");

            if (radicando < 0 && indice % 2 == 0) {
                throw new IllegalArgumentException("No se puede calcular la raíz par de un número negativo.");
            }
            if (indice == 0) {
                throw new IllegalArgumentException("El índice de la raíz no puede ser cero.");
            }

            double result = Math.pow(radicando, 1.0 / indice);
            print(String.format("Raíz (%.2f√%.2f): %.4f", indice, radicando, result));
        } catch (NumberFormatException e) {
            printErr("Entrada numérica inválida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            printErr("Error: " + e.getMessage());
        }
    }

    private void executeVectorOperation() {
        try {
            String selectedOperation = (String) vectorOperationComboBox.getSelectedItem();
            int dimension = Integer.parseInt((String) vectorDimensionComboBox.getSelectedItem());

            double[] v1 = null;
            double[] v2 = null;
            double scalar = 0;

            if ("Escalar por Vector".equals(selectedOperation)) {
                scalar = parseDoubleField(escalarVectorField, "Escalar");
                v1 = getVectorInput(vectorFields1, "Vector");
            } else {
                v1 = getVectorInput(vectorFields1, "Vector 1");
                v2 = getVectorInput(vectorFields2, "Vector 2");
            }


            double[] resultVector;
            double resultScalar;

            switch (selectedOperation) {
                case "Suma":
                    if (v1.length != v2.length) throw new IllegalArgumentException("Los vectores deben tener la misma dimensión para la suma.");
                    resultVector = new double[dimension];
                    for (int i = 0; i < dimension; i++) resultVector[i] = v1[i] + v2[i];
                    print("Suma de Vectores: " + vecToString(resultVector));
                    break;
                case "Resta":
                    if (v1.length != v2.length) throw new IllegalArgumentException("Los vectores deben tener la misma dimensión para la resta.");
                    resultVector = new double[dimension];
                    for (int i = 0; i < dimension; i++) resultVector[i] = v1[i] - v2[i];
                    print("Resta de Vectores: " + vecToString(resultVector));
                    break;
                case "Producto Escalar":
                    if (v1.length != v2.length) throw new IllegalArgumentException("Los vectores deben tener la misma dimensión para el producto escalar.");
                    resultScalar = 0;
                    for (int i = 0; i < dimension; i++) resultScalar += v1[i] * v2[i];
                    print(String.format("Producto Escalar: %.4f", resultScalar));
                    break;
                case "Producto Vectorial":
                    if (dimension != 3) throw new IllegalArgumentException("El producto vectorial solo aplica para vectores de dimensión 3.");
                    resultVector = new double[3];
                    resultVector[0] = v1[1] * v2[2] - v1[2] * v2[1];
                    resultVector[1] = v1[2] * v2[0] - v1[0] * v2[2];
                    resultVector[2] = v1[0] * v2[1] - v1[1] * v2[0];
                    print("Producto Vectorial: " + vecToString(resultVector));
                    break;
                case "Escalar por Vector":
                    resultVector = new double[dimension];
                    for (int i = 0; i < dimension; i++) resultVector[i] = scalar * v1[i];
                    print("Escalar por Vector: " + vecToString(resultVector));
                    break;
            }
        } catch (NumberFormatException e) {
            printErr("Entrada numérica inválida: " + e.getMessage());
        } catch (IllegalArgumentException | ArithmeticException e) {
            printErr("Error: " + e.getMessage());
        }
    }

    private void executeMatrixOperation() {
        try {
            System.out.println("DEBUG: Entrando a executeMatrixOperation.");
            String selectedOperation = (String) matrixOperationComboBox.getSelectedItem();

            int rowsA = (Integer) rowsA_Spinner.getValue();
            int colsA = (Integer) colsA_Spinner.getValue();
            int rowsB = (Integer) rowsB_Spinner.getValue();
            int colsB = (Integer) colsB_Spinner.getValue();

            System.out.println("DEBUG: Spinner A: " + rowsA + "x" + colsA + ", Spinner B: " + rowsB + "x" + colsB);

            double[][] matA;
            double[][] matB;
            double scalar;
            double[][] resultMatrix;

            switch (selectedOperation) {
                case "Suma (A + B)":
                case "Resta (A - B)":
                    // Asegurar que las matrices tienen las mismas dimensiones para suma y resta
                    if (rowsA != rowsB || colsA != colsB) {
                        throw new IllegalArgumentException("Las matrices deben tener las mismas dimensiones para " + selectedOperation.split(" ")[0].toLowerCase() + ".");
                    }
                    matA = getMatrixInput(matrixAFields, "Matriz A");
                    matB = getMatrixInput(matrixBFields, "Matriz B");
                    resultMatrix = new double[rowsA][colsA];
                    for (int i = 0; i < rowsA; i++) {
                        for (int j = 0; j < colsA; j++) {
                            if (selectedOperation.equals("Suma (A + B)")) {
                                resultMatrix[i][j] = matA[i][j] + matB[i][j];
                            } else { // Resta (A - B)
                                resultMatrix[i][j] = matA[i][j] - matB[i][j];
                            }
                        }
                    }
                    print(selectedOperation + ":\n" + matToString(resultMatrix));
                    break;

                case "Multiplicación (A * B)":
                    // Para la multiplicación, colsA debe ser igual a rowsB
                    if (colsA != rowsB) {
                        throw new IllegalArgumentException("Para la multiplicación (A*B), el número de columnas de A debe ser igual al número de filas de B.");
                    }
                    matA = getMatrixInput(matrixAFields, "Matriz A");
                    matB = getMatrixInput(matrixBFields, "Matriz B");
                    resultMatrix = new double[rowsA][colsB];
                    for (int i = 0; i < rowsA; i++) {
                        for (int j = 0; j < colsB; j++) {
                            for (int k = 0; k < colsA; k++) { // o rowsB
                                resultMatrix[i][j] += matA[i][k] * matB[k][j];
                            }
                        }
                    }
                    print("Multiplicación (A * B):\n" + matToString(resultMatrix));
                    break;

                case "Escalar por Matriz":
                    scalar = parseDoubleField(scalarMatrixField, "Escalar");
                    matA = getMatrixInput(matrixAFields, "Matriz");
                    resultMatrix = new double[rowsA][colsA];
                    for (int i = 0; i < rowsA; i++) {
                        for (int j = 0; j < colsA; j++) {
                            resultMatrix[i][j] = scalar * matA[i][j];
                        }
                    }
                    print("Escalar por Matriz:\n" + matToString(resultMatrix));
                    break;

                case "Determinante (Matriz A)":
                    if (rowsA != colsA) {
                        throw new IllegalArgumentException("La matriz debe ser cuadrada para calcular el determinante.");
                    }
                    matA = getMatrixInput(matrixAFields, "Matriz A");
                    double det = determinant(matA);
                    print(String.format("Determinante de Matriz A: %.4f", det));
                    break;

                case "Transpuesta (Matriz A)":
                    matA = getMatrixInput(matrixAFields, "Matriz A");
                    resultMatrix = transpose(matA);
                    print("Transpuesta de Matriz A:\n" + matToString(resultMatrix));
                    break;

                case "Inversa (Matriz A)":
                    if (rowsA != colsA) {
                        throw new IllegalArgumentException("La matriz debe ser cuadrada para calcular la inversa.");
                    }
                    matA = getMatrixInput(matrixAFields, "Matriz A");
                    double detInv = determinant(matA);
                    if (detInv == 0) {
                        throw new ArithmeticException("La matriz no tiene inversa (determinante es cero).");
                    }
                    resultMatrix = inverse(matA);
                    print("Inversa de Matriz A:\n" + matToString(resultMatrix));
                    break;
            }
        } catch (NumberFormatException e) {
            printErr("Entrada numérica inválida: " + e.getMessage());
        } catch (IllegalArgumentException | ArithmeticException | IllegalStateException e) {
            printErr("Error: " + e.getMessage());
        } catch (Exception e) {
            printErr("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace(); // Imprime la traza de la pila para depuración
        }
    }

    private void executeSystem2x2Operation() {
        try {
            double a1 = parseDoubleField(a1Field, "a1");
            double b1 = parseDoubleField(b1Field, "b1");
            double c1 = parseDoubleField(c1Field, "c1");
            double a2 = parseDoubleField(a2Field, "a2");
            double b2 = parseDoubleField(b2Field, "b2");
            double c2 = parseDoubleField(c2Field, "c2");

            double det = a1 * b2 - a2 * b1;
            if (det == 0) {
                printErr("El sistema no tiene solución única (determinante es cero).");
                return;
            }

            double x = (c1 * b2 - c2 * b1) / det;
            double y = (a1 * c2 - a2 * c1) / det;

            print(String.format("Solución del Sistema 2x2:\nx = %.4f\ny = %.4f", x, y));

        } catch (NumberFormatException e) {
            printErr("Entrada numérica inválida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            printErr("Error: " + e.getMessage());
        }
    }


    // --- Utilidades de Parsing y Validación ---

    private List<Double> parseBasicNumbersInput() throws NumberFormatException {
        String text = basicNumbersInput.getText().trim();
        if (text.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(text.split("[\\s,]+")) // Divide por espacios o comas
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    private double parseDoubleField(JTextField field, String fieldName) throws NumberFormatException {
        if (field == null) {
            throw new IllegalStateException("El campo '" + fieldName + "' no está inicializado.");
        }
        String text = field.getText().trim();
        if (text.isEmpty()) {
            throw new NumberFormatException("El campo '" + fieldName + "' no puede estar vacío.");
        }
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Valor inválido en el campo '" + fieldName + "'. Asegúrate de ingresar un número válido.");
        }
    }

    // Modificado: Ahora infiere las dimensiones de los campos existentes
    private double[][] getMatrixInput(JTextField[][] fields, String matrixName) throws NumberFormatException {
        // *** VERIFICACIÓN REFORZADA AL PRINCIPIO DEL MÉTODO ***
        // Verificamos si 'fields' es null O si su primera dimensión es 0 (vacío)
        // O si su primera fila (fields[0]) es null O si su segunda dimensión es 0
        if (fields == null || fields.length == 0 || fields[0] == null || fields[0].length == 0) {
            // Este error explícito se mostrará en el outputArea si la matriz de JTextFields no está bien construida.
            throw new IllegalStateException("La interfaz para la '" + matrixName + "' no está lista o sus dimensiones son incorrectas. Por favor, revisa que la matriz haya sido dibujada correctamente y que no esté vacía.");
        }

        int rows = fields.length;
        int cols = fields[0].length;

        System.out.println("DEBUG: getMatrixInput para " + matrixName + ". Intentando leer matriz de " + rows + "x" + cols);

        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            if (fields[i] == null) { // Verificación adicional para cada fila
                 throw new IllegalStateException("Error interno: Fila " + (i + 1) + " de la matriz '" + matrixName + "' es nula.");
            }
            if (fields[i].length != cols) { // Verificación de consistencia de columnas
                 throw new IllegalStateException("Error interno: Fila " + (i + 1) + " de la matriz '" + matrixName + "' tiene una dimensión inconsistente (" + fields[i].length + " esperadas " + cols + ").");
            }

            for (int j = 0; j < cols; j++) {
                JTextField field = fields[i][j];
                // Verificación adicional para campos individuales
                if (field == null) {
                    throw new IllegalStateException("Error interno: Campo de matriz '" + matrixName + "' en [" + (i + 1) + "," + (j + 1) + "] es nulo. Revisa la configuración de la interfaz.");
                }
                String text = field.getText(); // <-- Capturamos el texto del campo
                System.out.println("DEBUG: Leyendo " + matrixName + " [" + (i+1) + "," + (j+1) + "]: '" + text + "'"); // <-- MUY IMPORTANTE: Depuración del texto leído

                if (text.trim().isEmpty()) { // Verificar si el texto está realmente vacío
                    throw new NumberFormatException("El campo '" + matrixName + " [" + (i + 1) + "," + (j + 1) + "]' no puede estar vacío.");
                }
                try {
                    matrix[i][j] = Double.parseDouble(text);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Valor inválido en el campo '" + matrixName + " [" + (i + 1) + "," + (j + 1) + "]'. Asegúrate de ingresar un número válido.");
                }
            }
        }
        return matrix;
    }

    private double[] getVectorInput(JTextField[][] fields, String vectorName) throws NumberFormatException {
        if (fields == null || fields.length == 0 || fields[0] == null || fields[0].length == 0) {
            throw new IllegalStateException("La interfaz para el '" + vectorName + "' no está lista o sus dimensiones son incorrectas.");
        }
        int dimension = fields[0].length;
        double[] vector = new double[dimension];
        System.out.println("DEBUG: getVectorInput para " + vectorName + ". Intentando leer vector de " + dimension);
        for (int i = 0; i < dimension; i++) {
            JTextField field = fields[0][i];
            if (field == null) {
                throw new IllegalStateException("Error interno: Campo de vector '" + vectorName + "' en [" + (i + 1) + "] es nulo.");
            }
            String text = field.getText().trim();
            System.out.println("DEBUG: Leyendo " + vectorName + " [" + (i+1) + "]: '" + text + "'");
            if (text.isEmpty()) {
                throw new NumberFormatException("El campo '" + vectorName + " [" + (i + 1) + "]' no puede estar vacío.");
            }
            try {
                vector[i] = Double.parseDouble(text);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Valor inválido en el campo '" + vectorName + " [" + (i + 1) + "]'. Asegúrate de ingresar un número válido.");
            }
        }
        return vector;
    }


    // --- Lógica de cálculo de Matrices ---
    // (Métodos movidos de calculadora.java y adaptados para esta clase)

    public double[][] addMatrices(double[][] a, double[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    public double[][] subtractMatrices(double[][] a, double[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    public double[][] multiplyMatrices(double[][] a, double[][] b) {
        int r1 = a.length;
        int c1 = a[0].length;
        int r2 = b.length;
        int c2 = b[0].length;

        if (c1 != r2) {
            throw new IllegalArgumentException("Para la multiplicación de matrices, el número de columnas de la primera matriz debe ser igual al número de filas de la segunda.");
        }

        double[][] product = new double[r1][c2];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    product[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return product;
    }

    public double[][] scalarMultiplyMatrix(double scalar, double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = scalar * matrix[i][j];
            }
        }
        return result;
    }

    // Método para calcular el determinante (recursivo)
    public double determinant(double[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double det = 0;
        for (int j = 0; j < n; j++) {
            det += matrix[0][j] * Math.pow(-1, j) * determinant(minor(matrix, 0, j));
        }
        return det;
    }

    // Método para obtener la submatriz (menor)
    public double[][] minor(double[][] matrix, int row, int col) {
        int n = matrix.length;
        double[][] minorMatrix = new double[n - 1][n - 1];
        int minorRow = 0;
        int minorCol;

        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            minorCol = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                minorMatrix[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }
        return minorMatrix;
    }

    // Método para calcular la transpuesta
    public double[][] transpose(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transposedMatrix = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        return transposedMatrix;
    }

    // Método para calcular la inversa
    public double[][] inverse(double[][] matrix) {
        int n = matrix.length;
        double det = determinant(matrix);
        if (det == 0) {
            throw new ArithmeticException("No se puede calcular la inversa de una matriz con determinante cero.");
        }

        double[][] adjugate = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjugate[j][i] = Math.pow(-1, i + j) * determinant(minor(matrix, i, j));
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
        outputArea.setText(msg);
    }

    private void printErr(Exception ex) {
        outputArea.setText("❌ Error: " + ex.getMessage());
        // ex.printStackTrace(); // Para depuración, puedes descomentar esto
    }

    private void printErr(String msg) {
        outputArea.setText("❌ Error: " + msg);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(calculadoraGUI::new);
    }
}
