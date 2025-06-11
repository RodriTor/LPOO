import java.util.Scanner;
import java.util.regex.Matcher; // Necesario para buscar patrones
import java.util.regex.Pattern;

public class ejercicio17 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, ingresa una frase o un párrafo:");
        String texto = scanner.nextLine();

        int contadorPalabras = 0;

        Pattern patron = Pattern.compile("\\b\\p{L}+\\b");
        
       
        Matcher buscador = patron.matcher(texto);

        
        while (buscador.find()) {
            contadorPalabras++;
        }

        System.out.println("El número de palabras es: " + contadorPalabras);

        scanner.close();
    }
}
