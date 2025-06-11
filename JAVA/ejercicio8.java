import java.util.Scanner;
import java.util.Random;

public class ejercicio8 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int numeroSecreto = random.nextInt(100) + 1;
        int intentos = 0;
        int suAdivinanza;

        System.out.println("Juego de Adivinanza");
        System.out.println("Adivina el numero entre 1 y 100.");

        do {
            System.out.print("Ingresa tu numero: ");
            suAdivinanza = scanner.nextInt();
            intentos++;

            if (suAdivinanza < numeroSecreto) {
                System.out.println("El número es MAYOR.");
            } else if (suAdivinanza > numeroSecreto) {
                System.out.println("El número es MENOR.");
            } else {
                System.out.println("Adivinaste el número " + numeroSecreto + " en " + intentos + " intentos!");
            }
        } while (suAdivinanza != numeroSecreto);

        scanner.close();
    }
}
