import java.util.Scanner;

public class ejercicio16 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la longitud del primer lado del triángulo:");
        double lado1 = scanner.nextDouble();

        System.out.println("Ingrese la longitud del segundo lado del triángulo:");
        double lado2 = scanner.nextDouble();

        System.out.println("Ingrese la longitud del tercer lado del triángulo:");
        double lado3 = scanner.nextDouble();

        if (lado1 == lado2 && lado2 == lado3) {
            System.out.println("El triángulo es Equilátero.");
        } else if (lado1 == lado2 || lado1 == lado3 || lado2 == lado3) {
            System.out.println("El triángulo es Isósceles.");
        } else {
            System.out.println("El triángulo es Escaleno.");
        }

        scanner.close();
    }
}
