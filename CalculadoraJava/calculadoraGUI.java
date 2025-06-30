package interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class calculadoraGUI extends JFrame implements ActionListener {
    private JTextField displayBasico;
    private StringBuilder currentInputBasico;
    private double primerNumeroBasico = 0;
    private String operacionPendienteBasico = "";
    private boolean inicioDeNumeroBasico = true;

    private JTextField[] vector1Fields;
    private JTextField[] vector2Fields;
    private JTextField vectorResultadoField;
    private JTextField escalarVectorField;

    private JTextField[][] matrizAFields;
    private JTextField[][] matrizBFields;
    private JTextField filasAField, columnasAField, filasBField, columnasBField;
    private JTextArea matrizResultadoArea; 
    private JTextField escalarMatrizField;
    private JButton btnCargarMatrices;
    private JPanel inputMatrixPanelContainer; 

    private JTextField a1Field, b1Field, c1Field;
    private JTextField a2Field, b2Field, c2Field;
    private JLabel sistemaEcuacionesResultadoLabel;


    private JTabbedPane tabbedPane;


    public calculadoraGUI() {
        setTitle("Calculadora Avanzada - Múltiples Funciones");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    
        UIManager.put("Panel.background", new Color(240, 240, 240));
        UIManager.put("Button.background", new Color(200, 220, 240));
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 16));
        UIManager.put("TextField.background", Color.WHITE);
        UIManager.put("TextField.font", new Font("Monospaced", Font.PLAIN, 18));


        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        add(tabbedPane, BorderLayout.CENTER);

 
        setupBasicOperationsTab();

       
        setupVectorOperationsTab();

 
        setupMatrixOperationsTab();

 
        setupEquationSolverTab();

        setVisible(true);
    }


    private void setupBasicOperationsTab() {
        JPanel basicPanel = new JPanel(new BorderLayout(10, 10));
        basicPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        currentInputBasico = new StringBuilder();

        displayBasico = new JTextField("0");
        displayBasico.setEditable(false);
        displayBasico.setHorizontalAlignment(SwingConstants.RIGHT);
        displayBasico.setFont(new Font("Arial", Font.PLAIN, 40));
        basicPanel.add(displayBasico, BorderLayout.NORTH);

        JPanel numButtonsPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        String[] numButtons = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "="};
        for (String text : numButtons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 28));
            button.addActionListener(this);
            numButtonsPanel.add(button);
        }
        basicPanel.add(numButtonsPanel, BorderLayout.CENTER);

        JPanel opButtonsPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        String[] opButtons = {"+", "-", "*", "/", "AC", "√", "^"};
        for (String text : opButtons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 28));
            button.setBackground(new Color(255, 200, 100));
            if (text.equals("AC")) button.setBackground(new Color(255, 100, 100));
            if (text.equals("=")) button.setBackground(new Color(150, 255, 150));
            button.addActionListener(this);
            opButtonsPanel.add(button);
        }
        basicPanel.add(opButtonsPanel, BorderLayout.EAST);

        tabbedPane.addTab("Básica", basicPanel);
    }

    private void setupVectorOperationsTab() {
        JPanel vectorPanel = new JPanel(new GridBagLayout());
        vectorPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        vector1Fields = new JTextField[3];
        vector2Fields = new JTextField[3];

        gbc.gridx = 1; gbc.gridy = 0; vectorPanel.add(new JLabel("Vector 1:"), gbc);
        gbc.gridx = 3; gbc.gridy = 0; vectorPanel.add(new JLabel("Vector 2:"), gbc);

        for (int i = 0; i < 3; i++) {
            gbc.gridx = 0; gbc.gridy = i + 1; vectorPanel.add(new JLabel("Componente " + (i+1) + ":"), gbc);
            gbc.gridx = 1; vector1Fields[i] = new JTextField("0", 5); vectorPanel.add(vector1Fields[i], gbc);

            gbc.gridx = 2; gbc.gridy = i + 1; vectorPanel.add(new JLabel("Componente " + (i+1) + ":"), gbc);
            gbc.gridx = 3; vector2Fields[i] = new JTextField("0", 5); vectorPanel.add(vector2Fields[i], gbc);
        }

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1; vectorPanel.add(new JLabel("Escalar:"), gbc);
        gbc.gridx = 1; escalarVectorField = new JTextField("1", 5); vectorPanel.add(escalarVectorField, gbc);

        gbc.gridy = 5; gbc.gridwidth = 4;
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        String[] vectorOps = {"Suma", "Resta", "Escalar x Vec", "Prod. Escalar", "Prod. Vectorial"};
        for (String op : vectorOps) {
            JButton button = new JButton(op);
            button.addActionListener(this);
            buttonsPanel.add(button);
        }
        vectorPanel.add(buttonsPanel, gbc);

        gbc.gridy = 6; gbc.gridwidth = 4;
        vectorResultadoField = new JTextField("Resultado Vectorial: ");
        vectorResultadoField.setEditable(false);
        vectorPanel.add(vectorResultadoField, gbc);

        tabbedPane.addTab("Vectores", vectorPanel);
    }

    private void setupMatrixOperationsTab() {
        JPanel matrixPanel = new JPanel(new GridBagLayout());
        matrixPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Entradas de dimensiones con valores iniciales
        gbc.gridx = 0; gbc.gridy = 0; matrixPanel.add(new JLabel("Filas A:"), gbc);
        gbc.gridx = 1; filasAField = new JTextField("2", 3); matrixPanel.add(filasAField, gbc);
        gbc.gridx = 2; gbc.gridy = 0; matrixPanel.add(new JLabel("Cols A:"), gbc);
        gbc.gridx = 3; columnasAField = new JTextField("2", 3); matrixPanel.add(columnasAField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; matrixPanel.add(new JLabel("Filas B:"), gbc);
        gbc.gridx = 1; filasBField = new JTextField("2", 3); matrixPanel.add(filasBField, gbc);
        gbc.gridx = 2; gbc.gridy = 1; matrixPanel.add(new JLabel("Cols B:"), gbc);
        gbc.gridx = 3; columnasBField = new JTextField("2", 3); matrixPanel.add(columnasBField, gbc);

        // Botón para cargar las matrices (crea los JTextFields de entrada dinámicamente)
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4;
        btnCargarMatrices = new JButton("Definir Tamaño y Cargar Campos de Matriz");
        btnCargarMatrices.addActionListener(this);
        matrixPanel.add(btnCargarMatrices, gbc);

        // Panel que contendrá los JTextFields de las matrices
        inputMatrixPanelContainer = new JPanel();
        inputMatrixPanelContainer.setBorder(BorderFactory.createTitledBorder("Valores de Matrices (Ingrese Aquí)"));
        
        // Un JScrollPane para los campos de entrada de las matrices
        JScrollPane scrollInput = new JScrollPane(inputMatrixPanelContainer);
        scrollInput.setPreferredSize(new Dimension(300, 150));
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 0.5;
        matrixPanel.add(scrollInput, gbc);

        // Botones de operaciones de matrices
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4; gbc.weighty = 0;
        JPanel matrixButtonsPanel = new JPanel(new GridLayout(3, 4, 5, 5));
        String[] matrixOps = {"Suma Mat", "Resta Mat", "Mat x Escalar", "Mult. Mat",
                              "Determinante", "Transpuesta", "Inversa", "División Mat"};
        for (String op : matrixOps) {
            JButton button = new JButton(op);
            button.addActionListener(this);
            matrixButtonsPanel.add(button);
        }
        matrixPanel.add(matrixButtonsPanel, gbc);

        // Entrada para el escalar de matrices
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 1; matrixPanel.add(new JLabel("Escalar Matriz:"), gbc);
        gbc.gridx = 1; escalarMatrizField = new JTextField("1", 5); matrixPanel.add(escalarMatrizField, gbc);

        // Área de resultado de matrices
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 4; gbc.weighty = 0.5;
        matrizResultadoArea = new JTextArea("Resultado de Matriz:");
        matrizResultadoArea.setEditable(false);
        matrizResultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollOutput = new JScrollPane(matrizResultadoArea);
        matrixPanel.add(scrollOutput, gbc);

        tabbedPane.addTab("Matrices", matrixPanel);
    }

    private void setupEquationSolverTab() {
        JPanel equationPanel = new JPanel(new GridBagLayout());
        equationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Ecuación 1: a1x + b1y = c1
        gbc.gridx = 0; gbc.gridy = 0; equationPanel.add(new JLabel("a1:"), gbc);
        gbc.gridx = 1; a1Field = new JTextField("1", 5); equationPanel.add(a1Field, gbc);
        gbc.gridx = 2; gbc.gridy = 0; equationPanel.add(new JLabel("b1:"), gbc);
        gbc.gridx = 3; b1Field = new JTextField("2", 5); equationPanel.add(b1Field, gbc);
        gbc.gridx = 4; gbc.gridy = 0; equationPanel.add(new JLabel("c1:"), gbc);
        gbc.gridx = 5; c1Field = new JTextField("5", 5); equationPanel.add(c1Field, gbc);

        // Ecuación 2: a2x + b2y = c2
        gbc.gridx = 0; gbc.gridy = 1; equationPanel.add(new JLabel("a2:"), gbc);
        gbc.gridx = 1; a2Field = new JTextField("3", 5); equationPanel.add(a2Field, gbc);
        gbc.gridx = 2; gbc.gridy = 1; equationPanel.add(new JLabel("b2:"), gbc);
        gbc.gridx = 3; b2Field = new JTextField("4", 5); equationPanel.add(b2Field, gbc);
        gbc.gridx = 4; gbc.gridy = 1; equationPanel.add(new JLabel("c2:"), gbc);
        gbc.gridx = 5; c2Field = new JTextField("11", 5); equationPanel.add(c2Field, gbc);

        // Botón para resolver
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 6;
        JButton solveButton = new JButton("Resolver Sistema 2x2");
        solveButton.addActionListener(this);
        equationPanel.add(solveButton, gbc);

        // Etiqueta de resultado
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 6;
        sistemaEcuacionesResultadoLabel = new JLabel("Solución:");
        sistemaEcuacionesResultadoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        equationPanel.add(sistemaEcuacionesResultadoLabel, gbc);

        tabbedPane.addTab("Ecuaciones 2x2", equationPanel);
    }

    /**
     * Método para crear dinámicamente los campos de texto para la entrada de matrices.
     * Esto se hace al presionar "Definir Tamaño y Cargar Campos de Matriz".
     */
    private void createMatrixInputFields(JPanel containerPanel, int filasA, int columnasA, int filasB, int columnasB) {
        containerPanel.removeAll(); // Limpia cualquier campo anterior
        containerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Un layout simple para organizar A y B

        // Panel específico para Matriz A
        JPanel panelA = new JPanel(new GridLayout(filasA, columnasA, 5, 5));
        panelA.setBorder(BorderFactory.createTitledBorder("Matriz A (" + filasA + "x" + columnasA + ")"));
        matrizAFields = new JTextField[filasA][columnasA];
        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < columnasA; j++) {
                JTextField field = new JTextField("0", 3); // Valor inicial "0"
                matrizAFields[i][j] = field;
                panelA.add(field);
            }
        }
        containerPanel.add(panelA);

        // Panel específico para Matriz B
        JPanel panelB = new JPanel(new GridLayout(filasB, columnasB, 5, 5));
        panelB.setBorder(BorderFactory.createTitledBorder("Matriz B (" + filasB + "x" + columnasB + ")"));
        matrizBFields = new JTextField[filasB][columnasB];
        for (int i = 0; i < filasB; i++) {
            for (int j = 0; j < columnasB; j++) {
                JTextField field = new JTextField("0", 3); // Valor inicial "0"
                matrizBFields[i][j] = field;
                panelB.add(field);
            }
        }
        containerPanel.add(panelB);

        containerPanel.revalidate(); // Revalida el layout para que los nuevos componentes se muestren
        containerPanel.repaint(); // Redibuja el panel
    }


    /**
     * Implementación del ActionListener para manejar todos los eventos de botones.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand(); // Obtiene el texto del botón presionado

        // Obtener la pestaña actualmente seleccionada
        Component selectedComponent = tabbedPane.getSelectedComponent();

        if (selectedComponent == tabbedPane.getComponentAt(0)) { // Pestaña "Básica"
            handleBasicOperations(comando);
        } else if (selectedComponent == tabbedPane.getComponentAt(1)) { // Pestaña "Vectores"
            handleVectorOperations(comando);
        } else if (selectedComponent == tabbedPane.getComponentAt(2)) { // Pestaña "Matrices"
            handleMatrixOperations(comando);
        } else if (selectedComponent == tabbedPane.getComponentAt(3)) { // Pestaña "Ecuaciones 2x2"
            handleEquationSolverOperations(comando);
        }
    }

    // --- Métodos de Lógica para cada Pestaña (separados para mayor claridad) ---

    private void handleBasicOperations(String comando) {
        if ("0123456789.".contains(comando)) {
            if (inicioDeNumeroBasico) {
                displayBasico.setText(comando);
                inicioDeNumeroBasico = false;
            } else {
                if (comando.equals(".") && displayBasico.getText().contains(".")) {
                    return;
                }
                displayBasico.setText(displayBasico.getText() + comando);
            }
        } else if (comando.equals("AC")) {
            displayBasico.setText("0");
            primerNumeroBasico = 0;
            operacionPendienteBasico = "";
            inicioDeNumeroBasico = true;
        } else if (comando.equals("=")) {
            if (!operacionPendienteBasico.isEmpty()) {
                try {
                    double segundoNumero = Double.parseDouble(displayBasico.getText());
                    double resultado = 0;

                    switch (operacionPendienteBasico) {
                        case "+": resultado = primerNumeroBasico + segundoNumero; break;
                        case "-": resultado = primerNumeroBasico - segundoNumero; break;
                        case "*": resultado = primerNumeroBasico * segundoNumero; break;
                        case "/":
                            if (segundoNumero != 0) {
                                resultado = primerNumeroBasico / segundoNumero;
                            } else {
                                displayBasico.setText("Error: Div/0"); return;
                            }
                            break;
                        case "^":
                            resultado = Math.pow(primerNumeroBasico, segundoNumero);
                            break;
                    }
                    displayBasico.setText(String.valueOf(resultado));
                    primerNumeroBasico = resultado;
                    operacionPendienteBasico = "";
                    inicioDeNumeroBasico = true;
                } catch (NumberFormatException ex) {
                    displayBasico.setText("Error");
                }
            }
        } else if (comando.equals("√")) {
            try {
                double valor = Double.parseDouble(displayBasico.getText());
                if (valor >= 0) {
                    double resultadoRaiz = Math.sqrt(valor);
                    displayBasico.setText(String.valueOf(resultadoRaiz));
                } else {
                    displayBasico.setText("Error: Num Neg");
                }
                primerNumeroBasico = 0; operacionPendienteBasico = ""; inicioDeNumeroBasico = true;
            }
            catch (NumberFormatException ex) {
                displayBasico.setText("Error");
            }
        } else if (comando.equals("^")) {
             try {
                primerNumeroBasico = Double.parseDouble(displayBasico.getText());
                operacionPendienteBasico = comando;
                inicioDeNumeroBasico = true;
            } catch (NumberFormatException ex) {
                displayBasico.setText("Error");
            }
        }
        else { // Operaciones +, -, *, /
            try {
                if (!displayBasico.getText().equals("Error: Div/0") && !displayBasico.getText().equals("Error: Num Neg")) {
                    primerNumeroBasico = Double.parseDouble(displayBasico.getText());
                    operacionPendienteBasico = comando;
                    inicioDeNumeroBasico = true;
                }
            } catch (NumberFormatException ex) {
                displayBasico.setText("Error");
            }
        }
    }

    private void handleVectorOperations(String comando) {
        try {
            double[] v1 = new double[3];
            double[] v2 = new double[3];
            
            for (int i = 0; i < 3; i++) {
                v1[i] = Double.parseDouble(vector1Fields[i].getText());
                v2[i] = Double.parseDouble(vector2Fields[i].getText());
            }

            switch (comando) {
                case "Suma":
                    double[] sumaResult = calculadora.sumarVectoresGUI(v1, v2);
                    vectorResultadoField.setText(String.format("Suma: (%.2f, %.2f, %.2f)", sumaResult[0], sumaResult[1], sumaResult[2]));
                    break;
                case "Resta":
                     double[] restaResult = calculadora.restarVectoresGUI(v1, v2);
                    vectorResultadoField.setText(String.format("Resta: (%.2f, %.2f, %.2f)", restaResult[0], restaResult[1], restaResult[2]));
                    break;
                case "Escalar x Vec":
                    double escalar = Double.parseDouble(escalarVectorField.getText());
                    double[] escalarVecResult = calculadora.multiplicarEscalarVectorGUI(v1, escalar);
                    vectorResultadoField.setText(String.format("Vec1 x Escalar: (%.2f, %.2f, %.2f)", escalarVecResult[0], escalarVecResult[1], escalarVecResult[2]));
                    break;
                case "Prod. Escalar":
                    double productoEscalarResult = calculadora.productoEscalarGUI(v1, v2);
                    vectorResultadoField.setText(String.format("Prod. Escalar: %.2f", productoEscalarResult));
                    break;
                case "Prod. Vectorial":
                    double[] productoVectorialResult = calculadora.productoVectorialGUI(v1, v2);
                    vectorResultadoField.setText(String.format("Prod. Vectorial: (%.2f, %.2f, %.2f)", productoVectorialResult[0], productoVectorialResult[1], productoVectorialResult[2]));
                    break;
            }
        } catch (NumberFormatException ex) {
            vectorResultadoField.setText("Error: Entrada inválida. Asegúrate de ingresar solo números.");
        } catch (Exception ex) {
             vectorResultadoField.setText("Error de cálculo en vectores: " + ex.getMessage());
             ex.printStackTrace();
        }
    }

    private void handleMatrixOperations(String comando) {
        try {
            if (comando.equals("Definir Tamaño y Cargar Campos de Matriz")) {
                int filasA = Integer.parseInt(filasAField.getText());
                int columnasA = Integer.parseInt(columnasAField.getText());
                int filasB = Integer.parseInt(filasBField.getText());
                int columnasB = Integer.parseInt(columnasBField.getText());

                if (filasA <= 0 || columnasA <= 0 || filasB <= 0 || columnasB <= 0) {
                    matrizResultadoArea.setText("Error: Las dimensiones de la matriz deben ser mayores que 0.");
                    return;
                }
                if (filasA > 5 || columnasA > 5 || filasB > 5 || columnasB > 5) {
                    matrizResultadoArea.setText("Advertencia: Las dimensiones máximas recomendadas son 5x5 para visibilidad.");
                }

                createMatrixInputFields(inputMatrixPanelContainer, filasA, columnasA, filasB, columnasB);
                matrizResultadoArea.setText("Matrices cargadas. Ahora ingresa los valores en los campos de arriba.");
                return;
            }

            if (matrizAFields == null || matrizBFields == null ||
                matrizAFields.length == 0 || matrizBFields.length == 0 ||
                matrizAFields[0].length == 0 || matrizBFields[0].length == 0 ||
                matrizAFields[0][0] == null)
            {
                matrizResultadoArea.setText("Error: Primero define el tamaño y carga los campos de matriz con el botón 'Definir Tamaño...'.");
                return;
            }

            int filasA = matrizAFields.length;
            int columnasA = matrizAFields[0].length;
            int filasB = matrizBFields.length;
            int columnasB = matrizBFields[0].length;

            int[][] matrizA = new int[filasA][columnasA];
            int[][] matrizB = new int[filasB][columnasB];

            for (int i = 0; i < filasA; i++) {
                for (int j = 0; j < columnasA; j++) {
                    matrizA[i][j] = Integer.parseInt(matrizAFields[i][j].getText());
                }
            }
            for (int i = 0; i < filasB; i++) {
                for (int j = 0; j < columnasB; j++) {
                    matrizB[i][j] = Integer.parseInt(matrizBFields[i][j].getText());
                }
            }

            switch (comando) {
                case "Suma Mat":
                    if (filasA != filasB || columnasA != columnasB) {
                        matrizResultadoArea.setText("Error: Las matrices deben tener las mismas dimensiones para sumar.");
                        break;
                    }
                    int[][] sumaMatResult = calculadora.sumarMatricesGUI(matrizA, matrizB, filasA, columnasA);
                    matrizResultadoArea.setText("Suma de Matrices:\n" + formatMatrix(sumaMatResult, filasA, columnasA));
                    break;
                case "Resta Mat":
                    if (filasA != filasB || columnasA != columnasB) {
                        matrizResultadoArea.setText("Error: Las matrices deben tener las mismas dimensiones para restar.");
                        break;
                    }
                    int[][] restaMatResult = calculadora.restaMatricesGUI(matrizA, matrizB, filasA, columnasA);
                    matrizResultadoArea.setText("Resta de Matrices:\n" + formatMatrix(restaMatResult, filasA, columnasA));
                    break;
                case "Mat x Escalar":
                    int escalarMat = Integer.parseInt(escalarMatrizField.getText());
                    int[][] escalarMatResultA = calculadora.multiplicarPorEscalarGUI(matrizA, escalarMat, filasA, columnasA);
                    matrizResultadoArea.setText("Matriz A x Escalar:\n" + formatMatrix(escalarMatResultA, filasA, columnasA));
                    break;
                case "Mult. Mat":
                    if (columnasA != filasB) {
                        matrizResultadoArea.setText("Error: Para multiplicar matrices, las columnas de la primera deben ser iguales a las filas de la segunda (Cols A != Filas B).");
                        break;
                    }
                    int[][] multMatResult = calculadora.multiplicarMatricesGUI(matrizA, matrizB, filasA, columnasA, columnasB);
                    matrizResultadoArea.setText("Multiplicación de Matrices:\n" + formatMatrix(multMatResult, filasA, columnasB));
                    break;
                case "Determinante":
                    if (filasA != columnasA) {
                        matrizResultadoArea.setText("Error: La matriz A no es cuadrada para calcular el determinante.");
                        break;
                    }
                    if (filasA == 2 || filasA == 3) {
                        int det = calculadora.determinanteMatrizGUI(matrizA, filasA);
                        matrizResultadoArea.setText("Determinante de Matriz A: " + det);
                    } else {
                        matrizResultadoArea.setText("El cálculo de determinante solo está implementado para matrices 2x2 y 3x3 en tu lógica.");
                    }
                    break;
                case "Transpuesta":
                    matrizResultadoArea.setText("Transpuesta de Matriz A:\n" +
                                                formatMatrix(calculadora.transpuestaMatrizGUI(matrizA, filasA, columnasA), columnasA, filasA));
                    break;
                case "Inversa":
                    if (filasA != columnasA) {
                        matrizResultadoArea.setText("Error: La matriz A no es cuadrada para calcular la inversa.");
                        break;
                    }
                    if (filasA == 2) {
                        double[][] inversa = calculadora.inversaMatriz2x2Double(matrizA);
                        if (inversa != null) {
                            matrizResultadoArea.setText("Inversa de Matriz A (2x2):\n" + formatMatrix(inversa, filasA, columnasA));
                        } else {
                            matrizResultadoArea.setText("Matriz A no tiene inversa (determinante 0).");
                        }
                    } else {
                        matrizResultadoArea.setText("Cálculo de inversa solo implementado para 2x2 en tu lógica.");
                    }
                    break;
                case "División Mat":
                    if (filasB != columnasB) {
                        matrizResultadoArea.setText("Error: Matriz B debe ser cuadrada para calcular su inversa para la división.");
                        break;
                    }
                    if (filasB != 2 || columnasB != 2) { // Tu inversa solo es 2x2
                         matrizResultadoArea.setText("Error: Para la división (A * Inv(B)), la Matriz B debe ser 2x2.");
                         break;
                    }
                    if (columnasA != filasB) {
                        matrizResultadoArea.setText("Error: Las dimensiones no permiten multiplicar A * inversa(B). (Columnas A != Filas B)");
                        break;
                    }

                    double[][] invB = calculadora.inversaMatriz2x2Double(matrizB);
                    if (invB == null) {
                        matrizResultadoArea.setText("Error: Matriz B no tiene inversa (determinante 0). No se puede dividir.");
                        break;
                    }

                    double[][] divisionResult = new double[filasA][columnasB];
                    for (int i = 0; i < filasA; i++) {
                        for (int j = 0; j < columnasB; j++) {
                            for (int k = 0; k < columnasA; k++) {
                                divisionResult[i][j] += matrizA[i][k] * invB[k][j];
                            }
                        }
                    }
                    matrizResultadoArea.setText("División de Matrices (A * Inv(B)):\n" + formatMatrix(divisionResult, filasA, columnasB));
                    break;
            }
        } catch (NumberFormatException ex) {
            matrizResultadoArea.setText("Error: Entrada de matriz inválida. Asegúrate de ingresar solo números.");
            ex.printStackTrace();
        } catch (Exception ex) {
            matrizResultadoArea.setText("Error inesperado en operación de matriz: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void handleEquationSolverOperations(String comando) {
        if (comando.equals("Resolver Sistema 2x2")) {
            try {
                double a1 = Double.parseDouble(a1Field.getText());
                double b1 = Double.parseDouble(b1Field.getText());
                double c1 = Double.parseDouble(c1Field.getText());
                double a2 = Double.parseDouble(a2Field.getText());
                double b2 = Double.parseDouble(b2Field.getText());
                double c2 = Double.parseDouble(c2Field.getText());

                double det = a1 * b2 - a2 * b1;
                if (Math.abs(det) < 1e-9) { // Usar una tolerancia para comparar con cero
                    double detX = c1 * b2 - c2 * b1;
                    double detY = a1 * c2 - a2 * c1;
                    if (Math.abs(detX) < 1e-9 && Math.abs(detY) < 1e-9) {
                        sistemaEcuacionesResultadoLabel.setText("Solución: Infinitas soluciones.");
                    } else {
                        sistemaEcuacionesResultadoLabel.setText("Solución: No hay solución.");
                    }
                } else {
                    double x = (c1 * b2 - c2 * b1) / det;
                    double y = (a1 * c2 - a2 * c1) / det;
                    sistemaEcuacionesResultadoLabel.setText(String.format("Solución: x = %.5f, y = %.5f", x, y));
                }

            } catch (NumberFormatException ex) {
                sistemaEcuacionesResultadoLabel.setText("Error: Entrada inválida. Ingresa solo números.");
            }
        }
    }



    private String formatMatrix(int[][] matrix, int filas, int columnas) {
        StringBuilder sb = new StringBuilder();
        if (matrix == null) return "Matriz NULA";
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                sb.append(String.format("%5d ", matrix[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String formatMatrix(double[][] matrix, int filas, int columnas) {
        StringBuilder sb = new StringBuilder();
        if (matrix == null) return "Matriz NULA";
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                sb.append(String.format("%8.2f ", matrix[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new calculadoraGUI();
        });
    }
