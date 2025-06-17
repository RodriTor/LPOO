import java.util.Scanner;

public class ejercicio6 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String frase;
		
		System.out.println("Ingrese frase: ");
		frase = scanner.nextLine(); // Lee una línea completa como texto (incluye espacios)
		
		String invertido = ""; // Variable que almacenará la frase invertida
		
		for(int i = frase.length() - 1; i >= 0; i--) { // Recorre la frase desde el final hacia el inicio
			invertido += frase.charAt(i); // Agrega cada carácter al nuevo string invertido
		}
		
		// Muestra la frase original y su versión invertida
		System.out.println("La frase: " + frase + " / invertida es: " + invertido);
		
		scanner.close(); // Cierra el scanner
	}
}
