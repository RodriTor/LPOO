import java.util.Scanner;
public class ejercicio7 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String frase;
		
		System.out.println("Ingrese frase: ");
		frase = scanner.nextLine();
		
		int ContadorVocales;
		int ContadorConsonantes;
		
		frase = frase.toLowerCase();

		for(int i = 0; i < frase.length(); i++) {
			char texton = texton.charAt(i);
			if(texton = 'a' || texton = 'e') {
				ContadorVocales++;
			}
			else {
				ContadorConsonantes++;
			}
		}
		
		System.out.println 
		scanner.close();
	}

}
