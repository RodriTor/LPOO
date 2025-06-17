import java.util.Scanner;

public class ejercicio7 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String frase;
		
		System.out.println("Ingrese frase: ");
		frase = scanner.nextLine(); // Lee la frase ingresada por el usuario
		
		int ContadorVocales = 0; // Cuenta cuántas vocales hay
		int ContadorConsonantes = 0; // Cuenta cuántas consonantes (y otros caracteres) hay
		
		frase = frase.toLowerCase(); // Convierte toda la frase a minúsculas para facilitar la comparación

		for(int i = 0; i < frase.length(); i++) { // Recorre cada carácter de la frase
			char texton = frase.charAt(i); // Obtiene el carácter en la posición i
			
			if(texton == 'a' || texton == 'e' || texton == 'i' || texton == 'o' || texton == 'u') {
				ContadorVocales++; // Si es vocal, suma al contador de vocales
			}
			else {
				ContadorConsonantes++; // Si no es vocal, suma al de consonantes (incluye espacios y símbolos)
			}
		}
		
		// Muestra los resultados
		System.out.println("Las vocales en la frase: " +  frase + " son: " + ContadorVocales);
		System.out.println("Las consonantes en la frase: " +  frase + " son: " + ContadorConsonantes);
		
		scanner.close();
	}
}

