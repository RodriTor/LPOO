import java.util.Scanner;

public class ejercicio10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un número entero no negativo para calcular su factorial:");
        int numero = scanner.nextInt(); // Lee el número para calcular el factorial

        if (numero < 0) { // Verifica si el número es negativo
            System.out.println("Error: El número debe ser no negativo."); // Mensaje de error para números negativos
        } else {
            long factorial = 1; // Usamos long para manejar factoriales grandes

            for (int i = 1; i <= numero; i++) { // Multiplica de 1 hasta el número para calcular factorial
                factorial = factorial * i;
            }
            System.out.println("El factorial de " + numero + " es: " + factorial); // Muestra el resultado
        }
        scanner.close(); // Cierra el scanner
    }
}

