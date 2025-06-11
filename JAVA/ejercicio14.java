import java.util.Scanner;

public class ejercicio14 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el numero | Secuencia de Fibonacci:");
        int n = scanner.nextInt();
       
        if (n == 1) {
            System.out.println("Los primeros " + n + " términos de Fibonacci son: 0");
        } else {
            long a = 0;
            long b = 1;

            System.out.print("Los primeros " + n + " términos de Fibonacci son: " + a + ", " + b);

            for (int i = 2; i < n; i++) {
                long siguienteTermino = a + b;
                System.out.print(", " + siguienteTermino);
                a = b;
                b = siguienteTermino;
            }
            System.out.println();
        }
        scanner.close();
    }
}
