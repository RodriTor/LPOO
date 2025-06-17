import java.util.Scanner;

public class ejercicio9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el primer numero:");
        int num1 = scanner.nextInt(); // Lee el primer número

        System.out.println("Ingrese el segundo numero:");
        int num2 = scanner.nextInt(); // Lee el segundo número

        System.out.println("Ingrese el tercer numero:");
        int num3 = scanner.nextInt(); // Lee el tercer número

        int mayor = num1; // Asume inicialmente que el primero es el mayor

        if (num2 > mayor) { // Compara con el segundo número
            mayor = num2; // Actualiza si el segundo es mayor
        }

        if (num3 > mayor) { // Compara con el tercer número
            mayor = num3; // Actualiza si el tercero es mayor
        }

        System.out.println("El número mayor es: " + mayor); // Muestra el mayor número
        scanner.close(); // Cierra el scanner
    }
}

        System.out.println("El número mayor es: " + mayor);
        scanner.close();
    }
}
