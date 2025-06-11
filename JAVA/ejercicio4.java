import java.util.Scanner;
public class ejercicio4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numero;
		
		System.out.println("Ingrese un numero: ");
	    numero = scanner.nextInt();
	    
	    int SumaPares = 0;
	    int SumaImpares = 0;
	    
	    for(int i=1; i<=numero; i++) {
	    	if(i % 2 == 0) {
	    		SumaPares += i;
	    	}
	    	else {
	    		SumaImpares += i;
	    	}
	    }
	    
	    System.out.println("La suma de numero Pares de 1 hasta " + numero + " : " + SumaPares);
	    System.out.println("La suma de numero Impares de 1 hasta " + numero + " : " + SumaImpares);
	    
	    scanner.close();
	}

}
