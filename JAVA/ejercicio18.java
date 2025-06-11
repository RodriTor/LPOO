import java.util.Scanner;

public class ejercicio18 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un número N para el pin de asteriscos:");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("N debe ser un número positivo.");
        } else {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }

        scanner.close();
    }
}
