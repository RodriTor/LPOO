import java.util.Scanner;

public class ejercicio11 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un año:");
        int anio = scanner.nextInt();

        boolean esBisiesto = false;

        if (anio % 4 == 0) {
            if (anio % 100 == 0) {
                if (anio % 400 == 0) {
                    esBisiesto = true;
                }
            } else {
                esBisiesto = true;
            }
        }

        if (esBisiesto) {
            System.out.println(anio + " es un año bisiesto.");
        } else {
            System.out.println(anio + " NO es un año bisiesto.");
        }
        scanner.close();
    }
}
