import java.util.Scanner;

public class ejercicio15 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double saldo = 1000.00;
        int opcion;

        System.out.println("Bienvenido a su Cajero Automático");

        do {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.printf("Su saldo actual es: $%.2f%n", saldo);
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad a depositar: $");
                    double deposito = scanner.nextDouble();
                    if (deposito > 0) {
                        saldo += deposito;
                      
                        System.out.printf("Depósito realizado con éxito. Nuevo saldo: $%.2f%n", saldo);
                    } else {
                        System.out.println("La cantidad a depositar debe ser positiva.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad a retirar: $");
                    double retiro = scanner.nextDouble();
                    if (retiro > 0) {
                        if (retiro <= saldo) {
                            saldo -= retiro;

                            System.out.printf("Retiro realizado con éxito. Nuevo saldo: $%.2f%n", saldo);
                        } else {
                            
                            System.out.printf("Saldo insuficiente. No puede retirar más de $%.2f%n", saldo);
                        }
                    } else {
                        System.out.println("La cantidad a retirar debe ser positiva.");
                    }
                    break;
                case 4:
                    System.out.println("Gracias por usar nuestro cajero automático. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 4);

        scanner.close();
    }
}
