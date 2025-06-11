import java.util.Scanner;
import java.util.Random;

public class ejercicio20 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Ingrese la longitud deseada para la contraseña:");
        int longitud = scanner.nextInt();

        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder contraseña = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            int indiceAleatorio = random.nextInt(caracteres.length());
            contraseña.append(caracteres.charAt(indiceAleatorio));
        }

        System.out.println("Contraseña generada: " + contraseña.toString());
        scanner.close();
    }
}
