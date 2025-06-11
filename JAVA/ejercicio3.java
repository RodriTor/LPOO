import java.util.Scanner;
public class ejercicio3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numero;
		int divisores;
		
		do {
		System.out.println("Ingrese un numero: ");
	    numero = scanner.nextInt();
		divisores = 0;
	    if(numero > 100) {
	    	for(int i=1; i<numero/2; i++) {
	    		if(numero % i == 0) {
	    			divisores++;
	    		}
	    	}
	    }
		
		}while(numero < 100 || divisores > 2);
		
		System.out.println(numero + " Es mayor que 100 y es primo");
	}

}
