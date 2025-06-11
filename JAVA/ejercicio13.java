import java.util.Scanner;

public class ejercicio13 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione:");
        System.out.println("1. Celsius a Fahrenheit");
        System.out.println("2. Fahrenheit a Celsius");
        System.out.print("Ingrese su opción (1 o 2): ");
        int opcion = scanner.nextInt();

        double temperatura;
        double resultado;

        switch (opcion) {
            case 1:
                System.out.print("Ingrese la temperatura en Celsius: ");
                temperatura = scanner.nextDouble();
                resultado = (temperatura * 9 / 5) + 32;
                System.out.println(temperatura + "°C es igual a " + resultado + "°F");
                break;
            case 2:
                System.out.print("Ingrese la temperatura en Fahrenheit: ");
                temperatura = scanner.nextDouble();
                resultado = (temperatura - 32) * 5 / 9;
                System.out.println(temperatura + "°F es igual a " + resultado + "°C");
                break;
            default:
                System.out.println("Opción no válida. Por favor, ingrese 1 o 2.");
                break;
        }
        scanner.close();
    }
}
