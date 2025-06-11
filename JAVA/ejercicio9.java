import java.util.Scanner;

public class ejercicio9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el primer numero:");
        int num1 = scanner.nextInt();

        System.out.println("Ingrese el segundo numero:");
        int num2 = scanner.nextInt();

        System.out.println("Ingrese el tercer numero:");
        int num3 = scanner.nextInt();

        int mayor = num1;

        if (num2 > mayor) {
            mayor = num2;
        }

        if (num3 > mayor) {
            mayor = num3;
        }

        System.out.println("El número mayor es: " + mayor);
        scanner.close();
    }
}
