import java.util.Random;

public class ejercicio19 {

    public static void main(String[] args) {
        Random random = new Random();

        // Genera un número aleatorio entre 1 y 6 para el primer dado
        int dado1 = random.nextInt(6) + 1;

        // Genera un número aleatorio entre 1 y 6 para el segundo dado
        int dado2 = random.nextInt(6) + 1;

        int sumaTotal = dado1 + dado2;

        System.out.println("Lanzamiento de Dados:");
        System.out.println("Dado 1: " + dado1);
        System.out.println("Dado 2: " + dado2);
        System.out.println("Suma total: " + sumaTotal);
    }
}
