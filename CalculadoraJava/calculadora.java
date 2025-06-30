import java.util.Scanner;

public class calculadora {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n----- MENÚ -----");
            System.out.println("1) Suma");
            System.out.println("2) Resta");
            System.out.println("3) Multiplicacion");
            System.out.println("4) Division");
            System.out.println("5) Potencia");
            System.out.println("6) Raíz");
            System.out.println("7) Suma de Vectores");
            System.out.println("8) Resta de Vectores");
            System.out.println("9) Multiplicacion de escalar por vector");
            System.out.println("10) Producto escalar");
            System.out.println("11) Producto vectorial");
            System.out.println("12) Suma de Matrices");
            System.out.println("13) Resta de Matrices");
            System.out.println("14) Multiplicar matriz por un escalar");
            System.out.println("15) Multiplicacion entre matricess");
            System.out.println("16) Multiplicacion de escalar por vector");
            System.out.println("17) Determinante de una matriz");
            System.out.println("18) Transpuesta de una matriz");
            System.out.println("19) Inversa de una matriz");
            System.out.println("20) Division de matrices");
            System.out.println("21) Sistema de ecuaciones 2x2");
            System.out.println("0) Salir");
            System.out.print("Elegí una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	System.out.print("¿Cuántos números querés sumar? ");
                    int n1 = scanner.nextInt();
                    int[] sumaArr = new int[n1];
                    for (int i = 0; i < n1; i++) {
                        System.out.print("Número " + (i + 1) + ": ");
                        sumaArr[i] = scanner.nextInt();
                    }
                    sumaArr(sumaArr);
                    break;

                case 2:
                	 System.out.print("¿Cuántos números querés restar? ");
                     int n2 = scanner.nextInt();
                     int[] restaArr = new int[n2];
                     for (int i = 0; i < n2; i++) {
                         System.out.print("Número " + (i + 1) + ": ");
                         restaArr[i] = scanner.nextInt();
                     }
                     restaArr(restaArr);
                     break;

                case 3:
                	 System.out.print("¿Cuántos números querés multiplicar? ");
                     int n3 = scanner.nextInt();
                     int[] multArr = new int[n3];
                     for (int i = 0; i < n3; i++) {
                         System.out.print("Número " + (i + 1) + ": ");
                         multArr[i] = scanner.nextInt();
                     }
                     multiplicacionArr(multArr);
                     break;


                case 4:
                    System.out.print("Ingrese un número a dividir: ");
                    int num1_d = scanner.nextInt();
                    System.out.print("Ingrese el segundo número a dividir: ");
                    int num2_d = scanner.nextInt();
                    if (num2_d != 0) {
                        System.out.println("El resultado es: " + (num1_d / num2_d));
                    } else {
                        System.out.println("No se puede dividir por cero.");
                    }
                    break;
                    
                    
                case 5:
                	 System.out.print("Base: ");
                     double base = scanner.nextDouble();
                     System.out.print("Exponente: ");
                     double exp = scanner.nextDouble();
                     potencia(base, exp);
                     break;

                     
                case 6:
                	System.out.print("Número: ");
                    double num = scanner.nextDouble();
                    System.out.print("Índice de la raíz: ");
                    double raiz = scanner.nextDouble();
                    raiz(num, raiz);
                    break;
                    
                    
                case 7:
                	double[] v1 = new double[3];
                    double[] v2 = new double[3];
                    for (int i = 0; i < 3; i++) {
                        System.out.print("vector1[" + i + "]: ");
                        v1[i] = scanner.nextDouble();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.print("vector2[" + i + "]: ");
                        v2[i] = scanner.nextDouble();
                    }
                    sumaVectores(v1, v2);
                    break;
                    
                case 8:
                	
                	 double[] v3 = new double[3];
                double[] v4 = new double[3];
                for (int i = 0; i < 3; i++) {
                    System.out.print("vector1[" + i + "]: ");
                    v3[i] = scanner.nextDouble();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.print("vector2[" + i + "]: ");
                    v4[i] = scanner.nextDouble();
                }
                restaVectores(v3, v4);
                break;


                case 10:
                	double[] ve1 = new double[3];
                    double[] ve2 = new double[3];
                    for (int i = 0; i < 3; i++) {
                        System.out.print("v1[" + i + "]: ");
                        ve1[i] = scanner.nextDouble();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.print("v2[" + i + "]: ");
                        ve2[i] = scanner.nextDouble();
                    }
                    productoEscalar(ve1, ve2);
                    break;
                    
                    
                case 11:
                    double[] vec1 = new double[3];
                    double[] vec2 = new double[3];
                    for (int i = 0; i < 3; i++) {
                        System.out.print("v1[" + i + "]: ");
                        vec1[i] = scanner.nextDouble();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.print("v2[" + i + "]: ");
                        vec2[i] = scanner.nextDouble();
                    }
                    productoVectorial(vec1, vec2);
                    break;

                	
                	
                case 12:
                    System.out.print("Ingrese el número de filas de la primera matriz: ");
                    int filasA = scanner.nextInt();
                    System.out.print("Ingrese el número de columnas de la primera matriz: ");
                    int columnasA = scanner.nextInt();
                    int [][] matrizA = new int[filasA][columnasA];
                    ingresarMatriz(scanner, matrizA, filasA, columnasA);

                    System.out.print("Ingrese el número de filas de la segunda matriz: ");
                    int filasB = scanner.nextInt();
                    System.out.print("Ingrese el número de columnas de la segunda matriz: ");
                    int columnasB = scanner.nextInt();

                    if (filasA != filasB || columnasA != columnasB) {
                        System.out.println("Error: Las matrices deben tener las mismas dimensiones para sumar.");
                        break;
                    }

                    int[][] matrizB = new int[filasB][columnasB];
                    ingresarMatriz(scanner, matrizB, filasB, columnasB);

                    int[][] resultado = sumarMatrices(matrizA, matrizB, filasA, columnasA);
                    System.out.println("Resultado de la suma:");
                    mostrarMatriz(resultado, filasA, columnasA);
                    break;

                    
                case 13:
                	
                    System.out.print("Ingrese el número de filas de la primera matriz: ");
                    int filasA2 = scanner.nextInt();
                    System.out.print("Ingrese el número de columnas de la primera matriz: ");
                    int columnasA2 = scanner.nextInt();
                    int [][] matrizA2 = new int[filasA2][columnasA2];
                    ingresarMatriz(scanner, matrizA2, filasA2, columnasA2);

                    System.out.print("Ingrese el número de filas de la segunda matriz: ");
                    int filasB2 = scanner.nextInt();
                    System.out.print("Ingrese el número de columnas de la segunda matriz: ");
                    int columnasB2 = scanner.nextInt();

                    if (filasA2 != filasB2 || columnasA2 != columnasB2) {
                        System.out.println("Error: Las matrices deben tener las mismas dimensiones para sumar.");
                        break;
                    }

                    int[][] matrizB2 = new int[filasB2][columnasB2];
                    ingresarMatriz(scanner, matrizB2, filasB2, columnasB2);

                    int[][] resultado2 = restaMatrices(matrizA2, matrizB2, filasA2, columnasA2);
                    System.out.println("Resultado de la resta:");
                    mostrarMatriz(resultado2, filasA2, columnasA2);

                    break;
                    
                    
                case 14:
                    System.out.print("Ingrese el número de filas de la matriz: ");
                    int filasM = scanner.nextInt();
                    System.out.print("Ingrese el número de columnas de la matriz: ");
                    int columnasM = scanner.nextInt();
                    int[][] matrizM = new int[filasM][columnasM];
                    ingresarMatriz(scanner, matrizM, filasM, columnasM);

                    System.out.print("Ingrese el escalar por el que desea multiplicar: ");
                    int escalar = scanner.nextInt();

                    int[][] resultadoEscalar = multiplicarPorEscalar(matrizM, escalar, filasM, columnasM);
                    System.out.println("Resultado de la multiplicación por escalar:");
                    mostrarMatriz(resultadoEscalar, filasM, columnasM);
                    break;
                    
                case 15:
                    System.out.print("Ingrese el numero de filas de la primera matriz: ");
                    int filasA3 = scanner.nextInt();
                    System.out.print("Ingrese el numero de columnas de la primera matriz: ");
                    int columnasA3 = scanner.nextInt();
                    int[][] matrizA3 = new int[filasA3][columnasA3];
                    ingresarMatriz(scanner, matrizA3, filasA3, columnasA3);

                    System.out.print("Ingrese el numero de filas de la segunda matriz: ");
                    int filasB3 = scanner.nextInt();
                    System.out.print("Ingrese el numero de columnas de la segunda matriz: ");
                    int columnasB3 = scanner.nextInt();

                    if (columnasA3 != filasB3) {
                        System.out.println("Error: El numero de columnas de la primera matriz debe ser igual al numero de filas de la segunda matriz.");
                        break;
                    }

                    int[][] matrizB3 = new int[filasB3][columnasB3];
                    ingresarMatriz(scanner, matrizB3, filasB3, columnasB3);

                    int[][] resultado3 = multiplicarMatrices(matrizA3, matrizB3, filasA3, columnasA3, columnasB3);
                    System.out.println("Resultado de la multiplicación de matrices:");
                    mostrarMatriz(resultado3, filasA3, columnasB3);
                    break;
                    
                case 9:
                    System.out.print("Ingrese la longitud del vector: ");
                    int longitud = scanner.nextInt();
                    int[] vector = new int[longitud];

                    System.out.println("Ingrese los elementos del vector:");
                    for (int i = 0; i < longitud; i++) {
                        System.out.print("Elemento [" + (i + 1) + "]: ");
                        vector[i] = scanner.nextInt();
                    }

                    System.out.print("Ingrese el escalar por el que quiere multiplicar el vector: ");
                    int escalar2 = scanner.nextInt();

                    int[] resultadoVector = multiplicarVectorPorEscalar(vector, escalar2, longitud);

                    System.out.println("Resultado del vector multiplicado por el escalar:");
                    for (int i = 0; i < longitud; i++) {
                        System.out.print(resultadoVector[i] + " ");
                    }
                    System.out.println();
                    break;

                    
                case 17:
                    System.out.print("Ingrese el tamaño de la matriz (2 o 3): ");
                    int orden = scanner.nextInt();

                    if (orden != 2 && orden != 3) {
                        System.out.println("Error: Solo se permiten matrices 2x2 o 3x3.");
                        break;
                    }

                    int[][] matrizDet = new int[orden][orden];
                    ingresarMatriz(scanner, matrizDet, orden, orden);

                    int determinante = determinanteMatriz(matrizDet, orden);
                    System.out.println("El determinante de la matriz es: " + determinante);
                    break;
                    
                    
                case 18:
                    System.out.print("Ingrese el número de filas: ");
                    int filasT = scanner.nextInt();
                    System.out.print("Ingrese el número de columnas: ");
                    int columnasT = scanner.nextInt();
                    int[][] matrizT = new int[filasT][columnasT];
                    ingresarMatriz(scanner, matrizT, filasT, columnasT);
                    transpuestaMatriz(matrizT, filasT, columnasT);
                    break;

                    
                case 19:
                    int n = 2;
                    int[][] matrizInv = new int[n][n];
                    ingresarMatriz(scanner, matrizInv, n, n);
                    inversaMatriz2x2(matrizInv);
                    break;

                    
                    
                case 21:
                	
                    System.out.print("a1: ");
                    double a1 = scanner.nextDouble();
                    System.out.print("b1: ");
                    double b1 = scanner.nextDouble();
                    System.out.print("c1: ");
                    double c1 = scanner.nextDouble();
                    System.out.print("a2: ");
                    double a2 = scanner.nextDouble();
                    System.out.print("b2: ");
                    double b2 = scanner.nextDouble();
                    System.out.print("c2: ");
                    double c2 = scanner.nextDouble();
                    resolverSistema2x2(a1, b1, c1, a2, b2, c2);
                    break;
                    
                case 0:
                    System.out.println("Saliendo");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 0);

        scanner.close();
    }

    
    public static void ingresarMatriz(Scanner scanner, int[][] matriz, int filas, int columnas) {
        System.out.println("Ingrese los elementos de la matriz:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.printf("Elemento [%d][%d]: ", i+1, j+1);
                matriz[i][j] = scanner.nextInt();
            }
        }
    }


    public static void mostrarMatriz(int[][] matriz, int filas, int columnas) {
        System.out.println("Matriz:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static int[][] sumarMatrices(int[][] matrizA, int[][] matrizB, int filas, int columnas) {
        int[][] resultado = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }
        return resultado;
    }
    
    
    public static int[][] restaMatrices(int[][] matrizA, int[][] matrizB, int filas, int columnas) {
        int[][] resultado = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado[i][j] = matrizA[i][j] - matrizB[i][j];
            }
        }
        return resultado;
    }
    
    public static int[][] multiplicarPorEscalar(int[][] matriz, int escalar, int filas, int columnas) {
        int[][] resultado = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado[i][j] = matriz[i][j] * escalar;
            }
        }
        return resultado;
    }
    
    
    public static int[][] multiplicarMatrices(int[][] matrizA, int[][] matrizB, int filasA, int columnasA, int columnasB) {
        int[][] resultado = new int[filasA][columnasB];
        
        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < columnasB; j++) {
                for (int k = 0; k < columnasA; k++) {
                    resultado[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
        
        return resultado;
    }
    
    public static int[] multiplicarVectorPorEscalar(int[] vector, int escalar, int longitud) {
        int[] resultado = new int[longitud];
        for (int i = 0; i < longitud; i++) {
            resultado[i] = vector[i] * escalar;
        }
        return resultado;
    }
    
    
    
    public static int determinanteMatriz(int[][] matriz, int n) {
        int det = 0;

        if (n == 2) {
            det = matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        } else if (n == 3) {
            det = matriz[0][0]*(matriz[1][1]*matriz[2][2] - matriz[1][2]*matriz[2][1])
                - matriz[0][1]*(matriz[1][0]*matriz[2][2] - matriz[1][2]*matriz[2][0])
                + matriz[0][2]*(matriz[1][0]*matriz[2][1] - matriz[1][1]*matriz[2][0]);
        } else {
            System.out.println("Solo se admite matriz de 2x2 o 3x3.");
        }

        return det;
    }
    
    
    public static void inversaMatriz2x2(int[][] matriz) {
        int a = matriz[0][0];
        int b = matriz[0][1];
        int c = matriz[1][0];
        int d = matriz[1][1];

        int det = a * d - b * c;

        if (det == 0) {
            System.out.println("La matriz no tiene inversa porque su determinante es 0.");
            return;
        }

        double[][] inversa = new double[2][2];
        inversa[0][0] = d / (double)det;
        inversa[0][1] = -b / (double)det;
        inversa[1][0] = -c / (double)det;
        inversa[1][1] = a / (double)det;

        System.out.println("La inversa de la matriz es:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.printf("%f ", inversa[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void transpuestaMatriz(int[][] matriz, int filas, int columnas) {
        int[][] transpuesta = new int[columnas][filas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                transpuesta[j][i] = matriz[i][j];
            }
        }

        System.out.println("La matriz transpuesta es:");
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                System.out.print(transpuesta[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    
    public static void sumaArr(int[] arr) {
        int suma = 0;
        for (int num : arr) suma += num;
        System.out.println("La suma es: " + suma);
    }

    public static void restaArr(int[] arr) {
        int resta = arr[0];
        for (int i = 1; i < arr.length; i++) resta -= arr[i];
        System.out.println("La resta es: " + resta);
    }

    public static void multiplicacionArr(int[] arr) {
        int mult = 1;
        for (int num : arr) mult *= num;
        System.out.println("La multiplicación es: " + mult);
    }

    public static void potencia(double base, double exponente) {
        double resultado = Math.pow(base, exponente);
        System.out.printf("El resultado es: %.2f\n", resultado);
    }

    public static void raiz(double base, double raiz) {
        if (raiz == 0) {
            System.out.println("Error: raíz cero no es válida.");
        } else {
            double resultado = Math.pow(base, 1.0 / raiz);
            System.out.printf("El resultado es: %.2f\n", resultado);
        }
    }

    public static void sumaVectores(double[] v1, double[] v2) {
        double[] resultado = new double[v1.length];
        for (int i = 0; i < v1.length; i++) resultado[i] = v1[i] + v2[i];
        System.out.println("Resultado de la suma de vectores:");
        for (double val : resultado) System.out.printf("%.2f ", val);
        System.out.println();
    }

    public static void restaVectores(double[] v1, double[] v2) {
        double[] resultado = new double[v1.length];
        for (int i = 0; i < v1.length; i++) resultado[i] = v1[i] - v2[i];
        System.out.println("Resultado de la resta de vectores:");
        for (double val : resultado) System.out.printf("%.2f ", val);
        System.out.println();
    }

    public static void productoVectorial(double[] v1, double[] v2) {
        double[] resultado = new double[3];
        resultado[0] = v1[1] * v2[2] - v1[2] * v2[1];
        resultado[1] = v1[2] * v2[0] - v1[0] * v2[2];
        resultado[2] = v1[0] * v2[1] - v1[1] * v2[0];
        System.out.printf("Producto vectorial: (%.2f, %.2f, %.2f)\n", resultado[0], resultado[1], resultado[2]);
    }

    public static void productoEscalar(double[] v1, double[] v2) {
        double resultado = 0;
        for (int i = 0; i < v1.length; i++) resultado += v1[i] * v2[i];
        System.out.printf("Producto escalar: %.2f\n", resultado);
    }

    public static void resolverSistema2x2(double a1, double b1, double c1, double a2, double b2, double c2) {
        double factor = a2 / a1;
        b2 -= factor * b1;
        c2 -= factor * c1;
        if (Math.abs(b2) < 1e-9) {
            if (Math.abs(c2) < 1e-9) {
                System.out.println("Infinitas soluciones.");
            } else {
                System.out.println("No hay solución.");
            }
            return;
        }
        double y = c2 / b2;
        double x = (c1 - b1 * y) / a1;
        System.out.printf("Solución:\nx = %.5f\ny = %.5f\n", x, y);
    }

    
    
    

    
    
    
}
