import java.util.Scanner;
public class ejercicio7 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String frase;
		
		System.out.println("Ingrese frase: ");
		frase = scanner.nextLine();
		
		int ContadorVocales = 0;
		int ContadorConsonantes = 0;
		
		frase = frase.toLowerCase();

		for(int i = 0; i < frase.length(); i++) {
			char texton = frase.charAt(i);
			if(texton == 'a' || texton == 'e' || texton == 'i' || texton == 'o' || texton == 'u') {
				ContadorVocales++;
			}
			else {
				ContadorConsonantes++;
			}
		}
		
		System.out.println("Las vocales en la frase: " +  frase + " son: " + ContadorVocales);
		System.out.println("Las consonantes en la frase: " +  frase + " son: " + ContadorConsonantes);
		scanner.close();
	}

}
