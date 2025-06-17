import java.util.Scanner;
import java.util.Random; // Importa Random para generar un número aleatorio

public class ejercicio8 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(); // Crea un objeto Random para generar números aleatorios

        int numeroSecreto = random.nextInt(100) + 1; // Genera un número aleatorio entre 1 y 100
        int intentos = 0; // Contador de intentos del jugador
        int suAdivinanza; // Variable para guardar lo que ingresa el usuario

        System.out.println("Juego de Adivinanza");
        System.out.println("Adivina el numero entre 1 y 100.");

        do {
            System.out.print("Ingresa tu numero: ");
            suAdivinanza = scanner.nextInt(); // Lee el número que ingresa el usuario
            intentos++; // Suma un intento

            if (suAdivinanza < numeroSecreto) {
                System.out.println("El número es MAYOR."); // Pista: el número secreto es más grande
            } else if (suAdivinanza > numeroSecreto) {
                System.out.println("El número es MENOR."); // Pista: el número secreto es más chico
            } else {
                // Mensaje final cuando adivina correctamente
                System.out.println("Adivinaste el número " + numeroSecreto + " en " + intentos + " intentos!");
            }
        } while (suAdivinanza != numeroSecreto); // Sigue hasta que adivine

        scanner.close(); // Cierra el scanner
    }
}
