import java.util.Scanner;

public class ejercicio6 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String frase;
		
		System.out.println("Ingrese frase: ");
		frase = scanner.nextLine();
		
		String invertido = "";
		for(int i = frase.length() - 1; i >= 0; i--) {
			invertido += frase.charAt(i);
		}
		
		System.out.println("La frase: " + frase + " / invertida es: " + invertido);
		scanner.close();
	}

}
