import java.util.Scanner;

public class ejercicio12 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un número entero:");
        int numero = scanner.nextInt();

        int sumaDigitos = 0;
        int numeroOriginal = numero;

        if (numero < 0) {
            numero = -numero;
        }

        while (numero > 0) {
            int digito = numero % 10;
            sumaDigitos += digito;
            numero /= 10;
        }

        System.out.println("La suma de los dígitos de " + numeroOriginal + " es: " + sumaDigitos);
        scanner.close();
    }
}
