import java.util.Scanner;

public class ejercicio10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un número entero no negativo para calcular su factorial:");
        int numero = scanner.nextInt();

        if (numero < 0) {
            System.out.println("Error: El número debe ser no negativo.");
        } else {
            long factorial = 1;

            for (int i = 1; i <= numero; i++) {
                factorial = factorial*i;
            }
            System.out.println("El factorial de " + numero + " es: " + factorial);
        }
        scanner.close();
    }
}
